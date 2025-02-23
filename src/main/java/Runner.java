import flight.reservation.Airport;
import flight.reservation.flight.Schedule;
import flight.reservation.flight.Flight;
import flight.reservation.plane.Helicopter;
import flight.reservation.plane.PassengerDrone;
import flight.reservation.plane.PassengerPlane;
import flight.reservation.planefactory.HelicopterFactory;
import flight.reservation.planefactory.PassengerDroneFactory;
import flight.reservation.planefactory.PassengerPlaneFactory;
import flight.reservation.flightfactory.FlightFactory;
import java.util.Arrays;
import java.util.List;

public class Runner {
        private static HelicopterFactory helicopterFactory = new HelicopterFactory();
        private static PassengerDroneFactory passengerDroneFactory = new PassengerDroneFactory();
        private static PassengerPlaneFactory passengerPlaneFactory = new PassengerPlaneFactory();
        private static FlightFactory flightFactory = new FlightFactory();   
    static List<Airport> airports = Arrays.asList(
            new Airport("Berlin Airport", "BER", "Berlin, Berlin"),
            new Airport("Frankfurt Airport", "FRA", "Frankfurt, Hesse"),
            new Airport("Madrid Barajas Airport", "MAD", "Barajas, Madrid"),
            new Airport("Guarulhos International Airport", "GRU", "Guarulhos (São Paulo)"),
            new Airport("John F. Kennedy International Airport", "JFK", "Queens, New York, New York"),
            new Airport("Istanbul Airport", "IST", "Arnavutköy, Istanbul"),
            new Airport("Dubai International Airport", "DXB", "Garhoud, Dubai"),
            new Airport("Chengdu Shuangliu International Airport", "CTU", "Shuangliu-Wuhou, Chengdu, Sichuan")
    );

    static List<Object> aircrafts = Arrays.asList(
        passengerPlaneFactory.createAircraft("A380") ,
        passengerPlaneFactory.createAircraft("A350") ,
        passengerPlaneFactory.createAircraft("Embraer 190"),
        passengerPlaneFactory.createAircraft("Antonov AN2") ,
        helicopterFactory.createAircraft("H1") ,
        passengerDroneFactory.createAircraft("HypaHype")
    );

    static List<Flight> flights = Arrays.asList(
        flightFactory.createFlight(1, airports.get(0), airports.get(1), aircrafts.get(0)),
        flightFactory.createFlight(2, airports.get(1), airports.get(2), aircrafts.get(1)),
        flightFactory.createFlight(3, airports.get(2), airports.get(4), aircrafts.get(2)),
        flightFactory.createFlight(4, airports.get(3), airports.get(2), aircrafts.get(3)),
        flightFactory.createFlight(5, airports.get(4), airports.get(2), aircrafts.get(4)),
        flightFactory.createFlight(6, airports.get(5), airports.get(7), aircrafts.get(5))
    );

    static Schedule schedule;

    public static void main(String[] args) {
    }
}
