package az.elgunsh.bslibrary.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PublisherResponseDto {
    private Long id;
    private String title;
    private String name;
    private String country;
    private String bookId;
    private String isbn;
}
