package org.gorzela.library.client.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gorzela.library.common.Reader;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CurrentReaderProvider {

    private Reader loggedInReader;

    public Reader checkCurrentReaderProvider(){

        return getLoggedInReader();
    }
}
