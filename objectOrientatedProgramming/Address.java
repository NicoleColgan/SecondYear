
/**
 * Address class
 *
 * @author Nicole Colgan
 * @version v1.0
 */
public class Address
{
    //class has two addresses
    private String billingAddress;
    private String deliveryAddress;
    public Address()
    {
        //empty constructor
    }
    //addresses set from main
    public void setBillingAddress(String billingAddress)
    {
        this.billingAddress=billingAddress;
    }
    public void setDeliveryAddress(String deliveryAddress)
    {
        this.deliveryAddress=deliveryAddress;
    }
    //access to addresses
    public String getBillingAddress()
    {
        return billingAddress;
    }
    public String getDeliveryAddress()
    {
        return deliveryAddress;
    }
}
