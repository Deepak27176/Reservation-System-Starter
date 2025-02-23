package flight.reservation.planefactory;

import flight.reservation.plane.Aircraft;
import flight.reservation.plane.PassengerDrone;

public class PassengerDroneFactory implements AirCraftFactory {
    Aircraft aircraft;
    @Override
    public  Aircraft createAircraft(String model) {
        PassengerDrone passengerDrone = new PassengerDrone(model);
       this.aircraft = passengerDrone;
         return aircraft;
    }
    
}
