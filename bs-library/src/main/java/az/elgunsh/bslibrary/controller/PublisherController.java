package az.elgunsh.bslibrary.controller;

import az.elgunsh.bslibrary.dto.PublisherRequestDto;
import az.elgunsh.bslibrary.dto.PublisherResponseDto;
import az.elgunsh.bslibrary.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @PreAuthorize("hasAnyRole('ADMIN','PUBLISHER')")
    @GetMapping("/{id}")
    public PublisherResponseDto getPublisherBookById(@PathVariable Long id) {
        return publisherService.getPublisher(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<PublisherResponseDto> getPublishers(){
        return publisherService.list();
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/save")
    public PublisherResponseDto save(@RequestBody PublisherRequestDto publisher) {
        return publisherService.save(publisher);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping()
    public void addBookTo(@RequestParam String name, @RequestParam String title) {
        publisherService.addBookTo(name, title);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PUBLISHER')")
    @PutMapping("/{id}")
    public PublisherResponseDto updateBookById(@PathVariable Long id, @RequestBody PublisherRequestDto publisher){
        return publisherService.update(id, publisher);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        publisherService.delete(id);
    }
}
