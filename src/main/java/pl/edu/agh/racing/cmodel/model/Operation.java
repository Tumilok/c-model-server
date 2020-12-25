package pl.edu.agh.racing.cmodel.model;

import java.util.stream.Stream;

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

    public static Operation of(String operation) {
        return Stream.of(Operation.values())
                .filter(o -> o.getOperation().equals(operation))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
