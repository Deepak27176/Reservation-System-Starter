package flight.reservation.planefactory;
import flight.reservation.plane.Helicopter;
import flight.reservation.plane.Aircraft;
public class HelicopterFactory implements AirCraftFactory {
    Aircraft aircraft;
    @Override
    public  Aircraft createAircraft(String model) {
        
       this.aircraft =  new Helicopter(model);
         return aircraft;
    }

    
}