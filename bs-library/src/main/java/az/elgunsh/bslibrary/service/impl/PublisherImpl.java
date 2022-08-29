package az.elgunsh.bslibrary.service.impl;

import az.elgunsh.bslibrary.dao.Book;
import az.elgunsh.bslibrary.dao.Publisher;
import az.elgunsh.bslibrary.dto.PublisherRequestDto;
import az.elgunsh.bslibrary.dto.PublisherResponseDto;
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

import static az.elgunsh.bslibrary.constants.PageCons.*;
import static az.elgunsh.bslibrary.constants.PageCons.SORT_COLUMN;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PublisherImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    @Override
    public PublisherResponseDto getPublisher(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()){
            return PublisherMapper.INSTANCE.toDto(publisherOptional.get());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<PublisherResponseDto> list() {
        return PublisherMapper.INSTANCE.toDto(publisherRepository.findAll());
    }


    @Override
    @Transactional
    public PublisherResponseDto save(PublisherRequestDto publisherDto) {
        Publisher publisher = PublisherMapper.INSTANCE.toEntity(publisherDto);
        List<Book> books = publisher.getBooks();
        publisher.setBooks(null);
        publisher = publisherRepository.save(publisher);

        for (Book book : books) {
            book.setPublisher(publisher);
        }

        publisher.setBooks(bookRepository.saveAll(books));

        return PublisherMapper.INSTANCE.toDto(publisher);
    }

    @Override
    public void addBookTo(String name, String title) {
        Publisher publisher = publisherRepository.findByName(name);
        Book book = bookRepository.findByTitle(title);
        book.setPublisher(publisher);
    }

    @Override
    public PublisherResponseDto update(Long id, PublisherRequestDto publisher) {
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
