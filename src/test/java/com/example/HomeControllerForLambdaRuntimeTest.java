package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.env.Environment;
import io.micronaut.function.aws.proxy.MockLambdaContext;
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction;
import io.micronaut.function.aws.test.MicronautLambdaJunit5Extension;
import io.micronaut.function.aws.test.annotation.MicronautLambdaTest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//UNCOMMENT this class to test when using Lambda runtime

/**
 * Tests passes when using platform version 4.4.3 but fails when using version 4.6.1.
 * <p>Mitigation action: using version 4.6.1 but forcing micronaut-servlet-core to version 4.7.0 passes again the test!</p>
 */
@Slf4j
@MicronautLambdaTest
class HomeControllerForLambdaRuntimeTest {
    private static final Context lambdaContext = new MockLambdaContext();

    @RegisterExtension
    static MicronautLambdaJunit5Extension micronautJUnit5Extension = new MicronautLambdaJunit5Extension();
    private static ApiGatewayProxyRequestEventFunction handler;

    @Inject
    Environment env;

    @Inject
    ObjectMapper objectMapper;

    @BeforeAll
    public static void setupSpec() {
        handler = new ApiGatewayProxyRequestEventFunction();
    }

    @AfterAll
    public static void cleanupSpec() {
        handler.getApplicationContext().close();
    }

    @Bean
    Context getAwsLambdaContext() {
        return new MockLambdaContext();
    }


    @BeforeEach
    void setUp() {
        assertTrue(handler.getApplicationContext().containsBean(HomeController.class));
    }


    @Test
    void testGetClaimNotFound() throws JsonProcessingException {
        String missing_claim_id = "0";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer test_Token");
        APIGatewayProxyResponseEvent response = fetch("/claims/" + missing_claim_id, "GET", null, headers);
        assertEquals(HttpStatus.NOT_FOUND.getCode(), response.getStatusCode());
        String responseBody = response.getBody();
        Map<String, Object> jsonAsMap = objectMapper.readValue(responseBody, new TypeReference<>() {
        });
        assertEquals("Not Found", jsonAsMap.get("message"));
        Map<String, Object> linksMap = (Map<String, Object>) jsonAsMap.get("_links");
        Map<String, Object> selfMap = (Map<String, Object>) linksMap.get("self");
        assertEquals("/claims/0", selfMap.get("href"));

        //assertEquals("{\"message\":\"Not Found\",\"_embedded\":{\"errors\":[{\"message\":\"Page Not Found\"}]},\"_links\":{\"self\":{\"href\":\"/claims/claim_not_found_id\",\"templated\":false}}}", responseBody);
    }

    @Test
    void testGetClaimFound() {
        String claim_id = "1";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer test_Token");
        APIGatewayProxyResponseEvent response = fetch("/claims/" + claim_id, "GET", null, headers);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
        assertEquals("{\"id\":\"1\"}", response.getBody());

        //second call
        response = fetch("/claims/1234", "GET", null, headers);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
        assertEquals("{\"id\":\"1234\",\"claimDate\":[2024,6,15],\"circumstances\":\"some description\"}", response.getBody());

    }


    private APIGatewayProxyResponseEvent fetch(String path, String method, String body, Map<String, String> headers) {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod(method);
        request.setPath(path);
        request.setHeaders(headers);
        if (body != null)
            request.setBody(body);

        return handler.handleRequest(request, lambdaContext);
    }

}
