package executor.service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public interface SourceController<T> {
    @PostMapping
    void add(@RequestBody T element);

    @PostMapping( "/all")
    void addAll(@RequestBody List<T> elements);

}
