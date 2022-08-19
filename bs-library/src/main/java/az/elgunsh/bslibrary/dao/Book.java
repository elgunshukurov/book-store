package az.elgunsh.bslibrary.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String isbn;
    private String title;
    private String author;

    @ManyToOne()
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}
