package flight.reservation.plane;

public class PassengerDrone implements Aircraft {
    private String model;


    public PassengerDrone(String model) {
        this.model = model;
    }

    @Override
    public String getModel() {
        return model;
    }
}

    

 