/**
 * Created by VIVEK VERMA on 1/16/2017.
 */
public abstract class Vehicle implements Comparable<Vehicle>{
    private String vehicleNumber;
    private String veicleType;
    private int ownerId;
    private Location location;

    protected Vehicle(String vehicleType, int ownerId, String vehicleNumber){
        this.veicleType = vehicleType;
        this.ownerId = ownerId;
        this.vehicleNumber = vehicleNumber;
    }

    public abstract boolean bookCab();

    public abstract void endTrip(Location currentLocation);

    public abstract boolean isVehicleAvailable();

    public String getType(){
        return this.veicleType;
    }

    public int getOwnerId(){
        return this.ownerId;
    }

    // This is needed in case car is sold to another owner.
    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }

    public String getVehicleNumber(){
        return this.vehicleNumber;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }
}
