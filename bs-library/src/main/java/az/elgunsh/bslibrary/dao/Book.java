package az.elgunsh.bslibrary.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("ALL")
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
