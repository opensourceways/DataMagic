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

package com.om.DataMagic.common.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class YamlConfig {

    /**
     * yaml config feild hsitNDCG.
     */
    private List<String> hostNDCG;
    /**
     * yaml config feild portNDCG.
     */
    private int portNDCG;
    /**
     * yaml config feild protocolNDCG.
     */
    private String protocolNDCG;
    /**
     * yaml config feild usernameNDCG.
     */
    private String usernameNDCG;
    /**
     * yaml config feild passwordNDCG.
     */
    private String passwordNDCG;
    /**
     * yaml config feild useCerNDCG.
     */
    private boolean useCerNDCG;
    /**
     * yaml config feild cerFilePathNDCG.
     */
    private String cerFilePathNDCG;
    /**
     * yaml config feild cerPasswordNDCG.
     */
    private String cerPasswordNDCG;
    /**
     * yaml config feild host.
     */
    private List<String> host;
    /**
     * yaml config feild port.
     */
    private int port;
    /**
     * yaml config feild protocol.
     */
    private String protocol;
    /**
     * yaml config feild username.
     */
    private String username;
    /**
     * yaml config feild password.
     */
    private String password;
    /**
     * yaml config feild useCer.
     */
    private boolean useCer;
    /**
     * yaml config feild cerFilePath.
     */
    private String cerFilePath;
    /**
     * yaml config feild cerPassword.
     */
    private String cerPassword;

}
