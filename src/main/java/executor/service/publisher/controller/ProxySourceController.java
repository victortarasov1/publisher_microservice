package executor.service.publisher.controller;

import executor.service.publisher.model.ProxyConfigHolderDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publisher")
@PreAuthorize("isAuthenticated()")
public interface ProxySourceController {
    @PostMapping(value = "/proxy", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProxyConfigHolderDto> add(@RequestBody ProxyConfigHolderDto proxyConfigHolderDto);

    @PostMapping( value ="/proxies", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProxyConfigHolderDto>> addAll(@RequestBody List<ProxyConfigHolderDto> proxyConfigHolderDtos);

    @DeleteMapping("/proxy")
    ResponseEntity<Optional<ProxyConfigHolderDto>> poll();

    @DeleteMapping( "/proxies")
    ResponseEntity<List<ProxyConfigHolderDto>> removeAll();
}
