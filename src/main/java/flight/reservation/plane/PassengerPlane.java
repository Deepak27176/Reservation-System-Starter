package flight.reservation.plane;

import java.util.Map;

public class PassengerPlane implements Aircraft {

    private  String model;
    private  int passengerCapacity;
    private  int crewCapacity;
     private static final Map<String, int[]> PLANE_MODELS = Map.of(
        "A380", new int[]{500, 42},
        "A350", new int[]{320, 40},
        "Embraer 190", new int[]{25, 5},
        "Antonov AN2", new int[]{15, 3}
    );

    public PassengerPlane(String model) {
        this.model = model;
        
        int[] capacities = PLANE_MODELS.get(model);

        if (capacities == null) {
            throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }

        this.passengerCapacity = capacities[0];
        this.crewCapacity = capacities[1];
        }
        @Override
        public String getModel() {
            return model;
        }
    
    
        public int getPassengerCapacity() {
            return passengerCapacity;
        }
    
       
        public int getCrewCapacity() {
            return crewCapacity;
        }
    }


