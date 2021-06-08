package pl.edu.agh.racing.cmodel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 50)
    private String fw;

    @Column(length = 50)
    private String rw;

    @Column(length = 50)
    private String s;

    @Column(length = 50)
    private String diff;

    @Column(length = 50)
    private String u;

    @Column(length = 50)
    private String n;

    @Column(precision = 1)
    private Double df;

    @Column(precision = 1)
    private Double d;

    @Column(precision = 1)
    private Double balance;

    @Column(precision = 3)
    private Double mrfChlodnica;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @OneToMany(mappedBy = "simulation")
    private Set<Log> logs = new HashSet<>();

    @CreationTimestamp
    private Instant createDateTime;

    @UpdateTimestamp
    private Instant updateDateTime;
}
