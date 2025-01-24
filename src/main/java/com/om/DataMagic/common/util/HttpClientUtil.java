/*
 This project is licensed under the Mulan PSL v2.
 You can use this software according to the terms and conditions of the Mulan PSL v2.
 You may obtain a copy of Mulan PSL v2 at:
     http://license.coscl.org.cn/MulanPSL2
 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 See the Mulan PSL v2 for more details.
 Created: 2025
*/

package com.om.DataMagic.common.util;

import java.io.IOException;

import com.om.DataMagic.common.config.RetryConfig;
import com.om.DataMagic.common.exception.RateLimitException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import com.om.DataMagic.common.constant.HttpConstant;

@Component
public class HttpClientUtil {

    @Autowired
    RetryConfig retryConfig;

    /**
     * Logger for HttpClientUtil.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * Request configuration with timeout settings.
     */
    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(HttpConstant.TIME_OUT)
            .setSocketTimeout(HttpConstant.TIME_OUT).build();

    /**
     * Get an HTTP client with specified parameters.
     *
     * @param uri    The URI for the HTTP client.
     * @param header The request header.
     * @return The HTTP client as a string.
     * @throws IOException
     */
    @Retryable(recover = "recoverApiResp", value = {IOException.class, RateLimitException.class}, maxAttemptsExpression = "#{@retryConfig.maxAttempts}")
    public String getHttpClient(final String uri, final Header header) throws IOException, RateLimitException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(REQUEST_CONFIG);

        if (header != null) {
            httpGet.addHeader(header);
        }
        HttpResponse response = httpClient.execute(httpGet);
        String responseRes = EntityUtils.toString(response.getEntity());
        // 400 次数超出限制 404 找不到用户
        if (response.getStatusLine().getStatusCode() == 400) {
            throw new RateLimitException("limit error");
        }
        if (response.getStatusLine().getStatusCode() != 404 && response.getStatusLine().getStatusCode() != 200) {
            throw new IOException(responseRes);
        }
        return responseRes;
    }

    /**
     * Get an HTTP client with specified parameters.
     *
     * @param e      The IOException.
     * @param uri    The URI for the HTTP client.
     * @param header The request header.
     * @return The recover response as a string.
     */
    @Recover
    public String recoverApiResp(IOException e, final String uri, final Header header) {
        LOGGER.info(uri + e.getMessage());
        return "IOException retry 3 times failed: " + uri;
    }

    /**
     * Handle rate limit exception when retry still error.
     *
     * @param e      The RateLimitException.
     * @param uri    The URI for the HTTP client.
     * @param header The request header.
     * @return The recover response as a string.
     */
    @Recover
    public String recoverApiResp(RateLimitException e, final String uri, final Header header) {
        LOGGER.info(uri + e.getMessage());
        return "RateLimitException retry 3 times failed: " + uri;
    }
}