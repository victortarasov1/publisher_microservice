package executor.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepReport {
    private Step step;
    private LocalTime startTime;
    private LocalTime endTime;
    private String errorMessage;
}