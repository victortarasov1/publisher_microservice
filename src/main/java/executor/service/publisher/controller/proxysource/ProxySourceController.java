package executor.service.publisher.controller.proxysource;

import executor.service.publisher.model.ProxyConfigHolderDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public interface ProxySourceController {
    @PostMapping("/proxy")
    ResponseEntity<?> add(@RequestBody ProxyConfigHolderDto proxyConfigHolderDto);

    @PostMapping("/proxies")
    ResponseEntity<?> addAll(@RequestBody List<ProxyConfigHolderDto> proxyConfigHolderDtos);

//    @DeleteMapping(value = "/proxy", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/proxy")
    ResponseEntity<?> poll();

    @DeleteMapping("/proxies")
    ResponseEntity<?> removeAll();
}
