package az.elgunsh.bslibrary.mapper;

import az.elgunsh.bslibrary.dao.Book;
import az.elgunsh.bslibrary.dto.BookDto;
import az.elgunsh.bslibrary.dto.BookRequestDto;
import az.elgunsh.bslibrary.dto.BookResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);;

    @Mappings(
            value = {
                    @Mapping(source = "pubName", target = "publisher.name"),
                    @Mapping(source = "pubCountry", target = "publisher.country")
            }
    )
    Book toEntity(BookRequestDto source);

    @Mappings(
            value = {
                    @Mapping(source = "pubName", target = "publisher.name"),
                    @Mapping(source = "pubCountry", target = "publisher.country")
            }
    )
    List<Book> toEntity(List<BookRequestDto> source);

    @Mappings(
            value = {
                    @Mapping(source = "publisher.name", target = "pubName"),
                    @Mapping(source = "publisher.country", target = "pubCountry")
            }
    )
    BookResponseDto toDto(Book source);


    @Mappings(
            value = {
                    @Mapping(source = "publisher.name", target = "pubName"),
                    @Mapping(source = "publisher.country", target = "pubCountry")
            }
    )
    List<BookResponseDto> toDto(List<Book> books);

}