/**
 * Created by VIVEK VERMA on 1/16/2017.
 */
public class VehicleManagerSingleton extends VehicleManager{
    private static VehicleManager vehicleManager = null;

    public static VehicleManager getVehicleManager(){
        if(vehicleManager == null){
            vehicleManager = new VehicleManager();
        }

        return vehicleManager;
    }
}
