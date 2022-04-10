import java.util.ArrayList;
import java.util.Random;

/**
 * Order class
 *
 * @author Nicole Colgan
 * @version v1.0
 */
public class Order
{
    private Payment payment;            //an order has a payment (this information is needed by the email class which only has an order reference variable)
    private final long orderNumber;    //ordernumber cant change
    private ShoppingCart shoppingCart;   //order HAS A shopping cart whos items need to be transferred into it
    private Custumer custumer;           //order Has  A custumer that it needs information about 
    private ArrayList<Item> orderItems;   //arraylist of items in the order
    
    
   public Order(ShoppingCart shoppingCart)   //shopping cart passed into the constructor from the test class
   {
       orderItems = new ArrayList<>(); //initialising an empty arrat
       this.shoppingCart= shoppingCart; //assosiating a shopping cart with this order
       orderNumber=setOrderNumber();    //call function for creating an order number
   }
   //add a custumer object
    public void add(Custumer custumer)
    {
       this.custumer=custumer;
    }
    //give other class access to the custumer object
    public Custumer getCustumer()
    {
        return custumer;
    }
    //add payment object
    public void add(Payment payment)
    {
       this.payment=payment;
    }
    //give other class access to the payment object
    public Payment getPayment()
    {
        return payment;
    }
    //give other class access to the shopping cart object that was initialised in main
    public ShoppingCart getShoppingCart()
    {
        return shoppingCart;
    }
   
    //here we will pass items from shopping cart array into order items array
   public boolean processOrder()
   {
       //first check to see if the shopping cart is closed 
       if(shoppingCart.close()==true)
       {
           for (int i=0; i<shoppingCart.numItems();i++)
           {
               orderItems.add(shoppingCart.getItem(i));    //if condition was met get the items from the shopping cart array and add it to this array one by one
           }
           shoppingCart.clear();//remove everything from the shoppingcart after 
           return true;      //return true if the order  was processed
       }
       else 
       {
           System.out.println("Order cannot be processed as shopping cart is not closed");  //if the shopping cart is not closed print 
           return false;    //return false if processing failed 
       }
       //if its not then print some message
   }
   //confirm order
   public boolean confirmOrder()
   {
       if(processOrder()==true && custumer!=null &&custumer.getAddress()!=null)  //if conditions are met confirm the order and return true 
       {
           return true;
       }
      
       return false;   //otherwise return false
   }
   //generate random order number
   public long setOrderNumber()
   {
       Random orderNumber = new Random();
       long value = orderNumber.nextLong();
       //if generated number is negative, make it positive bu multiplying it by -1
       if(value<0)
       {
           value = value*-1;
           return value;
        }
       return value; //if its not negatuve return it as is
   }
   //access to order number
   public long getOrdernum()
   {
       return orderNumber;
   }
   //can call this method to check the order status
   public void getStatus()
   {
       //if the order was confirmed this can print 
       if(confirmOrder()==true)
       {
           System.out.println("Your order has been confirmed");
       }
       else
       {
           System.out.println("Your order was not confirmed");
       }
   }
   //method for printing items in order array
   public void printItems()
   {
       for (int i=0; i<orderItems.size(); i++)
       {
           System.out.println("Name of item purchased:");
           System.out.println(orderItems.get(i).getName());     //get name from items class
          
           System.out.println("Item details:");
           System.out.println(orderItems.get(i));     //get details from item class
           System.out.println("");
       }
   }
   
   
}
