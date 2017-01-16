Classes:
	abstract User:Maintains user Id.
	
	Rider(extends User): Rider can book maxRidesAllowedAtATime number of rides and end a ride. 
	
	Driver (extends User): Driver owns a vehicle

	abstract Vehicle: Maintains vehicle number, type, owner and location

	CarDefault: Vehicle implementation with type number 1.
			Functions: It can be booked by a user.
					   It will be returned to the vehicle pool once trip is completed. 

	UserManager: It is maintaining list of all the users(riders and drivers).

	VehicleManager: This is a link between users and vehicles. 
			Function: Add a new vehicle to the vehicle pool
					  find a vehicle to be booked which is following all restrictions like radius of search, type of vehicle or any other specific requirements

	VehicleManagerSingleton: This is used to create an object of VehicleManager and which is being used by all the users and vehicles.


ToDo:
	1. Instead of directly keeping the vehicles in a list in vehicleManager, keep the vehicles in sorted order on the basis of coordinates, so that when a request 	
		comes, we can do binary search and find nearby cabs. This saves comparision with all the cabs.
	2. MaxRidesAllowedAtATime for a user should be configurable
	3. Vehicle type should be an enum
	4. When a new vehicle is added to the pool it should be added at corect location based on vehicle's location
	5. When a tri ends, vehicle's location change. So it's position should be updated in the pool based on location.
	6. The additional requirements search needs to be modified.
	7. Validations like userId already exists, vehicle id already exists, null checks etc

Testings:
	Run Application.java. It supports 4 kinds of operations
	
    1 UserId x y-->Add new user with gien userId and (x,y) coordinates
    2 userId VehicleNumber VehicleType x y--> Add new driver with given user id(Driver id), vehicle with vehicleId and vehicleType is added to pool and assigned to 
    	this driver. Also position of vehicle is added as (x,y)
    3 userId radius vehicleType --> Book a cab which is in given radius for the used based on restrictions: It can book atmax maxRidesAllowedAtATime(Currently 1) 
    	number of cabs. 
    4 userId vehicleNuber --> Ends trip for vehicle whose number is provided


Test Output
============
1 1 5 10
Added rider successfully. Id= 1

1 2 15 20
Added rider successfully. Id= 2

2 5 123 1 5 5
Added driver successfully. Id = 5

2 6 124 1 20 5
Added driver successfully. Id = 6

3 1 10 1
 Total vehicles found: 2
Distance of vehicle 123 from user is 5.0
Vehicle is in user's search radius
Distance of vehicle 124 from user is 15.811388300841896
 Assigning cab with number 123
Found vehicle with owner 5
Booked vehicle successfully

3 2 10 1
 Total vehicles found: 2
Distance of vehicle 123 from user is 18.027756377319946
Distance of vehicle 124 from user is 15.811388300841896
There are no cabs in user's search radius.
No vehicle available or no vehicles for given restriction

3 1 20 1
Cab booking limit exceeded

3 2 20 1
 Total vehicles found: 2
Distance of vehicle 123 from user is 18.027756377319946
Vehicle is in user's search radius
Distance of vehicle 124 from user is 15.811388300841896
Vehicle is in user's search radius
 Assigning cab with number 124
Found vehicle with owner 6
Booked vehicle successfully

4 1 123
Trip ended successfully. Vehicle number 123

4 1 123
No trip found for given vehicle number

4 2 124
Trip ended successfully. Vehicle number 124

3 1 20 1
 Total vehicles found: 2
Distance of vehicle 123 from user is 11.180339887498949
Vehicle is in user's search radius
Distance of vehicle 124 from user is 11.180339887498949
 Assigning cab with number 123
Found vehicle with owner 5
Booked vehicle successfully


