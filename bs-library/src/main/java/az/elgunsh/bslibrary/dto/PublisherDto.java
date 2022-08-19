package az.elgunsh.bslibrary.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class PublisherDto {

    private Long id;
    private String name;
    private String country;
    private List<BookDto> books;
}
