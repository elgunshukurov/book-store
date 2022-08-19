package az.elgunsh.bslibrary.service;

import az.elgunsh.bslibrary.dto.BookRequestDto;
import az.elgunsh.bslibrary.dto.BookResponseDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface BookService {
    BookResponseDto getBook(Long id);
    List<BookResponseDto> listWithParams(HashMap<String, String> params);
    List<BookResponseDto> list();
    BookResponseDto save(BookRequestDto bookDto);
    BookResponseDto update(Long id, BookRequestDto bookDto);
    void delete(Long id);
}
