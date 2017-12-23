package rest.carRepair.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.carRepair.domain.Member;
import rest.carRepair.domain.Vehicle;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleExistException;
import rest.carRepair.exceptions.vehicle.VehicleNotFoundException;
import rest.carRepair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.carRepair.repositories.VehicleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MemberService memberService;

    @Override
    public List<Vehicle> getAllVehiclesByMember(Long memberId) throws VehicleNotFoundException, MemberNotFoundException {
        Member member = memberService.getMemberById(memberId);
        List<Vehicle> memberVehicles = member.getVehicles();
        if(memberVehicles.size() == 0){
            throw new VehicleNotFoundException("There are not found vehicle for user with id " + memberId);
        }
        return memberVehicles;
    }

    @Override
    public Vehicle getVehicleByMember(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        memberService.getMemberById(memberId);
        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        if(vehicle == null){
            throw new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found");
        }
        if(!(vehicle.getMember().getUserId() == memberId)){
            throw new VehicleNotReferredToUserException("Vehicle with id " + vehicleId + " is not referred to user with id " + memberId);
        }
        return vehicle;
    }

    @Override
    public Vehicle saveVehicle(Long memberId, Vehicle vehicle) throws VehicleExistException, MemberNotFoundException {
        Member member = memberService.getMemberById(memberId);
        try {
            vehicle.setMember(member);
            Vehicle newVehicle = vehicleRepository.save(vehicle);
            return newVehicle;
        } catch (Exception e){
            throw new VehicleExistException("Vehicle with plate " + vehicle.getPlate() + " already exists");
        }
    }

    @Override
    public Vehicle updateVehicle(Long memberId, Long vehicleId, Vehicle vehicle) throws VehicleNotFoundException, VehicleNotReferredToUserException, VehicleExistException, MemberNotFoundException {
        getVehicleByMember(memberId, vehicleId);
        vehicle.setVehicleId(vehicleId);
        return saveVehicle(memberId,vehicle);
    }

    @Transactional
    @Override
    public void deleteVehicle(Long memberId, Long vehicleId) throws VehicleNotFoundException, VehicleNotReferredToUserException, MemberNotFoundException {
        getVehicleByMember(memberId, vehicleId);
        vehicleRepository.deleteByVehicleId(vehicleId);
    }
}
