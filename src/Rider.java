import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by VIVEK VERMA on 1/16/2017.
 */
public class Rider extends User {

    private int lastPayment = 0;
    private Location location;

    private List<Vehicle> currentCabs =  new ArrayList<>();

    int maxRidesAllowedAtATime = 1; // ToDo: This should be configurable
    int currentCabsBooked = 0;

    public Rider(int userId){
        super(userId);
        UserManager.rider.put(userId, this);
        System.out.println("Added rider successfully. Id= " + userId);
    }

    public Vehicle bookCab(int allowedRadius, Map<String, String> requirements, String type){
        if(this.currentCabsBooked >= this.maxRidesAllowedAtATime){
            System.out.println("Cab booking limit exceeded");
            return null;
        }

        VehicleManager vehicleManager = VehicleManagerSingleton.getVehicleManager();

        Vehicle vehicle = vehicleManager.findVehicle(allowedRadius, requirements, type, this.location);

        if(vehicle == null){
            System.out.println("No vehicle available or no vehicles for given restriction");
            return null;
        }

        this.currentCabs.add(vehicle);
        this.currentCabsBooked ++;

        System.out.println("Found vehicle with owner " + vehicle.getOwnerId());
        System.out.println("Booked vehicle successfully");
        return vehicle;
    }

    public void endTrip(int cost, String vehicleNumber, Location location){
        if(this.currentCabs == null){
            System.out.println("User has no active trip");
            return;
        }

        currentCabsBooked--;
        this.lastPayment = cost;
        for (int i = 0; i < currentCabs.size(); i++) {
            if(currentCabs.get(i).getVehicleNumber().equalsIgnoreCase(vehicleNumber)){
                currentCabs.get(i).endTrip(location);
                this.currentCabs.remove(i);
                System.out.println("Trip ended successfully. Vehicle number " + vehicleNumber);
                return;
            }
        }

        System.out.println("No trip found for given vehicle number");
    }/*

    public void endFirstTrip(){
        if(this.currentCabs == null){
            System.out.println("User has no active trip");
            return;
        }

        currentCabsBooked--;
        this.currentCabs.remove(0);
        System.out.println("Trip ended successfully");
    }*/

    public int getUserId(){
        return this.userId;
    }

    public Location getLocation(){
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
