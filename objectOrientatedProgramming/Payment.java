
/**
 * Payment class
 *
 * @author Nicole Colgan
 * @version v1.0
 */
public class Payment
{
    private boolean valid;        //fields of payment
    private String ccType;
    private String ccNum;
    private String date;
    private String bankName;
    private Custumer custumer;     //payment has a custumer (needs info from the custumer)
    private String masterCard;
    private String visa;
   public Payment(String ccType, String date)     //takes in a credit card type and the date of the credit card
   {
       this.ccType = ccType;
       this.date = date;
       masterCard = "MasterCard";     //setting initial values for the equals method to compare against
       visa = "Visa";
   }
   //add the custumer object to this payment
   public void add(Custumer custumer)
   {
       this.custumer=custumer;
   }
   //access to the custumer object
   public Custumer getCustumer()
   {
       return custumer;
   }
   //passing in the payment details from the test class
   public void setPaymentDetails(String ccNum, String bankName)
   {
       this.ccNum = ccNum;
       this.bankName = bankName;
   }
   //access to these details
   public String getccNum()
   {
       return ccNum;
   }
   public String getBankName()
   {
       return bankName;
   }
   //validate payment and return a true/ false which can be called from email
   public boolean isValid()
   {
       //if the validate credit card method returns true and the validate card number returns true, then return true
       if(ValidccType()==true && validccNum()==true)
       {
           return true;
       }
       //otherwise return false
       return false;
   }
   //check if the credit care type is correct
   public boolean ValidccType()
   {
       //checking user has either entered mastercard or visa
       if (ccType.equals(masterCard) || ccType.equals(visa))
       {
           return true;   //return true if its either of these
       }
       //otherwise return false
       return false;
   }
   //checking if the credit card has the correct number of digits (including spaces)
   public boolean validccNum()
   {
       if(ccNum.length()==19)    //using length method
       {
           return true;   //return true if its correct
       }
       return false;   //otherwise return false
   }
}
