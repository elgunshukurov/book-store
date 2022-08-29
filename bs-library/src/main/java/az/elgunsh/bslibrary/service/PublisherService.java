package az.elgunsh.bslibrary.service;

import az.elgunsh.bslibrary.dto.PublisherRequestDto;
import az.elgunsh.bslibrary.dto.PublisherResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PublisherService {
    PublisherResponseDto getPublisher(Long id);
    List<PublisherResponseDto> list();
    PublisherResponseDto save(PublisherRequestDto publisher);
    void addBookTo(String name, String title);
    PublisherResponseDto update(Long id, PublisherRequestDto publisher);
    void delete(Long id);
}
