
/**
 * class TransactionTest
 *
 * @author Nicole Colgan
 * @version v1.0
 */
public class TransactionTest
{
    //constructor method for test class
    public TransactionTest()
    {
        //empty constuctor - could be left out
    }
    //this is the main
    public static void main(String args[])
    {
        //creating an instance (object) of this class
        TransactionTest test = new TransactionTest();
        //each method contains a different transaction scenario
        test.transaction1();
        test.transaction2();
    }
    
    //scenario 1
    public void transaction1()
    {
        //making custumer object
        Custumer custumer = new Custumer("Nicole","Colgan","nicole@yahoo.com");
        
        //make shopping cart object and pass this custumer in 
        ShoppingCart shoppingCart = new ShoppingCart(custumer);
        
        //addinf items to the shopping cart constructor
        Item item1 = new Item("bike", 123456789);
        item1.setPrice(5);       //settinf price for items 
        shoppingCart.addItem(item1);   //adding items to shopping cart array
        Item item2 = new Item("horse", 123426789);
        item2.setPrice(9);
        shoppingCart.addItem(item2);
        Item item3 = new Item("desk", 123756889);
        item3.setPrice(3);
        shoppingCart.addItem(item3);
        
        shoppingCart.close();//locks the cart
        
        //adding address object
        Address address = new Address();
        address.setBillingAddress("Old road, Galway, hy891, Ireland");  //setting address
        address.setDeliveryAddress("New road, Galway, hys89, Ireland");
        
        custumer.add(address);  //adding this address to custumer
        
        //setting payment details 
        Payment payment = new Payment("MasterCard", "05/10/2021");
        payment.setPaymentDetails("1111 1111 1111 1111", "AIB");
        
        //making an order object and passing in a shopping cart
        Order order = new Order(shoppingCart);
        order.add(custumer);   //adding a custumer
        order.add(payment);    //adding a payment to order
        payment.add(custumer); //adding a custumer to payment
        order.processOrder();   //calling order method
        
        //making an email object and passing in custumer
        Email email = new Email(order);
        email.add(address);   //adding an address to email
        email.confirmEmail();   //calling confirmation email
        
        
    }
    //scenario 2
    public void transaction2()
    {
        Custumer custumer = new Custumer("Sarah","Lee","sarah@yahoo.com");
        ShoppingCart shoppingCart = new ShoppingCart(custumer);
        Item item1 = new Item("couch", 123456189);
        item1.setPrice(8);
        shoppingCart.addItem(item1);
        Item item2 = new Item("computer", 123426780);
        item2.setPrice(12);
        shoppingCart.addItem(item2);
        Item item3 = new Item("keyboard", 123256889);
        item3.setPrice(3);
        shoppingCart.addItem(item3);
        
        shoppingCart.printItems();    //user requests a print out of the items 
        System.out.printf("Total is: %.2f\n",shoppingCart.getTotal());   //printing total from shopping cart to 2 decimal places
        shoppingCart.removeItem(1);   //removing the second item
        shoppingCart.close();         //locking the cart
        
        Address address = new Address();
        address.setBillingAddress("Old road, Dublin, hy891, Ireland");
        address.setDeliveryAddress("New road, Dublin, hys89, Ireland");
        custumer.add(address);
        
        Payment payment = new Payment("MaserCard", "05/10/2021");    //putting in an invalid credit card type to trst payment validation methods
        payment.setPaymentDetails("1111 1111 1151 1511", "AIB");
        
        Order order = new Order(shoppingCart);
        order.add(custumer);
        order.add(payment);
        payment.add(custumer);
        order.processOrder();
        
        Email email = new Email(order);
        email.add(address);
        System.out.println("");   //neater output
        email.confirmEmail();
    }
   
}
