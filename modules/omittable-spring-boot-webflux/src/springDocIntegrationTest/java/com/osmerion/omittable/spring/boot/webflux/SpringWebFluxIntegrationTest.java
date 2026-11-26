/*
 * Copyright 2025-2026 Leon Linhart
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.osmerion.omittable.spring.boot.webflux;

import com.example.Main;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(
    classes = Main.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public final class SpringWebFluxIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private JsonMapper jsonMapper;

    private RestTestClient restTestClient;

    @BeforeEach
    public void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
            .baseUrl("http://localhost:" + this.port)
            .build();
    }

    @Test
    public void testSpringConfiguration() {
        String response = this.restTestClient.get()
            .uri("/v3/api-docs")
            .exchange()
            .returnResult(String.class)
            .getResponseBody();

        JsonNode node = this.jsonMapper.readTree(response);
        String prettyJson = Json.pretty().writeValueAsString(node).replace("\r", "");

        assertThat(prettyJson)
            .isEqualTo(
                """
                {
                  "openapi" : "3.1.0",
                  "servers" : [ {
                    "url" : "http://localhost:%s",
                    "description" : "Generated server url"
                  } ],
                  "paths" : {
                    "/person" : {
                      "get" : {
                        "tags" : [ "person-controller" ],
                        "operationId" : "foo",
                        "parameters" : [ {
                          "name" : "required",
                          "in" : "query",
                          "required" : true,
                          "schema" : {
                            "type" : "string"
                          }
                        }, {
                          "name" : "omittable",
                          "in" : "query",
                          "required" : false,
                          "schema" : {
                            "type" : [ "string", "null" ]
                          }
                        } ],
                        "responses" : {
                          "200" : {
                            "description" : "OK",
                            "content" : {
                              "*/*" : {
                                "schema" : {
                                  "type" : "string"
                                }
                              }
                            }
                          }
                        }
                      },
                      "patch" : {
                        "tags" : [ "person-controller" ],
                        "operationId" : "patchPerson",
                        "parameters" : [ {
                          "name" : "arg0",
                          "in" : "query",
                          "required" : true,
                          "schema" : {
                            "$ref" : "#/components/schemas/PersonUpdate"
                          }
                        } ],
                        "responses" : {
                          "200" : {
                            "description" : "OK"
                          }
                        }
                      }
                    }
                  },
                  "components" : {
                    "schemas" : {
                      "PersonUpdate" : {
                        "type" : "object",
                        "properties" : {
                          "name" : {
                            "type" : "string"
                          },
                          "required" : {
                            "type" : "string"
                          },
                          "requiredNullable" : {
                            "type" : "string"
                          },
                          "nullable" : {
                            "type" : "string",
                            "format" : "uuid"
                          }
                        },
                        "required" : [ "required", "requiredNullable" ]
                      }
                    }
                  }
                }""",
                this.port
            );
    }

}
