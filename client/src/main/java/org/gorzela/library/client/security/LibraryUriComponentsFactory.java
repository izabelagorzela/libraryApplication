package org.gorzela.library.client.security;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;

import java.net.URI;
import java.net.URISyntaxException;

import static org.gorzela.library.client.util.Constants.HOST;
import static org.gorzela.library.client.util.Constants.PATH;
import static org.gorzela.library.client.util.Constants.PROTOCOL;

@Component
public class LibraryUriComponentsFactory {

    public URI getUri(String path) throws URISyntaxException{

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(PROTOCOL);
        uriBuilder.setHost(HOST);
        uriBuilder.setPath(PATH + path);    //"/reader/get/one"
        return uriBuilder.build();
    }

    public URI getUri(String path, String... parameters) throws URISyntaxException {


        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(PROTOCOL);
        uriBuilder.setHost(HOST);
        uriBuilder.setPath(PATH + path);
        for (int i = 0; i < parameters.length; i = i + 2) {
            uriBuilder.addParameter(parameters[i], parameters[i + 1]);
        }
        return uriBuilder.build();
    }


}
