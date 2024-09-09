package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//UNCOMMENT this class to test when using Netty runtime
/*
@MicronautTest
class HomeControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void getClaimNotFound() {
        UriTemplate uriTemplate = UriTemplate.of("claims/{id}");
        String expanded = uriTemplate.expand(Map.of("id", "0"));
        BlockingHttpClient blockingClient = client.toBlocking();
        assertThrows(HttpClientResponseException.class, () -> blockingClient.exchange(expanded));
    }

    @Test
    void getClaimShouldReturnSomething() {
        UriTemplate uriTemplate = UriTemplate.of("claims/{id}");
        String expanded = uriTemplate.expand(Map.of("id", "1234"));
        String s = client.toBlocking().retrieve(expanded);
        System.out.println("s = " + s);
        assertEquals("{\"id\":\"1234\",\"claimDate\":[2024,6,15],\"circumstances\":\"some description\"}", s);

    }
}*/
