package az.elgunsh.bslibrary.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookRequestDto {
    private Long id;
    private String author;
    private String isbn;
    private String title;
    private Long pubId;
}
