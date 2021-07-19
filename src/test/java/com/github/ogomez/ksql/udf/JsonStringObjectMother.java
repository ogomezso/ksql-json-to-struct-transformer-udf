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

public class JsonStringObjectMother {

  public static String JSON_STRING_DOCUMENT = "{\n"
      + "  \"string\": \"string\",\n"
      + "  \"boolean\": true,\n"
      + "  \"integer\": 1,\n"
      + "  \"float\": 0.55,\n"
      + "  \"objectsArray\": {\n"
      + "    \"objects\": [\n"
      + "      {\n"
      + "        \"objectStringKey\": \"String\",\n"
      + "        \"objectBooleanKey\": true,\n"
      + "        \"objectIntegerKey\": 1,\n"
      + "        \"objectFloat Key\": 0.55\n"
      + "      },\n"
      + "      {\n"
      + "        \"objectStringKey\": \"String\",\n"
      + "        \"objectBooleanKey\": true,\n"
      + "        \"objectIntegerKey\": 1,\n"
      + "        \"objectFloat Key\": 0.55\n"
      + "      },\n"
      + "      {\n"
      + "        \"objectStringKey\": \"String\",\n"
      + "        \"objectBooleanKey\": true,\n"
      + "        \"objectIntegerKey\": 1,\n"
      + "        \"objectFloat Key\": 0.55\n"
      + "      }\n"
      + "    ]\n"
      + "  },\n"
      + "  \"stringValuesArray\": [ \"id\", \"5001\", \"None\"],\n"
      + "  \"booleanValuesArray\": [ true, false],\n"
      + "  \"integerValuesArray\": [1,2,3],\n"
      + "  \"floatValuesArray\": [0.55,0.12,0.04],\n"
      + "  \"\" : \"noKeyValue\",\n"
      + "  \"nullvalueKey\": null\n"
      + "}";

  public static String MALFORMED_JSON_STRING = "{\n"
      + "  \"string\": \"string\",\n"
      + "  \"boolean\": true,\n"
      + "  \"integer\": 1,\n"
      + "  \"float\": 0.55,\n"
      + "  \"objectsArray\": {\n"
      + "    \"objects\": [\n";

  public static String JSON_SIMPLE_FIELDS = "{\n"
      + "  \"string\": \"string\",\n"
      + "  \"boolean\": true,\n"
      + "  \"integer\": 1,\n"
      + "  \"float\": 0.55\n"
      + "}";

  public static String JSON_ARRAY_FIELD = "{\n"
      + "  \"stringValuesArray\": [ \"id\", \"5001\", \"None\"],\n"
      + "  \"booleanValuesArray\": [ true, false],\n"
      + "  \"integerValuesArray\": [1,2,3],\n"
      + "  \"floatValuesArray\": [0.55,0.12,0.04]\n"
      + "}";

  public static String JSON_OBJECT_ARRAY = "{\n"
      + "  \"objectsArray\": {\n"
      + "    \"objects\": [\n"
      + "      {\n"
      + "        \"objectStringKey\": \"String\",\n"
      + "        \"objectBooleanKey\": true,\n"
      + "        \"objectIntegerKey\": 1,\n"
      + "        \"objectFloatKey\": 0.55\n"
      + "      },\n"
      + "      {\n"
      + "        \"objectStringKey\": \"String\",\n"
      + "        \"objectBooleanKey\": true,\n"
      + "        \"objectIntegerKey\": 1,\n"
      + "        \"objectFloatKey\": 0.55\n"
      + "      },\n"
      + "      {\n"
      + "        \"objectStringKey\": \"String\",\n"
      + "        \"objectBooleanKey\": true,\n"
      + "        \"objectIntegerKey\": 1,\n"
      + "        \"objectFloatKey\": 0.55\n"
      + "      }\n"
      + "    ]\n"
      + "  }}";

  public static String JSON_OBJECT_OF_OBJECTS = "{\n"
      + "  \"objectsArray\": {\n"
      + "    \"objects\": {\n"
      + "      \"object1\": {\n"
      + "         \"stringValuesArray\": [ \"id\", \"5001\", \"None\"],\n"
      + "         \"objectIntegerKey\": 1\n"
      + "      },\n"
      + "      \"object2\": {\n"
      + "        \"objectStringKey\": \"String\",\n"
      + "        \"booleanValuesArray\": [ true, false]\n"
      + "      },\n"
      + "      \"object3\": {\n"
      + "        \"integerValuesArray\": [1,2,3],\n"
      + "        \"objectFloatKey\": 0.55\n"
      + "      },\n"
      + "      \"object4\": {\n"
      + "         \"floatValuesArray\": [0.55,0.12,0.04],\n"
      + "          \"objectBooleanKey\": false\n"
      + "      }\n"
      + "    }\n"
      + "  }}";

}
