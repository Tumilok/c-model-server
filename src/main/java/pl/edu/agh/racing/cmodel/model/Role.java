package pl.edu.agh.racing.cmodel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private Long id;

    @NotBlank(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private ERole role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
