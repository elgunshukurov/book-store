package az.elgunsh.bslibrary.service.impl;

import az.elgunsh.bslibrary.dao.Book;
import az.elgunsh.bslibrary.dto.BookRequestDto;
import az.elgunsh.bslibrary.dto.BookResponseDto;
import az.elgunsh.bslibrary.mapper.BookMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static az.elgunsh.bslibrary.constants.PageCons.*;

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
            return BookMapper.INSTANCE.toDto(optionalBook.get());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<BookResponseDto> listWithParams(HashMap<String, String> params) {
        Page<Book> page = bookRepository.findAll(getBookWithSpec(params), makePageable(params));
        return BookMapper.INSTANCE.toDto(page.getContent());
    }

    private PageRequest makePageable(Map<String, String> map) {
        if (map.containsKey("page")) {
            PAGE_NUMBER = Integer.parseInt(map.remove("page"));
        }
        if (map.containsKey("size")) {
            PAGE_SIZE = Integer.parseInt(map.remove("size"));
        }
        if (map.containsKey("sortDirection")) {
            SORT_DIRECTION = map.remove("sortDirection");
        }
        if (map.containsKey("sortColumn")) {
            SORT_COLUMN = map.remove("sortColumn");
        }

        return PageRequest.of(PAGE_NUMBER, PAGE_SIZE, Sort.Direction.fromString(SORT_DIRECTION), SORT_COLUMN);
    }

    private Specification<Book> getBookWithSpec(Map<String, String> request) {
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
        return BookMapper.INSTANCE.toDto(bookRepository.findAll());
    }

    @Override
    public BookResponseDto save(BookRequestDto bookDto) {
        return BookMapper.INSTANCE.toDto(bookRepository.save(BookMapper.INSTANCE.toEntity(bookDto)));
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto bookDto) {
        bookDto.setId(id);
        if (bookRepository.existsById(id)) {
            return BookMapper.INSTANCE.toDto(bookRepository.save(BookMapper.INSTANCE.toEntity(bookDto)));
        } else {
            throw new EntityNotFoundException();
        }
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
