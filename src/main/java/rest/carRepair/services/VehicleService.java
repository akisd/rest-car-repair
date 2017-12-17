package rest.carRepair.services;

import rest.carRepair.domain.Vehicle;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleExistException;
import rest.carRepair.exceptions.vehicle.VehicleNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.carRepair.exceptions.vehicle.VehiclesNotFoundException;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllMemberVehicles(Long userId) throws VehiclesNotFoundException, MemberNotFoundException;

    Vehicle getMemberVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException,VehicleNotReferredToUserException;

    Vehicle saveVehicle(Long memberId, Vehicle vehicle) throws VehicleExistException, MemberNotFoundException;

    Vehicle updateVehicle(Long id, Long vehicleId, Vehicle vehicle) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException;

    void deleteVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException;
}
