import java.util.Random;
/**
 * Custumer class
 *
 * @author Nicole Colgan 
 * @version v1.0
 */
public class Custumer
{
    private String firstName;  
    private String surname;
    private String emailAddress;
    private final long custumerId; //final means value cannot be changed ever
    private Address address;       //custumer HAS AN address
    private Email email;
   public Custumer(String firstName,String surname,String emailAddress)
   {
       this.firstName=firstName;
       this.surname=surname;
       this.emailAddress=emailAddress;
       custumerId=makeCustumerId();    //method to make a custumer ID
   }
   //adding an address to custumer rather than passing it into the constructor
   public void add(Address address)
   {
       this.address=address;
   }
   //method to access address from other classes that have a relationship with object 
   public Address getAddress()
   {
       return address;
   }
   public long getId()
   {
       return custumerId;
   }
   public String getFirstName()
   {
       return firstName;
   }
   public String getsurname()
   {
       return surname;
   }
   public String getEmailAdd()
   {
       return emailAddress;
   }
   //using random package and methods to generate a random custumer ID
   public long makeCustumerId()
   {
       Random id = new Random();    //creating a new random object
       long value = id.nextLong();  //making an id with the random package next long method
       return value;                //send this value back to the constructor 
   }
}
