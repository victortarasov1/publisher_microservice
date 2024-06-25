package executor.service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;


@Entity
@Table(name = "scenarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Scenario {
    @Id
    @UuidGenerator
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String site;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Step> steps;
}