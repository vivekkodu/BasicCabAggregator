/**
 * Created by VIVEK VERMA on 1/16/2017.
 */
public class CarDefault extends Vehicle {
    private boolean isAlreadyOccupied = false;

    public CarDefault(String vehicleType, int ownerId, String vehicleNumber) {
        super(vehicleType, ownerId, vehicleNumber);
    }

    @Override
    public synchronized boolean bookCab() {
        if(!isAlreadyOccupied){
            System.out.println("Booking cab with id " + this.getVehicleNumber());
            this.isAlreadyOccupied = true;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void endTrip(Location currentLocation) {
        this.isAlreadyOccupied = false;
        this.setLocation(currentLocation);
    }

    public boolean isVehicleAvailable(){
        return !this.isAlreadyOccupied;
    }


    @Override
    public int compareTo(Vehicle o) {
        if(this.getLocation().getXCoordinate() < o.getLocation().getXCoordinate())
        {
            return -1;
        }
        else if(this.getLocation().getXCoordinate() == o.getLocation().getYCoordinate()
                && this.getLocation().getYCoordinate() < o.getLocation().getYCoordinate()){
            return -1;
        }else{
            return 1;
        }
    }
}
