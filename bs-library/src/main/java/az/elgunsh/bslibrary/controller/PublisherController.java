package az.elgunsh.bslibrary.controller;

import az.elgunsh.bslibrary.dto.PublisherDto;
import az.elgunsh.bslibrary.dto.PublisherResponseDto;
import az.elgunsh.bslibrary.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping("/{id}")
    public PublisherDto getPublisher(@PathVariable Long id) {
        return publisherService.getPublisher(id);
    }

    @GetMapping("/param")
    public List<PublisherDto> listWithParams(@RequestBody Map<String, String> map){
        return publisherService.listWithParams(map);
    }

    @GetMapping
    public List<PublisherDto> list(){
        return publisherService.list();
    }

    @PostMapping("/save")
    public PublisherDto save(@RequestBody PublisherDto publisher) {
        return publisherService.save(publisher);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping()
    public void addBookTo(@RequestParam String name, @RequestParam String title) {
        publisherService.addBookTo(name, title);
    }

    @PutMapping("/{id}")
    public PublisherDto update(@PathVariable Long id, @RequestBody PublisherDto publisher){
        return publisherService.update(id, publisher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        publisherService.delete(id);
    }
}
