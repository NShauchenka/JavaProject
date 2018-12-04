
package datamodels;

import static controllers.Application.getAUDIT_LOGGER;
import static controllers.Application.getDEBUG_LOGGER;
import interfaces.IClassroom;

public class Classroom implements IClassroom {
   
    private String roomNumber;
    private String roomType;
    private int capacity;

    public Classroom() {
        getAUDIT_LOGGER().info("Created new classroom: ");
    }
    
    public void setRoomNumber(String p_roomNumber) {
        roomNumber = p_roomNumber;
        getDEBUG_LOGGER().finest("Set Room Number: " + roomNumber);
    }

    
    public void setRoomType(String p_roomType) {
        roomType = p_roomType;
        getDEBUG_LOGGER().finest("Set Room Number: " + roomType);
    }

    
    public void setCapacity(int p_capacity) {
        capacity = p_capacity;
        getDEBUG_LOGGER().finest("Set Room Number: " + capacity);
    }

    
    public String getRoomNumber() {
        return roomNumber;
    }

    
    public String getRoomType() {
        return roomType;
    }

    
    public int getCapacity() {
        return capacity;
    }
    
    @Override
    public String toString() {
        return "Classroom{roomNumber=" + roomNumber + ", typeOfRoom=" + roomType + ", capacity=" + capacity + "}";
    }

    

}
