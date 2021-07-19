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

package com.github.ogomez.ksql.udf;


import org.apache.kafka.connect.data.Schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.ogomez.ksql.schemabuilder.JsonToStructBuilder;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(
    name = "Format Converter",
    description = "UDF that JSON or STRING values to AVRO with Schema",
    version = "1.0-SNAPSHOT",
    author = "ogomezso0@gmail.com"
)
public class FormatConverterUdf {

  @Udf(description = "Transform from Json String to Struct Schema")
  public Schema jsonToStruct(
      @UdfParameter(value = "root", description = "Root field name value") final String root,
      @UdfParameter(value = "jsonString", description = "Json Formated String to transform to Struct Schema") final String jsonString
  ) throws JsonProcessingException {
    return JsonToStructBuilder.inferDocumentSchema(root, jsonString);
  }
}
