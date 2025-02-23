package flight.reservation.planefactory;

import flight.reservation.plane.Aircraft;
import flight.reservation.plane.PassengerPlane;

public class PassengerPlaneFactory implements AirCraftFactory {
    Aircraft aircraft;
    @Override
    public  Aircraft createAircraft(String model) {
        PassengerPlane passengerPlane = new PassengerPlane(model);
       this.aircraft = passengerPlane;
         return aircraft;
    }
            
}
