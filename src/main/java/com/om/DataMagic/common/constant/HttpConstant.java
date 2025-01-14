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

package com.om.DataMagic.common.constant;

public final class HttpConstant {
    // Private constructor to prevent instantiation of the HttpConstant class
    private HttpConstant() {
        // private constructor to hide the implicit public one
        throw new AssertionError("HttpConstant class cannot be instantiated.");
    }

    /**
     * Timeout duration in milliseconds.
     */
    public static final int TIME_OUT = 6000;

    /**
     * Token key.
     */
    public static final String TOKEN = "token";

    /**
     * Content type key.
     */
    public static final String CONTENT_TYPE = "Content-Type";

}
