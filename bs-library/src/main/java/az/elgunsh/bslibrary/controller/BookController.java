package az.elgunsh.bslibrary.controller;

import az.elgunsh.bslibrary.dto.BookRequestDto;
import az.elgunsh.bslibrary.dto.BookResponseDto;
import az.elgunsh.bslibrary.dto.PublisherDto;
import az.elgunsh.bslibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @GetMapping("/param")
    public List<BookResponseDto> getBooksWithParam(@RequestBody HashMap<String, String> params){
        return bookService.listWithParams(params);
    }

    @GetMapping
    public List<BookResponseDto> getBooks(){
        return bookService.list();
    }

    @PostMapping
    public BookResponseDto saveBook(@RequestBody BookRequestDto bookDto){
        return bookService.save(bookDto);
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBookById(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto){
        System.out.println(" id => " + id + " || reqDto => " + bookRequestDto);
        return bookService.update(id, bookRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.delete(id);
    }
}
