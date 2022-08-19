package az.elgunsh.bslibrary.service.impl;

import az.elgunsh.bslibrary.dao.Book;
import az.elgunsh.bslibrary.dao.Publisher;
import az.elgunsh.bslibrary.dto.PublisherDto;
import az.elgunsh.bslibrary.mapper.BookMapper;
import az.elgunsh.bslibrary.mapper.PublisherMapper;
import az.elgunsh.bslibrary.repo.BookRepository;
import az.elgunsh.bslibrary.repo.PublisherRepository;
import az.elgunsh.bslibrary.service.PublisherService;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PublisherImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    @Override
    public PublisherDto getPublisher(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()){
            return PublisherMapper.INSTANCE.toDto(publisherOptional.get());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<PublisherDto> list() {
        return PublisherMapper.INSTANCE.toDto(publisherRepository.findAll());
    }

    @Override
    public List<PublisherDto> listWithParams(Map<String, String> map) {
        Page<Publisher> page = publisherRepository.findAll(getUserWithSpec(map), makePageable(map));
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

    private Specification<Publisher> getUserWithSpec(Map<String, String> request) {
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
    @Transactional
    public PublisherDto save(PublisherDto publisherDto) {

        Publisher publisher = PublisherMapper.INSTANCE.toEntity(publisherDto);
//        publisher.setBooks(null);
        publisher = publisherRepository.save(publisher);

        List<Book> books = BookMapper.INSTANCE.toEntity(publisherDto.getBooks());
        List<Book> savedBooks = new ArrayList<>();
        for (Book book:books){
            book.setPublisher(publisher);
            savedBooks.add(bookRepository.save(book));
        }
//         publisher.setBooks(bookRepository.saveAll(books));
        publisher.setBooks(savedBooks);

        return PublisherMapper.INSTANCE.toDto(publisher);
    }

    @Override
    public void addBookTo(String name, String title) {
        Publisher publisher = publisherRepository.findByName(name);
        Book book = bookRepository.findByTitle(title);
//        publisher.getBooks().add(book);
        book.setPublisher(publisher);
    }

    @Override
    public PublisherDto update(Long id, PublisherDto publisher) {
        publisher.setId(id);
        if (publisherRepository.existsById(id)) {
            return PublisherMapper.INSTANCE.toDto(publisherRepository.save(PublisherMapper.INSTANCE.toEntity(publisher)));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        if (publisherRepository.existsById(id)) {
            publisherRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
