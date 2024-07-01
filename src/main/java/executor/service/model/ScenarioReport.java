package executor.service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "scenario_reports")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ScenarioReport {
        @Id
        @UuidGenerator
        private String id;
        @Column(name = "scenario_id")
        private String scenarioId;
        @Column(name = "start_time")
        private LocalDateTime startTime;
        @Column(name = "end_time")
        private LocalDateTime endTime;
        @Column(name = "error_message")
        private String errorMessage;
        @Column(name = "web_driver_info")
        private String webDriverInfo;
        private String name;
        private String site;
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @OrderBy("startTime ASC")
        private List<StepReport> stepReports;
}
