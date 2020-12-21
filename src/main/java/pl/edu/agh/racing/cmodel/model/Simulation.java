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
    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String fw;

    @Column(unique = true)
    private String rw;

    @Column(unique = true)
    private String s;

    @Column(unique = true)
    private String diff;

    @Column(unique = true)
    private String u;

    @Column(unique = true)
    private String n;

    @Column(precision = 1)
    private double df;

    @Column(precision = 1)
    private String d;

    @Column(precision = 1)
    private String balance;

    @Column(precision = 3)
    private String mrfChlodnica;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
}
