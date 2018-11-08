package org.gorzela.library.client.security;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CurrentWindow {

    private String windowName;
}
