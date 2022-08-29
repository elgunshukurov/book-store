package az.elgunsh.bslibrary.controller;

import az.elgunsh.bslibrary.dto.BookRequestDto;
import az.elgunsh.bslibrary.dto.BookResponseDto;
import az.elgunsh.bslibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasAnyRole('ADMIN','PUBLISHER','USER')")
    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PUBLISHER','USER')")
    @GetMapping("/param")
    public List<BookResponseDto> getBooksWithParam(@RequestBody HashMap<String, String> params){
        return bookService.listWithParams(params);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PUBLISHER','USER')")
    @GetMapping
    public List<BookResponseDto> getBooks(){
        return bookService.list();
    }

    @PreAuthorize("hasAnyRole('ADMIN','PUBLISHER')")
    @PostMapping
    public BookResponseDto saveBook(@RequestBody BookRequestDto bookDto){
        return bookService.save(bookDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PUBLISHER')")
    @PutMapping("/{id}")
    public BookResponseDto updateBookById(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto){
        return bookService.update(id, bookRequestDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PUBLISHER')")
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.delete(id);
    }
}
