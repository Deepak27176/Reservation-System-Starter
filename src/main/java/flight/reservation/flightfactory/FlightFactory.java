package flight.reservation.flightfactory;

import flight.reservation.Airport;
import flight.reservation.flight.Flight;
import flight.reservation.plane.Helicopter;
import flight.reservation.plane.PassengerDrone;
import flight.reservation.plane.PassengerPlane;

public class FlightFactory {
    public Flight createFlight(int number, Airport departure, Airport arrival, Object aircraft) {
        if (!isAircraftValid(departure, aircraft) || !isAircraftValid(arrival, aircraft)) {
            throw new IllegalArgumentException("Selected aircraft is not valid for the selected route.");
        }
        return new Flight(number, departure, arrival, aircraft);
    }

    private boolean isAircraftValid(Airport airport, Object aircraft) {
        String model;
        if (aircraft instanceof PassengerPlane) {
            model = ((PassengerPlane) aircraft).getModel();
        } else if (aircraft instanceof Helicopter) {
            model = ((Helicopter) aircraft).getModel();
        } else if (aircraft instanceof PassengerDrone) {
            model = "HypaHype";
        } else {
            throw new IllegalArgumentException("Aircraft is not recognized");
        }
        return airport.getAllowedAircrafts() != null && java.util.Arrays.asList(airport.getAllowedAircrafts()).contains(model);
    }
}

