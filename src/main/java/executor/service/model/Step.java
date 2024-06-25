package executor.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "steps")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Step {
    @Id
    @UuidGenerator
    private String id;
    @NotEmpty
    private String action;
    @NotEmpty
    @Column(name = "step_value")
    private String value;
}
