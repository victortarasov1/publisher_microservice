package executor.service.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalTime;


@Entity
@Table(name = "step_reports")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class StepReport{
    @Id
    @UuidGenerator
    private String id;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "error_message")
    private String errorMessage;
    private String action;
    @Column(name = "step_value")
    private String value;
}