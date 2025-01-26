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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.om.DataMagic.common.config.RetryConfig;
import com.om.DataMagic.common.exception.RateLimitException;
import org.apache.http.Header;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.util.HttpClientUtil;

@Component
public class GitCodeClient {

    /**
     * 任务配置信息.
     */
    @Autowired
    private TaskConfig config;

    /**
     * 重试机制配置信息.
     */
    @Autowired
    private RetryConfig retryConfig;

    /**
     * httpclient .
     */
    @Autowired
    private HttpClientUtil client;

    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GitCodeClient.class);

    /**
     * Send a POST request using an HTTP client to the specified URI with the given
     * request body.
     *
     * @param path   The path for the api request.
     * @param params The params for the api request.
     * @return The response from the request as a string.
     */
    @Retryable(recover = "recoverRateLimit", value = {
            RateLimitException.class}, maxAttemptsExpression = "#{@retryConfig.maxAttempts}",
            backoff = @Backoff(delayExpression = "#{@retryConfig.delay}", multiplierExpression = "#{@retryConfig.multiplier}"))
    public String callApi(String path, Map<String, String> params) throws RateLimitException {
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
        List<String> tokenList = Arrays.stream(config.getToken().split(",")).toList();
        for (String token : tokenList) {
            Header header = new BasicHeader("Authorization", "Bearer " + token);
            String response = "";
            try {
                response = client.getHttpClient(url, header);
                if (response.startsWith("RateLimitException retry 3 times failed: ")) {
                    LOGGER.info("reach limit and change token");
                    continue;
                }
            } catch (RateLimitException | IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            return response;
        }
        LOGGER.error("all token reach limit error!");
        throw new RateLimitException(url);
    }

    /**
     * handle retry failed call and set default response.
     *
     * @param e      The RateLimitException.
     * @param path   The path for the api request.
     * @param params The params for the api request.
     * @return The default response.
     */
    @Recover
    public String recoverRateLimit(RateLimitException e, String path, Map<String, String> params) {
        LOGGER.info(path + e.getMessage());
        return "Too Many Requests :" + path;
    }

}
