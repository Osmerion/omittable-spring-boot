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
package com.osmerion.omittable.spring.boot.web;

import com.example.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@AutoConfigureRestTestClient
@SpringBootTest(
    classes = Main.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public final class SpringIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void testHandlerMethodArgumentResolver() {
        this.assertResponse("/person", "Omittable.absent");

        this.assertResponse("/person?name=Karl", "Omittable[Karl]");

        this.assertResponse(
            "/person/complex-type?myId=d3a33656-3fb4-4430-8103-b7c60f018eb4",
            "Omittable[d3a33656-3fb4-4430-8103-b7c60f018eb4] - UUID"
        );

        this.assertResponse("/person/complex-type?myId", "Omittable[null] - null");
    }

    private void assertResponse(String uri, String expectedBody) {
        this.restTestClient.get()
            .uri(uri)
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .isEqualTo(expectedBody);
    }

}
