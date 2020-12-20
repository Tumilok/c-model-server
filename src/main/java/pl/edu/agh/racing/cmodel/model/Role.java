package pl.edu.agh.racing.cmodel.model;

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
}
