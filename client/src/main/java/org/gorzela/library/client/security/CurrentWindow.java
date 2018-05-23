package org.gorzela.library.client.security;

import de.felixroske.jfxsupport.AbstractFxmlView;
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
