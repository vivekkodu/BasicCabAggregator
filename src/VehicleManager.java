import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by VIVEK VERMA on 1/16/2017.
 */
public class VehicleManager {

    Map<String, List<Vehicle>> vehicleTypeTovehicles = new HashMap<>();

    protected VehicleManager(){

    }

    public Vehicle addNewVehicle(String vehicleType, int driverId, String vehicleNumber){
        switch (vehicleType){
            // ToDo: Type needs to be taken from enum
            case "1":
                Vehicle car = new CarDefault(vehicleType, driverId, vehicleNumber);
                addNewVehicleToPool(car);
                return car;
        }

        return null;
    }

    private void addNewVehicleToPool(Vehicle vehicle){
        List<Vehicle> vehicleList = new ArrayList<>();
        if(vehicleTypeTovehicles.containsKey(vehicle.getType())){
            vehicleList = vehicleTypeTovehicles.get(vehicle.getType());
            vehicleTypeTovehicles.remove(vehicle.getType());
        }

        addNewVehicleToVehicleList(vehicleList, vehicle);
        vehicleTypeTovehicles.put(vehicle.getType(), vehicleList);
    }

    private void addNewVehicleToVehicleList(List<Vehicle> vehicleList, Vehicle vehicle){
        //ToDo: Add vehicle at correct location so that final list is sorted
        vehicleList.add(vehicle);
    }

    public Vehicle findVehicle(int radius, Map<String, String> requirements, String type, Location userLocation){

        double nearestCabDistance = Double.MAX_VALUE;
        Vehicle nearestCab = null;

        //ToDo: Create a heap/Map which stored vehicle details with matches number of requirements

        if(!vehicleTypeTovehicles.containsKey(type)){
            System.out.println("There are no vehicles of this type");
            return null;
        }

        List<Vehicle> vehicleList = vehicleTypeTovehicles.get(type);

        System.out.println(" Total vehicles found: " + vehicleList.size());
        //ToDo: Find the nearest cabs using binary search and remove following search
        for (int i = 0; i < vehicleList.size(); i++) {
            if(!vehicleList.get(i).isVehicleAvailable()){
                System.out.println("Found vehicle with vehicle number" + vehicleList.get(i).getVehicleNumber() + ". But vehicle is" +
                        "already booked.");
                continue;
            }

            double cabToUserDistance =
                    Math.sqrt(Math.pow(Math.abs(vehicleList.get(i).getLocation().getXCoordinate() - userLocation.getXCoordinate()), 2) +
                            Math.pow(Math.abs(vehicleList.get(i).getLocation().getYCoordinate() - userLocation.getYCoordinate()), 2)) ;

            System.out.println("Distance of vehicle " + vehicleList.get(i).getVehicleNumber() + " from user is " + cabToUserDistance);
            if(cabToUserDistance < nearestCabDistance && cabToUserDistance < radius){
                System.out.println("Vehicle is in user's search radius");
                nearestCab = vehicleList.get(i);
                nearestCabDistance = cabToUserDistance;
            }
        }

        if(nearestCab == null){
            System.out.println("There are no cabs in user's search radius.");
            return null;
        }

        if(nearestCabDistance < radius){
            System.out.println(" Assigning cab with number " + nearestCab.getVehicleNumber());
            return nearestCab;
        }

        return null;

    }

}
