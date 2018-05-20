package org.gorzela.library.client.security;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class Pair {

    String login;
    String password;

}
