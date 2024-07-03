package executor.service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    private List<Step> steps;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;
}