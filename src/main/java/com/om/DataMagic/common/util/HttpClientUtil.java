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

import com.om.DataMagic.common.constant.HttpConstant;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpClientUtil {

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
    @Retryable(recover = "recoverApiResp", value = { IOException.class }, maxAttempts = 3)
    public String getHttpClient(final String uri, final Header header) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(REQUEST_CONFIG);

        if (header != null) {
            httpGet.addHeader(header);
        }
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IOException(EntityUtils.toString(response.getEntity()));
        }
        String responseRes = EntityUtils.toString(response.getEntity());
        return responseRes;
    }



    @Retryable(recover = "recoverApiResp", value = { IOException.class }, maxAttempts = 3)
    public String postHttpClient(final String uri, final Header header,String jsonBody) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(REQUEST_CONFIG);

        if (header != null) {
            httpPost.addHeader(header);
        }

        if (jsonBody != null) {
            StringEntity entity = new StringEntity(jsonBody, "UTF-8");
            // 设置请求内容类型
            entity.setContentType("application/json");
            // 将StringEntity设置为HttpPost的实体
            httpPost.setEntity(entity);
        }

        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IOException(EntityUtils.toString(response.getEntity()));
        }
        String responseRes = EntityUtils.toString(response.getEntity());
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
        return "Retry 3 times failed: " + uri;
    }



    @Retryable(recover = "recoverApiResp", value = { IOException.class }, maxAttempts = 3)
    public String getHttpClient(final String uri) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(REQUEST_CONFIG);

        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IOException(EntityUtils.toString(response.getEntity()));
        }
        String responseRes = EntityUtils.toString(response.getEntity());
        return responseRes;
    }

}