package pl.edu.agh.racing.cmodel.model;

public enum Role {
    ADMIN ("admin"),
    MODERATOR ("moderator"),
    USER ("user"),
    NEWBIE ("newbie"),
    ;

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
