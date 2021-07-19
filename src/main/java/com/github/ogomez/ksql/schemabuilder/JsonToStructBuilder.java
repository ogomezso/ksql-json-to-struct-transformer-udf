/*
 *  Copyright © 2021 Oscar Gómez (ogomezso0@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.ogomez.ksql.schemabuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToStructBuilder {

  private static final String DEFAULT_KEY_VALUE = "value";

  public static Schema inferDocumentSchema(final String fieldPath, final String document)
      throws JsonProcessingException {
    return createSchemaBuilder(fieldPath, document).optional().build();
  }

  private static SchemaBuilder createSchemaBuilder(
      final String fieldPath, final String document) throws JsonProcessingException {

    final ObjectMapper mapper = new ObjectMapper();

    Map<String, Object> treeMap = mapper
        .readValue(document, new TypeReference<Map<String, Object>>() {
        });

    return createSchemaBuilder(fieldPath, treeMap);
  }

  private static SchemaBuilder createSchemaBuilder(
      final String fieldPath, Map<String, Object> treeMap) {
    SchemaBuilder builder = SchemaBuilder.struct();
    builder.name(fieldPath);

    treeMap.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(kv -> {
          String key = Optional.ofNullable(kv.getKey())
              .filter(k -> !k.isEmpty())
              .orElse(DEFAULT_KEY_VALUE);
          builder.field(
              key,
              inferSchema(kv.getKey(), kv.getValue()));
        });
    return builder;
  }

  private static Schema inferSchema(String key, Object value) {
    if (value != null) {
      if (Boolean.class.equals(value.getClass())) {
        return Schema.OPTIONAL_BOOLEAN_SCHEMA;
      } else if (Integer.class.equals(value.getClass())) {
        return Schema.OPTIONAL_INT32_SCHEMA;
      } else if (Double.class.equals(value.getClass())) {
        return Schema.OPTIONAL_FLOAT64_SCHEMA;
      } else if (LinkedHashMap.class.equals(value.getClass())) {
        return createSchemaBuilder(key, (LinkedHashMap<String, Object>) value);
      } else if (ArrayList.class.equals(value.getClass())) {
        if (!((ArrayList<?>) value).isEmpty()) {
          if (LinkedHashMap.class.equals(((ArrayList<?>) value).get(0).getClass())) {
            return createSchemaBuilder(key,
                (LinkedHashMap<String, Object>) ((ArrayList<?>) value).get(0));
          }
          return SchemaBuilder.array(inferSchema(key, ((ArrayList<?>) value).get(0)))
              .name(key)
              .optional()
              .build();
        }
        return Schema.OPTIONAL_BOOLEAN_SCHEMA;
      }
    }
    return Schema.OPTIONAL_STRING_SCHEMA;
  }

}
