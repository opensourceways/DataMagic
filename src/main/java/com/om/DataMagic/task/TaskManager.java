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

package com.om.DataMagic.task;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.process.DriverManager;

@Component
public class TaskManager {
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * task config.
     */
    @Autowired
    TaskConfig config;

    /**
     * Map of task mappings.
     */
    public static final Map<String, String> TASK_MAPPING = Map.of(
            "gitcode_user", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeProcess",
            "gitcode_repo", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeRepoProcess",
            "gitcode_pr", "com.om.DataMagic.process.codePlatform.gitcode.GitCodePRProcess",
            "gitcode_issue", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeIssueProcess",
            "gitcode_comment", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeCommentProcess",
            "gitee_user", "com.om.DataMagic.process.codePlatform.gitee.GiteeProcess",
            "gitcode_star", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeStarProcess",
            "gitcode_watch", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeWatchProcess",
            "gitcode_fork", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeForkProcess"
    );
            

    /**
     * Logger for logging messages in TaskManager class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);

    public void runTasks() {
        String[] driverNames = config.getTasks().split(",");
        try {
            for (String name : driverNames) {
                String className = TASK_MAPPING.get(name);
                Class<?> driverClass = Class.forName(className);
                DriverManager driver = (DriverManager) applicationContext.getBean(driverClass);
                driver.run();
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("run exception - {}", e.getMessage());
        }
    }
}
