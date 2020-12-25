package pl.edu.agh.racing.cmodel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(unique = true, length = 50)
    private String fw;

    @Column(unique = true, length = 50)
    private String rw;

    @Column(unique = true, length = 50)
    private String s;

    @Column(unique = true, length = 50)
    private String diff;

    @Column(unique = true, length = 50)
    private String u;

    @Column(unique = true, length = 50)
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
}
