package pl.edu.agh.racing.cmodel.model;

public enum  Operation {
    ADD ("ADD"),
    UPDATE ("UPDATE"),
    DELETE ("DELETE"),
    ;

    private final String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
