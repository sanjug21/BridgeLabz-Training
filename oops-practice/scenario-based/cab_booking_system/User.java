package cab_booking_system;

import java.util.ArrayList;
import java.util.List;

class User{
    private String userId;
    private String name;
    private String phoneNo;
    private List<Ride> rides;


    public User(String userId, String name, String phoneNo) {
        this.userId = userId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.rides=new ArrayList<>();
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void addRide(Ride ride) {
        rides.add(ride);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    @Override
    public String toString() {
        return "User{" +
               "userId='" + userId + '\'' +
               ", name='" + name + '\'' +
               ", phoneNo='" + phoneNo + '\'' +
               '}';
    }

}