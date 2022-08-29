package az.elgunsh.bslibrary.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookDto {

    private Long id;
    private String isbn;
    private String title;
    private String author;
}
