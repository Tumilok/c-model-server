package pl.edu.agh.racing.cmodel.model;

public enum  Operation {
    ADD ("add"),
    UPDATE ("update"),
    DELETE ("delete"),
    ;

    private final String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
