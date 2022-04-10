
/**
 * Email class
 *
 * @author Nicole Colgan
 * @version v1.0
 */
public class Email
{
    private Order order;    //email has an order which contains most other class information
    private Address address;   //email has an address of the cutumer which we will print
        public Email(Order order)   //order is passed into email constructor
    {
        this.order=order;
    }
    //access to order
    public Order getOrder()
    {
        return order;
    }
    //add an address to email
    public void add(Address address)
    {
        this.address=address;
    }
    //access to address
    public Address getAddress()
    {
        return address;
    }
    //method called to print an email
    public void confirmEmail()
    {
        if(order.confirmOrder()==true && order.getPayment().isValid()==true)  //if all details are valid send a positive email
        {
            System.out.println("Hello "+order.getCustumer().getFirstName() + " "+order.getCustumer().getsurname());  //getting custumer info from order
            System.out.println("your email is "+order.getCustumer().getEmailAdd());
            System.out.println("Your order has been successful");
            System.out.println("");
            System.out.println("Your order number is: "+order.getOrdernum());   //calling order method
            System.out.println("You billing address is: "+address.getBillingAddress());   //calling address method
            System.out.println("Your delivery Address is: "+address.getDeliveryAddress());
            System.out.println("");
            order.printItems();    //printing items from print method in order
            System.out.println("");
            
        }
        else    //if details were incorrect, notify user
        {
            System.out.println("Hello "+order.getCustumer().getFirstName() + " "+order.getCustumer().getsurname());
            System.out.println("your email is "+order.getCustumer().getEmailAdd());
            System.out.println("Your order has been unsuccessful");
            System.out.println("");
            System.out.println("You billing address is: "+address.getBillingAddress());
            System.out.println("Your delivery Address is: "+address.getDeliveryAddress());
            System.out.println("");
            System.out.println("Please check that your payment and order details are valid");
        }
    }
    
}
