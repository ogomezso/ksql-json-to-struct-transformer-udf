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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Schema.Type;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

class FormatConverterUdfTest {

  private static final String INTEGER_VALUES_ARRAY = "integerValuesArray";
  private static final String OBJECTS_ARRAY = "objectsArray";
  private static final String OBJECTS = "objects";
  private static final String STRING_VALUES_ARRAY = "stringValuesArray";
  private static final String BOOLEAN_VALUES_ARRAY = "booleanValuesArray";
  private static final String FLOAT_VALUES_ARRAY = "floatValuesArray";
  private static final String OBJECT_STRING_KEY = "objectStringKey";
  private static final String OBJECT_BOOLEAN_KEY = "objectBooleanKey";
  private static final String OBJECT_INTEGER_KEY = "objectIntegerKey";
  private static final String OBJECT_FLOAT_KEY = "objectFloatKey";
  private static final String OBJECT_1 = "object1";
  private static final String OBJECT_2 = "object2";
  private static final String OBJECT_3 = "object3";
  private static final String OBJECT_4 = "object4";
  FormatConverterUdf classToTest = new FormatConverterUdf();

  @Test
  public void given_a_complete_json_string_when_jsonToStruct_then_struct_returned()
      throws JsonProcessingException {

    Schema actual = assertDoesNotThrow(
        () -> classToTest.jsonToStruct("root", JsonStringObjectMother.JSON_STRING_DOCUMENT));
    assertEquals(11, actual.fields().size());
  }

  @Test
  public void given_simple_objects_json_when_jsonToStruct_then_fields_has_proper_type_returned()
      throws JsonProcessingException {
    Schema actual = classToTest.jsonToStruct("root", JsonStringObjectMother.JSON_SIMPLE_FIELDS);

    assertEquals(Schema.OPTIONAL_STRING_SCHEMA.type(), actual.field("string").schema().type());
    assertEquals(Schema.OPTIONAL_BOOLEAN_SCHEMA.type(), actual.field("boolean").schema().type());
    assertEquals(Schema.OPTIONAL_INT32_SCHEMA.type(), actual.field("integer").schema().type());
    assertEquals(Schema.OPTIONAL_FLOAT64_SCHEMA.type(), actual.field("float").schema().type());
  }

  @Test
  public void given_json_array_when_jsonToStruct_then_array_type_field_returned()
      throws JsonProcessingException {
    Schema actual = classToTest.jsonToStruct("root", JsonStringObjectMother.JSON_ARRAY_FIELD);

    assertEquals(Type.ARRAY,
        actual.field(STRING_VALUES_ARRAY).schema().type());
    assertEquals(Schema.OPTIONAL_STRING_SCHEMA.type(), actual.field(STRING_VALUES_ARRAY)
        .schema().valueSchema().type());
    assertEquals(Type.ARRAY,
        actual.field(BOOLEAN_VALUES_ARRAY).schema().type());
    assertEquals(Schema.OPTIONAL_BOOLEAN_SCHEMA.type(), actual.field(BOOLEAN_VALUES_ARRAY)
        .schema().valueSchema().type());
    assertEquals(Type.ARRAY,
        actual.field(INTEGER_VALUES_ARRAY).schema().type());
    assertEquals(Schema.OPTIONAL_INT32_SCHEMA.type(), actual.field(INTEGER_VALUES_ARRAY)
        .schema().valueSchema().type());
    assertEquals(Type.ARRAY,
        actual.field(FLOAT_VALUES_ARRAY).schema().type());
    assertEquals(Schema.OPTIONAL_FLOAT64_SCHEMA.type(), actual.field(FLOAT_VALUES_ARRAY)
        .schema().valueSchema().type());
  }

  @Test
  public void given_object_array_json_when_jsonToStruct_then_array_type_field_returned()
      throws JsonProcessingException {
    Schema actual = classToTest.jsonToStruct("root", JsonStringObjectMother.JSON_OBJECT_ARRAY);

    assertEquals(Type.STRUCT,
        actual.field(OBJECTS_ARRAY).schema().type());
    assertEquals(Type.STRUCT,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().type());
    assertEquals(Schema.OPTIONAL_STRING_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_STRING_KEY)
            .schema().type());
    assertEquals(Schema.OPTIONAL_BOOLEAN_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_BOOLEAN_KEY)
        .schema().type());
    assertEquals(Schema.OPTIONAL_INT32_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_INTEGER_KEY)
        .schema().type());
    assertEquals(Schema.OPTIONAL_FLOAT64_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_FLOAT_KEY)
        .schema().type());
  }

  @Test
  public void given_object_of_objects_json_when_jsonToStruct_then_array_type_field_returned()
      throws JsonProcessingException {
    Schema actual = classToTest.jsonToStruct("root", JsonStringObjectMother.JSON_OBJECT_OF_OBJECTS);

    assertEquals(Type.STRUCT,
        actual.field(OBJECTS_ARRAY).schema().type());
    assertEquals(Type.STRUCT,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().type());
    assertEquals(Type.STRUCT,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_1)
        .schema().type());
    assertEquals(Type.ARRAY,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_1)
        .schema().field(STRING_VALUES_ARRAY).schema().type());
    assertEquals(Schema.OPTIONAL_STRING_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_1)
            .schema().field(STRING_VALUES_ARRAY).schema().valueSchema().type());
    assertEquals(Schema.OPTIONAL_INT32_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_1)
            .schema().field(OBJECT_INTEGER_KEY).schema().type());
    assertEquals(Type.STRUCT,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_2)
            .schema().type());
    assertEquals(Type.ARRAY,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_2)
            .schema().field(BOOLEAN_VALUES_ARRAY).schema().type());
    assertEquals(Schema.OPTIONAL_BOOLEAN_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_2)
            .schema().field(BOOLEAN_VALUES_ARRAY).schema().valueSchema().type());
    assertEquals(Schema.OPTIONAL_STRING_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_2)
            .schema().field(OBJECT_STRING_KEY).schema().type());
    assertEquals(Type.STRUCT,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_3)
            .schema().type());
    assertEquals(Type.ARRAY,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_3)
            .schema().field(INTEGER_VALUES_ARRAY).schema().type());
    assertEquals(Schema.OPTIONAL_INT32_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_3)
            .schema().field(INTEGER_VALUES_ARRAY).schema().valueSchema().type());
    assertEquals(Schema.OPTIONAL_FLOAT64_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_3)
            .schema().field(OBJECT_FLOAT_KEY).schema().type());
    assertEquals(Type.STRUCT,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_4)
            .schema().type());
    assertEquals(Type.ARRAY,
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_4)
            .schema().field(FLOAT_VALUES_ARRAY).schema().type());
    assertEquals(Schema.OPTIONAL_FLOAT64_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_4)
            .schema().field(FLOAT_VALUES_ARRAY).schema().valueSchema().type());
    assertEquals(Schema.OPTIONAL_BOOLEAN_SCHEMA.type(),
        actual.field(OBJECTS_ARRAY).schema().field(OBJECTS).schema().field(OBJECT_4)
            .schema().field(OBJECT_BOOLEAN_KEY).schema().type());
  }
  @Test
  public void given_malformed_json_string_when_jsonToStruct_then_throws_Json_Exception()
      throws JsonProcessingException {
    assertThrows(JsonProcessingException.class,
        () -> classToTest.jsonToStruct("root", JsonStringObjectMother.MALFORMED_JSON_STRING));
  }
}