import java.util.ArrayList;  //need to import this in order to use arrays
import java.util.Random;     //need to import this in order to generate random number
/**
 * Shopping cart class
 *
 * @author Nicole Colgan
 * @version v1.0
 */
public class ShoppingCart
{
    private ArrayList<Item> cart;     //arraylist of items in a shopping cart 
    private long cartId;
    private float time;
    private String items;         //shopping cart has items 
    private float total;
    private boolean allowItem;
    private Item item;         //shopping cart has items
    private Custumer custumer; //shopping cart has cutumers
    public ShoppingCart(Custumer custumer)    //shopping cart takes in a custumer object 
    {
        //constructor 
        this.custumer = custumer;
        cart = new ArrayList<>();     //initialising arraylist
        allowItem= false;             //initially set this to false
        total = 0;
        cartId=getCartId();     //making a cart ID
    }
    //generate random cart ID
     public long getCartId()
    {
       Random cartId = new Random();
       long value = cartId.nextLong();
       return value;
    }
    //giving email and other classes access to custumer information
    public Custumer getCustumer()
    {
        return custumer;
    }
    //add items to the array
    public void addItem(Item item1)
    {
        if(allowItem == false)   //check if we are permitted to add an item
        {
            cart.add(item1);     //using arraylist add methos
        }
        else 
        {
            //if we cant add an item, notify the user
            System.out.println("Sorry the shopping cart is closed. This item cannot be added");
        }
    }
    public Item getItem(int index)   //access an item in the array
    {
        //reads in an index and returns the item in that index
        if(cart.get(index)!=null)    //making sure this item in the array exists
        {
            return cart.get(index);
        }
        else
        {
            System.out.println("Item does not exist");   //if we didnt find an item, notify the user
            return null;                                 //return nothing
        }
    }
    public int numItems()
    {
        return cart.size();    //finding thr size of array with arraylist function size()
    }
   //remove items from the array
    public boolean removeItem(int index)   //index of the item we want to remove
    {
        //checking if the entry exist in the array and then removing it
        if(cart.get(index)!=null)
        {
            cart.remove(index);
            return true;             //returning true to indicate that the item was removed
        }
        //else item was not removed
        return false;
    }
    //print items
    public void printItems()
    {
        
        for(int i=0; i<numItems(); i++)
        {
            System.out.println("Name: "+ getItem(i).getName());     //array is of type item so we have access to item class methods 
        }
    }
    //calculate the total
    public float getTotal()
    {
        //return item.total;
        for(int i=0; i<numItems(); i++)
        {
            total+=getItem(i).getPrice();       //getting price of each item and adding it to total
        }
        return total;    //returning total
    }
    //lock items into cart. items cannot be removed or added if this method is called
    public boolean close()
    {
        allowItem = true;
        return allowItem;
    }
    //remove everything from the cart
    public boolean clear()
    {
        cart.clear();  //arraylist function to remove every entry
        return true;   //return true to indicate that the cart has been cleared
    }
    
}
