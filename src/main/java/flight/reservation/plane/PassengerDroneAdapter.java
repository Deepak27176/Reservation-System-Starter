package flight.reservation.plane;

public class PassengerDroneAdapter implements Aircraft {
    private PassengerDrone passengerDrone;

    public PassengerDroneAdapter(PassengerDrone passengerDrone) {
        this.passengerDrone = passengerDrone;
    }

    @Override
    public String getModel() {
        return "HypaHype"; 
    }

    @Override
    public int getPassengerCapacity() {
        return 4; 
    }

    @Override
    public int getCrewCapacity() {
        return 0; 
    }
}