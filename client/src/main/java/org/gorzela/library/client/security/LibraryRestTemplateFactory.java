package org.gorzela.library.client.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LibraryRestTemplateFactory {

    @Autowired
    SecureResponseErrorHandler errorHandler;

    public RestTemplate getRestTemplate(String login, String password) {

        RestTemplateBuilder builder = new RestTemplateBuilder();
        builder = builder.errorHandler(errorHandler).basicAuthorization(login, password);
        return builder.build();

    }

    public RestTemplate getRestTemplate() {

        return new RestTemplate();
    }
}
