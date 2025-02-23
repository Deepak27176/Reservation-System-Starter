package flight.reservation.planefactory;

import flight.reservation.plane.Aircraft;

public interface AirCraftFactory {

    public  Aircraft createAircraft(String model);
    
}
