package rest.car_repair.exceptions.repair;

import lombok.Getter;

public class RepairExistException extends Exception {

    private static final long serialVersionUID = 1L;
    @Getter
    private final String message;

    public RepairExistException(String message) {
        super(message);
        this.message = message;
    }
}
