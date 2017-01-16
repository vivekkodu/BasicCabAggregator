import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by VIVEK VERMA on 1/16/2017.
 */
public class Application {
    public static void main(String[] args) throws IOException {

        Application application = new Application();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            // Define operations as follows:
            // 1 UserId x y-->Add new user
            // 2 userId VehicleNumber VehicleType x y--> Add new driver
            // 3 userId radius vehicleType --> Book a cab
            // 4 userId cost --> Ends trip. For testing purpose, we will end the first trip.

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int operationType = Integer.parseInt(tokenizer.nextToken());

            int userId = Integer.parseInt(tokenizer.nextToken());
            String vehicleType;
            int x, y;
            String vehicleNumber;
            switch (operationType){
                case 1:
                    x = Integer.parseInt(tokenizer.nextToken());
                    y = Integer.parseInt(tokenizer.nextToken());
                    application.addUser(userId, x, y);
                    break;
                case 2: vehicleNumber = tokenizer.nextToken();
                    vehicleType = tokenizer.nextToken();
                    x = Integer.parseInt(tokenizer.nextToken());
                    y = Integer.parseInt(tokenizer.nextToken());
                    application.addDriver(userId, vehicleNumber, vehicleType, x, y);
                    break;
                case 3: int radius = Integer.parseInt(tokenizer.nextToken());
                    vehicleType = tokenizer.nextToken();
                    application.bookCab(userId, radius, vehicleType);
                    break;
                case 4: vehicleNumber = tokenizer.nextToken();
                    application.endTrip(userId, vehicleNumber);
                    break;
                }
            }
        }

    private void addUser(int userId, int x, int y){
        Rider rider = new Rider(userId);
        rider.setLocation(new Location(x, y));
    }

    private void addDriver(int userId, String vechicleNumber, String vechicleType, int x, int y){
        new Driver(userId, vechicleNumber, vechicleType, new Location(x, y));
    }

    private void bookCab(int userId, int radius, String vechicleType){
        Rider rider = UserManager.rider.get(userId);
        rider.bookCab(radius, null, vechicleType);
    }

    private void endTrip(int userId, String vehicleNumber){
        Rider rider = UserManager.rider.get(userId);
        rider.endTrip(0, vehicleNumber, new Location(0, 0));
    }
}

