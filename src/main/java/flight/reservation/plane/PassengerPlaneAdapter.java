package flight.reservation.plane;

public class PassengerPlaneAdapter implements Aircraft {
    private PassengerPlane passengerPlane;

    public PassengerPlaneAdapter(PassengerPlane passengerPlane) {
        this.passengerPlane = passengerPlane;
    }

    @Override
    public String getModel() {
        return passengerPlane.model;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerPlane.passengerCapacity;
    }

    @Override
    public int getCrewCapacity() {
        return passengerPlane.crewCapacity;
    }
}