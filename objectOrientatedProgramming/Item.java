
/**
 * Item class
 *
 * @author Nicole Colgan
 * @version v1.0
 */
public class Item
{
    private String name;
    private int price;
    private long itemId;
    public Item(String itemName,long id)   //have to pass item name and id into the constructor
    {
        name = itemName;
        itemId = id;
    }
    //method to obtain name 
    //dont need setter methods because values were initialised in the constructor
    public String getName()
    {
        return name;
    }
    public long getItemid()
    {
        return itemId;
    }
    //method where value is passed in to price from test class
    public void setPrice(int price)
    {
        this.price = price;
    }
    public int getPrice()
    {
        return price;
    }
    //this is the same thing as a print method but this is essentially a string representation of the class
    //this must be an inbuilt Object function 
    @Override
    public String toString()
    {
        String out = "Item Id: "+ itemId + "\tPrice: "+ price;
        return out;
    }
    
}
