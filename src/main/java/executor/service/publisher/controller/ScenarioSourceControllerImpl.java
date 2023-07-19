package executor.service.publisher.controller;

import executor.service.publisher.model.ScenarioDto;
import executor.service.publisher.queue.QueueHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class ScenarioSourceControllerImpl implements ScenarioSourceController {

    private final QueueHandler<ScenarioDto> handler;

    @Override
    public ResponseEntity<ScenarioDto> add(ScenarioDto scenarioDto) {
        if (scenarioDto != null) {
            handler.add(scenarioDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<ScenarioDto> addAll(List<ScenarioDto> scenarios) {
        if (scenarios != null) {
            handler.addAll(scenarios);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Optional<ScenarioDto>> poll() {
        return new ResponseEntity<>(handler.poll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ScenarioDto>> removeAll() {
        return new ResponseEntity<>(handler.removeAll(), HttpStatus.OK);
    }
}
