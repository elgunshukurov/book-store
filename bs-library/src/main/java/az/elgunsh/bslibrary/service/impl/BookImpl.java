package az.elgunsh.bslibrary.service.impl;

import az.elgunsh.bslibrary.dao.Book;
import az.elgunsh.bslibrary.dao.Publisher;
import az.elgunsh.bslibrary.dto.BookRequestDto;
import az.elgunsh.bslibrary.dto.BookResponseDto;
import az.elgunsh.bslibrary.dto.PublisherDto;
import az.elgunsh.bslibrary.mapper.BookMapper;
import az.elgunsh.bslibrary.mapper.PublisherMapper;
import az.elgunsh.bslibrary.repo.BookRepository;
import az.elgunsh.bslibrary.repo.PublisherRepository;
import az.elgunsh.bslibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class BookImpl implements BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public BookResponseDto getBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            BookResponseDto responseDto = new BookResponseDto();
            responseDto.setAuthor(book.getAuthor());
            responseDto.setTitle(book.getTitle());
            responseDto.setIsbn(book.getIsbn());
            responseDto.setPubName(book.getPublisher().getName());
            responseDto.setPubCountry(book.getPublisher().getCountry());
            return responseDto;
//            return BookMapper.INSTANCE.toDto(optionalBook.get());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<BookResponseDto> listWithParams(HashMap<String, String> params) {
        Page<Book> page = publisherRepository.findAll(getUserWithSpec(params), makePageable(params));
        return PublisherMapper.INSTANCE.toDto(page.getContent());
    }

    private PageRequest makePageable(Map<String, String> map) {
        int page = 0;
        int size = 20;
        String sortDirection = "asc";
        String sortColumn = "id";
        log.info("{}", map);

        if (map.containsKey("page")) {
            page = Integer.parseInt(map.remove("page"));
        }
        if (map.containsKey("size")) {
            size = Integer.parseInt(map.remove("size"));
        }
        if (map.containsKey("sortDirection")) {
            sortDirection = map.remove("sortDirection");
        }
        if (map.containsKey("sortColumn")) {
            sortColumn = map.remove("sortColumn");
        }

        log.info("{} - {} - {} - {}", page, size, sortDirection, sortColumn);


        return PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortColumn);
    }

    private Specification<Book> getUserWithSpec(Map<String, String> request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            request.keySet().forEach(key -> {
                if (StringUtils.hasText(key)) {
                    predicates.add(criteriaBuilder.equal(root.get(key), request.get(key)));
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<BookResponseDto> list() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();

        for (Book book : bookList) {
            BookResponseDto responseDto = new BookResponseDto();
            responseDto.setId(book.getId());
            responseDto.setTitle(book.getTitle());
            responseDto.setIsbn(book.getIsbn());
            responseDto.setAuthor(book.getAuthor());
            responseDto.setPubCountry(book.getPublisher().getCountry());
            responseDto.setPubName(book.getPublisher().getName());
            bookResponseDtos.add(responseDto);
        }

        return bookResponseDtos;
    }

    @Override
    public BookResponseDto save(BookRequestDto bookDto) {
        return getBookFromRequestDto(bookDto);
//        return BookMapper.INSTANCE.toDto(bookRepository.save(BookMapper.INSTANCE.toEntity(bookDto)));
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto bookDto) {
        bookDto.setId(id);
        if (bookRepository.existsById(id)) {
            Book book = bookRepository.findById(id).get();
            Publisher publisher = new Publisher();
            publisher.setId(book.getPublisher().getId());
            publisher.setName(bookDto.getPubName());
            publisher.setCountry(bookDto.getPubCountry());

            publisherRepository.save(publisher);

            book.setId(bookDto.getId());
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setIsbn(bookDto.getIsbn());
            book.setPublisher(publisher);

            Book resBook = bookRepository.save(book);

            bookDto.setId(resBook.getId());
            return BookMapper.INSTANCE.toDto(bookDto);
//            return BookMapper.INSTANCE.toDto(bookRepository.save(BookMapper.INSTANCE.toEntity(book)));
        } else {
            throw new EntityNotFoundException();
        }
    }

    private BookResponseDto getBookFromRequestDto(BookRequestDto bookDto) {
        Book book = new Book();
        Publisher publisher = new Publisher();
        publisher.setName(bookDto.getPubName());
        publisher.setCountry(bookDto.getPubCountry());

        publisherRepository.save(publisher);

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPublisher(publisher);

        Book resBook = bookRepository.save(book);
        bookDto.setId(resBook.getId());
        return BookMapper.INSTANCE.toDto(bookDto);
    }

    @Override
    public void delete(Long id) {
        if (bookRepository.existsById(id)) {
            publisherRepository.deleteById(bookRepository.findById(id).get().getPublisher().getId());
            bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
