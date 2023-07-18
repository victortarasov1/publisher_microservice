package executor.service.publisher.controller.proxysource;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.queue.QueueHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class ProxySourceControllerImpl implements ProxySourceController {
    private final QueueHandler<ProxyConfigHolderDto> proxyHandler;

    public ProxySourceControllerImpl(QueueHandler<ProxyConfigHolderDto> handler) {
        this.proxyHandler = handler;
    }

    @Override
    public ResponseEntity<?> add(ProxyConfigHolderDto proxyConfigHolderDto) {
        if (proxyConfigHolderDto != null) {
            proxyHandler.add(proxyConfigHolderDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> addAll(List<ProxyConfigHolderDto> proxyConfigHolderDtos) {
        if (proxyConfigHolderDtos != null) {
            proxyHandler.addAll(proxyConfigHolderDtos);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> poll() {
        return new ResponseEntity<>(proxyHandler.poll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> removeAll() {
        return new ResponseEntity<>(proxyHandler.removeAll(), HttpStatus.OK);
    }
}