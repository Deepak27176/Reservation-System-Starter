package flight.reservation;
import flight.reservation.flightfactory.FlightFactory;
import flight.reservation.flight.Flight;
import flight.reservation.flight.Schedule;
import flight.reservation.flight.ScheduledFlight;
import flight.reservation.plane.Aircraft;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import flight.reservation.planefactory.HelicopterFactory;
import flight.reservation.planefactory.PassengerDroneFactory;
import flight.reservation.planefactory.PassengerPlaneFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Schedule Tests")
public class ScheduleTest {

    private Schedule schedule;
    private HelicopterFactory helicopterFactory;
    private PassengerDroneFactory passengerDroneFactory;
    private PassengerPlaneFactory passengerPlaneFactory;
    private FlightFactory flightFactory;

    @BeforeEach
    public void initSchedule() {
        schedule = new Schedule();
        helicopterFactory = new HelicopterFactory();
        passengerDroneFactory = new PassengerDroneFactory();
       passengerPlaneFactory = new PassengerPlaneFactory();
        flightFactory = new FlightFactory();
    }

    @Nested
    @DisplayName("Given an empty Schedule")
    class GivenAnEmptySchedule {

        @Test
        @DisplayName("then the size of the Schedule should be zero")
        void thenTheSizeOfTheScheduleShouldBeZero() {
            assertEquals(0, schedule.getScheduledFlights().size());
        }

        @Test
        @DisplayName("then the search for an unknown flight element should return null")
        void thenSearchForAnUnknownElementShouldReturnNull() {
            assertEquals(null, schedule.searchScheduledFlight(1337));
        }

        @Test
        @DisplayName("then the search for an known flight element should return null")
        void thenSearchForAnKnownElementShouldReturnNull() {
            assertEquals(null, schedule.searchScheduledFlight(1));
        }

        @Test
        @DisplayName("then removing a flight should still yield an empty list")
        void thenScheduleShouldYieldEmpty() {
            schedule.removeFlight(flightFactory.createFlight(1, new Airport("a", "a", "a"), new Airport("b", "b", "b"), passengerPlaneFactory.createAircraft("A380")));
            assertEquals(0, schedule.getScheduledFlights().size());
        }

        @Nested
        @DisplayName("when a flight is scheduled")
        class WhenAFlightIsScheduled {

            private Flight flight;
            private Date departure;

            @BeforeEach
            void scheduleOneFlight() {
                Airport startAirport = new Airport("Berlin Airport", "BER", "Berlin, Berlin");
                Airport destAirport = new Airport("Frankfurt Airport", "FRA", "Frankfurt, Hesse");

               // PassengerPlane aircraft = new PassengerPlane("A380");
                Aircraft aircraft = passengerPlaneFactory.createAircraft("A380");
                flight = flightFactory.createFlight(1, startAirport, destAirport, aircraft);
                departure = TestUtil.addDays(Date.from(Instant.now()), 3);
                schedule.scheduleFlight(flight, departure);
            }

            @Test
            @DisplayName("then the schedule should not be empty anymore")
            void thenScheduleShouldContainOneElement() {
                assertEquals(1, schedule.getScheduledFlights().size());
            }

            @Test
            @DisplayName("then the correct flight should be scheduled")
            void thenScheduleShouldContainCorrectElement() {
                ScheduledFlight scheduledFlight = schedule.getScheduledFlights().get(0);
                assertEquals(flight.getNumber(), scheduledFlight.getNumber());
                assertEquals(departure, scheduledFlight.getDepartureTime());
            }

            @Test
            @DisplayName("then the schedule should not be empty anymore")
            void thenSearchShouldReturnFlight() {
                ScheduledFlight scheduledFlight = schedule.getScheduledFlights().get(0);
                assertEquals(scheduledFlight, schedule.searchScheduledFlight(1));
            }

            @Test
            @DisplayName("then removing a flight should yield an empty list")
            void thenRemoveShouldYieldEmpty() {
                schedule.removeFlight(flight);
                assertEquals(0, schedule.getScheduledFlights().size());
            }
        }
    }

    @Nested
    @DisplayName("Given an existing Schedule")
    class GivenAnExistingSchedule {
   

        List<Airport> airports=new ArrayList<>();
        List<Flight> flights=new ArrayList<>();

