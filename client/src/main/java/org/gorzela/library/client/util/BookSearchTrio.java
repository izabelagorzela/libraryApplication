package org.gorzela.library.client.util;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class BookSearchTrio {

    String phrasePart;
    String phraseType;
    Long selectedBookId;
}
