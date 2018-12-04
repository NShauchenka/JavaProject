
package interfaces;

/**

 * Interface for Classroom
 * @author nastassiashauchenka
 */
public interface IClassroom {
    
    public void setRoomNumber (String p_roomNumber);
    public void setRoomType (String p_roomType);
    public void setCapacity (int p_capacity);
    public String getRoomNumber ();
    public String getRoomType ();
    public int getCapacity ();
}
