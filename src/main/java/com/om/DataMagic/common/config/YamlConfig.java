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

import java.util.List;

public class YamlConfig {
    private List<String> hostNDCG;
    private int portNDCG;
    private String protocolNDCG;
    private String usernameNDCG;
    private String passwordNDCG;
    private boolean useCerNDCG;
    private String cerFilePathNDCG;
    private String cerPasswordNDCG;


    private List<String> host;
    private int port;
    private String protocol;
    private String username;
    private String password;
    private boolean useCer;
    private String cerFilePath;
    private String cerPassword;

    public List<String> getHostNDCG() {
        return hostNDCG;
    }

    public void setHostNDCG(List<String> hostNDCG) {
        this.hostNDCG = hostNDCG;
    }

    public int getPortNDCG() {
        return portNDCG;
    }

    public void setPortNDCG(int portNDCG) {
        this.portNDCG = portNDCG;
    }

    public String getProtocolNDCG() {
        return protocolNDCG;
    }

    public void setProtocolNDCG(String protocolNDCG) {
        this.protocolNDCG = protocolNDCG;
    }

    public String getUsernameNDCG() {
        return usernameNDCG;
    }

    public void setUsernameNDCG(String usernameNDCG) {
        this.usernameNDCG = usernameNDCG;
    }

    public String getPasswordNDCG() {
        return passwordNDCG;
    }

    public void setPasswordNDCG(String passwordNDCG) {
        this.passwordNDCG = passwordNDCG;
    }

    public boolean isUseCerNDCG() {
        return useCerNDCG;
    }

    public void setUseCerNDCG(boolean useCerNDCG) {
        this.useCerNDCG = useCerNDCG;
    }

    public String getCerFilePathNDCG() {
        return cerFilePathNDCG;
    }

    public void setCerFilePathNDCG(String cerFilePathNDCG) {
        this.cerFilePathNDCG = cerFilePathNDCG;
    }

    public String getCerPasswordNDCG() {
        return cerPasswordNDCG;
    }

    public void setCerPasswordNDCG(String cerPasswordNDCG) {
        this.cerPasswordNDCG = cerPasswordNDCG;
    }

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUseCer() {
        return useCer;
    }

    public void setUseCer(boolean useCer) {
        this.useCer = useCer;
    }

    public String getCerFilePath() {
        return cerFilePath;
    }

    public void setCerFilePath(String cerFilePath) {
        this.cerFilePath = cerFilePath;
    }

    public String getCerPassword() {
        return cerPassword;
    }

    public void setCerPassword(String cerPassword) {
        this.cerPassword = cerPassword;
    }
}