/**
 * Created by VIVEK VERMA on 1/16/2017.
 */
public class Driver extends User{

    private String vehicleNumber;
    public Driver(int userId) {
        super(userId);
    }

    public Driver(int userId, String vehicleNumber, String vehicleType, Location location){
        this(userId);
        this.vehicleNumber = vehicleNumber;
        this.assignVehicle(vehicleType, location);
        UserManager.drivers.put(userId, this);
        System.out.println("Added driver successfully. Id = " + userId);
    }

    public void assignVehicle(String vehicleType, Location location){
        VehicleManager vehicleManager = VehicleManagerSingleton.getVehicleManager();
        Vehicle car = vehicleManager.addNewVehicle(vehicleType, this.userId, this.vehicleNumber);
        car.setLocation(location);
    }

    public int getUserId(){
        return this.userId;
    }

    public String getVehicleNumber(){
        return this.vehicleNumber;
    }


}
