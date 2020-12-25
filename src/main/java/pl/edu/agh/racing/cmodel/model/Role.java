package pl.edu.agh.racing.cmodel.model;

import java.util.stream.Stream;

public enum Role {
    ADMIN ("ROLE_ADMIN"),
    MODERATOR ("ROLE_MODERATOR"),
    USER ("ROLE_USER"),
    NEWBIE ("ROLE_NEWBIE"),
    ;

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static Role of(String role) {
        return Stream.of(Role.values())
                .filter(r -> r.getRole().equals(role))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
