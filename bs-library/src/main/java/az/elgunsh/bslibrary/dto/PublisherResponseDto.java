package az.elgunsh.bslibrary.dto;

import az.elgunsh.bslibrary.dao.Book;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class PublisherResponseDto {
    private Long id;
    private String name;
    private String country;
    private List<Book> books;

}
