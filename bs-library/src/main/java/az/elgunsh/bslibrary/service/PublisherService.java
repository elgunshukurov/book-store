package az.elgunsh.bslibrary.service;

import az.elgunsh.bslibrary.dao.Book;
import az.elgunsh.bslibrary.dao.Publisher;
import az.elgunsh.bslibrary.dto.PublisherDto;
import az.elgunsh.bslibrary.dto.PublisherResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PublisherService {
    PublisherDto getPublisher(Long id);
    List<PublisherDto> list();
    List<PublisherDto> listWithParams(Map<String, String> map);
    PublisherDto save(PublisherDto publisher);
    void addBookTo(String name, String title);
    PublisherDto update(Long id, PublisherDto publisher);
    void delete(Long id);
}