        @BeforeEach
        public void initSchedule() throws ParseException {
            
        // List<Airport> 
        airports = Arrays.asList(
                new Airport("Berlin Airport", "BER", "Berlin, Berlin"),
                new Airport("Frankfurt Airport", "FRA", "Frankfurt, Hesse"),
                new Airport("Madrid Barajas Airport", "MAD", "Barajas, Madrid"),
                new Airport("Guarulhos International Airport", "GRU", "Guarulhos (São Paulo)"),
                new Airport("John F. Kennedy International Airport", "JFK", "Queens, New York, New York"),
                new Airport("Istanbul Airport", "IST", "Arnavutköy, Istanbul"),
                new Airport("Dubai International Airport", "DXB", "Garhoud, Dubai"),
                new Airport("Chengdu Shuangliu International Airport", "CTU", "Shuangliu-Wuhou, Chengdu, Sichuan")
        );

        // List<Flight> 
        flights = Arrays.asList(
                flightFactory.createFlight(1, airports.get(0), airports.get(1), passengerPlaneFactory.createAircraft("A350")),
                flightFactory.createFlight(2, airports.get(1), airports.get(2),passengerPlaneFactory.createAircraft("A380") ),
                flightFactory.createFlight(3, airports.get(2), airports.get(4),passengerPlaneFactory.createAircraft("Embraer 190") ),
                flightFactory.createFlight(4, airports.get(3), airports.get(2),passengerPlaneFactory.createAircraft("Antonov AN2")),
                flightFactory.createFlight(5, airports.get(4), airports.get(2),helicopterFactory.createAircraft("H1") ),
                flightFactory.createFlight(6, airports.get(5), airports.get(7),passengerDroneFactory.createAircraft("HypaHype"))
        );


            int i = 1;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            for (Flight flight : flights) {
                Date departure = TestUtil.addDays(format.parse("2020-01-01"), i);
                schedule.scheduleFlight(flight, departure);
                i++;
            }
        }

        @Test
        @DisplayName("then the schedule should contain the correct number of scheduled flights")
        void thenScheduleShouldContainOneElement() {
            assertEquals(6, schedule.getScheduledFlights().size());
        }

        @Nested
        @DisplayName("when a flight is removed")
        class AFlightIsRemoved {

            @Test
            @DisplayName("then a flight should be removed")
            void thenAFlightShouldBeRemoved() {
                schedule.removeFlight(flights.get(0));
                assertEquals(5, schedule.getScheduledFlights().size());
            }

            @Test
            @DisplayName("then the flight should not be scheduled anymore")
            void thenTheCorrectFlightShouldBeRemoved() {
                Flight flight = flights.get(3);
                schedule.removeFlight(flight);
                assertTrue(schedule.getScheduledFlights().stream().anyMatch(o -> o.getNumber() == flights.get(0).getNumber()));
                assertTrue(schedule.getScheduledFlights().stream().anyMatch(o -> o.getNumber() == flights.get(1).getNumber()));
                assertTrue(schedule.getScheduledFlights().stream().anyMatch(o -> o.getNumber() == flights.get(2).getNumber()));
                assertTrue(schedule.getScheduledFlights().stream().anyMatch(o -> o.getNumber() == flights.get(4).getNumber()));
                assertFalse(schedule.getScheduledFlights().stream().anyMatch(o -> o.getNumber() == flights.get(3).getNumber()));
            }
        }

        @Nested
        @DisplayName("when a flight is scheduled")
        class AFlightIsScheduled {

            @Test
            @DisplayName("then the schedule should contain the added flight")
            void thenTheScheduleShouldContainTheAddedFlight() throws ParseException {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date departure = TestUtil.addDays(format.parse("2020-01-01"), 20);
                schedule.scheduleFlight(flights.get(3), departure);
                assertEquals(flights.get(3).getNumber(), schedule.getScheduledFlights().get(schedule.getScheduledFlights().size() - 1).getNumber());
                assertEquals(flights.get(3).getArrival(), schedule.getScheduledFlights().get(schedule.getScheduledFlights().size() - 1).getArrival());
                assertEquals(flights.get(3).getDeparture(), schedule.getScheduledFlights().get(schedule.getScheduledFlights().size() - 1).getDeparture());
                assertEquals(departure, schedule.getScheduledFlights().get(schedule.getScheduledFlights().size() - 1).getDepartureTime());
            }
        }

        @Nested
        @DisplayName("when a flight is searched")
        class AFlightIsSearched {

            @Test
            @DisplayName("and the flight is scheduled then the flight should be returned")
            void thenTheFlightShouldBeReturned() throws ParseException {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date departure = TestUtil.addDays(format.parse("2020-01-01"), 2);
                assertEquals(flights.get(1).getNumber(), schedule.searchScheduledFlight(flights.get(1).getNumber()).getNumber());
                assertEquals(flights.get(1).getArrival(), schedule.searchScheduledFlight(flights.get(1).getNumber()).getArrival());
                assertEquals(flights.get(1).getDeparture(), schedule.searchScheduledFlight(flights.get(1).getNumber()).getDeparture());
                assertEquals(departure, schedule.searchScheduledFlight(flights.get(1).getNumber()).getDepartureTime());
            }

            @Test
            @DisplayName("and the flight is not scheduled then null should be returned")
            void thenNullShouldBeReturned() {
                schedule.removeFlight(flights.get(0));
                assertEquals(null, schedule.searchScheduledFlight(flights.get(0).getNumber()));
            }
        }
    }
}
