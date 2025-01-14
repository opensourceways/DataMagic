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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectMapperUtil {
    // Private constructor to prevent instantiation of the utility class
    private ObjectMapperUtil() {
        // private constructor to hide the implicit public one
        throw new AssertionError("ObjectMapperUtil class cannot be instantiated.");
    }
    /**
     * ObjectMapper instance for JSON serialization and deserialization.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert an object to its JSON representation as a string.
     *
     * @param obj The object to convert
     * @return The JSON representation of the object as a string
     */
    public static String writeValueAsString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException("Convert to Json string error");
        }
    }

    /**
     * Convert a content to its JSON representation.
     *
     * @param content The content to convert to JsonNode
     * @return JsonNode representing the input content
     */
    public static JsonNode toJsonNode(final String content) {
        try {
            return objectMapper.readTree(content);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException("Convert to JsonNode error");
        }
    }

    /**
     * Convert a content to object.
     *
     * @param clazz The class of object
     * @param content The content to convert to JsonNode
     * @return JsonNode representing the input content
     */
    public static <T> T toObject(Class<T> clazz, String content) {
        T obj = null;
        try {
            if (content == null) {
                return null;
            }
            obj = objectMapper.readValue(content, clazz);
            return obj;

        } catch (Exception var4) {
            throw new RuntimeException("Convert to object error");
        }
    }
}

