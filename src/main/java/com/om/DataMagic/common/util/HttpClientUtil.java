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

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.om.DataMagic.common.constant.HttpConstant;

public final class HttpClientUtil {
    // Private constructor to prevent instantiation of the utility class
    private HttpClientUtil() {
        // private constructor to hide the implicit public one
        throw new AssertionError("HttpClientUtil class cannot be instantiated.");
    }

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
     * @param uri       The URI for the HTTP client.
     * @param header     The request header.
     * @return The HTTP client as a string.
     */
    public static String getHttpClient(final String uri, final Header header) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(REQUEST_CONFIG);

        if (header != null) {
            httpGet.addHeader(header);
        }

        String responseRes = "";
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                LOGGER.info(response.getEntity().toString());
            }
            responseRes = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            LOGGER.error("error happend in get request - {}", e.getMessage());
        }
        return responseRes;
    }

}

