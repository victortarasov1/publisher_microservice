package executor.service.publisher.controller;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProxySourceControllerImpl implements ProxySourceController {
    private final QueueHandler<ProxyConfigHolderDto> proxyHandler;

    public ProxySourceControllerImpl(QueueHandler<ProxyConfigHolderDto> handler) {
        this.proxyHandler = handler;
    }

    @Override
    public ResponseEntity<ProxyConfigHolderDto> add(ProxyConfigHolderDto proxyConfigHolderDto) {
        if (proxyConfigHolderDto != null) {
            proxyHandler.add(proxyConfigHolderDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProxyConfigHolderDto>> addAll(List<ProxyConfigHolderDto> proxyConfigHolderDtos) {
        if (proxyConfigHolderDtos != null) {
            proxyHandler.addAll(proxyConfigHolderDtos);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Optional<ProxyConfigHolderDto>> poll() {
        return new ResponseEntity<>(proxyHandler.poll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProxyConfigHolderDto>> removeAll() {
        List<ProxyConfigHolderDto> removedProxies = proxyHandler.removeAll();
        if (!removedProxies.isEmpty()) {
            return new ResponseEntity<>(removedProxies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}