package executor.service.processing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface RemoteSourceController<T> {
    @PostMapping
    void loadFromDefaultRemoteSource();

    @PostMapping("/custom")
    void loadFromCustomRemoteSource(@RequestBody T source);
}
