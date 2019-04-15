package rest.car_repair.exceptions.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(String message){
        super(message);
    }
}