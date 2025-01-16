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

package com.om.DataMagic.client.codePlatform.gitcode;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.util.HttpClientUtil;

@Component
public class GitcodeClient {

    @Autowired
    TaskConfig config;

    @Autowired
    HttpClientUtil client;

    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GitcodeClient.class);

    /**
     * Send a POST request using an HTTP client to the specified URI with the given
     * request body.
     *
     * @param path   The path for the api request.
     * @param params The params for the api request.
     * @return The response from the request as a string.
     */
    public String callApi(String path, Map<String, String> params) {
        String url = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(config.getBaseApi() + path);
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            url = uriBuilder.build().toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Header header = new BasicHeader("Authorization", "Bearer " + config.getToken());

        String response = "";
        try {
            response = client.getHttpClient(url, header);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    public String getUserInfo(String username) {
        String path = "/users/" + username;
        Map<String, String> params = Map.of(
                "page", "1",
                "pageSize", "100");
        return callApi(path, params);
    }
}
