package pl.edu.agh.racing.cmodel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private Long id;

    @NotNull
    private String token;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    private Instant expiryDate;
}
