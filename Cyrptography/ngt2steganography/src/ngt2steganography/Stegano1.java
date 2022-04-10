package ngt2steganography;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stegano1 {
	/*
	 * Constructor for objects of class Stegano1
	 */
	public Stegano1()
	{
	}
	public static void main(String[] args) {
		String arg1, arg2, arg3, arg4;
		Boolean err = false;
		if (args != null && args.length > 1) { // Check for minimum number of arguments
			arg1 = args[0];
			arg2 = args[1];
			if (arg2 == "") {
				err = true;
			}
			else if ((arg1 == "A") && (args.length > 3)){
				// Get other arguments
				arg3 = args[2];
				arg4 = args[3];
				if (arg3 == "" || arg4 == "") {
					err = true;
				}
				else { //valid input
					// Hide bitstring
					hide(arg2, arg3, arg4);
				}
			}
			else if (arg1 == "E"){
				// Extract bitstring from text
				retrieve(arg2);
			}
			else {
				err = true;
			}
		}
		else {
			err = true;
		}
		if (err == true) {
			System.out.println();
			System.out.println("Use: Stegano1 <A:E><Input File><OutputFile><Bitstring>");
			System.out.println("Example: Stegano1 A inp.txt out.txt 0010101");
			System.out.println("Example: Stegano1 E inp.txt");
		}
	}
	
	static void hide(String inpFile, String outFile, String binString) {
		BufferedReader reader;
		BufferedWriter writer;
		boolean addBit = true;
		Character zero = new Character('0'); //to check for character equality
		Character one = new Character('1');
		int i = 0;
		try{
			reader = new BufferedReader(new FileReader(inpFile));
			writer = new BufferedWriter(new FileWriter(outFile));
			String line = reader.readLine();
			while (line != null) {
				if(addBit == true) //initially set to true
				{
					char findChar = binString.charAt(i); //get i'th bit in string
					char findChar2 = binString.charAt(i+1);
					if(findChar == zero && findChar2==zero) //check if char i and i++ ==00
					{
						line+=" "+"\t"; //one tab corrosponds to a 0
					}
					else if(findChar == zero && findChar2 == one) //checking if character i and ++i = 01
					{
						line+=" "+"\t"+"\t"; //two tabs corrospond to a one
					}
					else if (findChar == one && findChar2 == one) //checking if character i and ++i = 11
					{
						line+=" "+"\t"+"\t";
					}
					else if (findChar == one && findChar2 == zero) //checking if character i and ++i = 10
					{
						line+=" "+"\t";
					}
						i=i+2; //go to the next two bits
					if(i == binString.length())
					{
						addBit =false;
					}
				}
				// Store amended line in output file
				writer.write(line);
				writer.newLine();
				// read next line
				line = reader.readLine();
				}
				reader.close();
				writer.close();
				} catch (IOException e) {
				e.printStackTrace();
				}

	}
	
	static void retrieve(String inpFile) {
		BufferedReader reader;
		String inputBitString ="";
		try {
			reader = new BufferedReader(new FileReader(inpFile));
			String line = reader.readLine();
			while (line != null) {
				if(line.length()>1) //checking the line is not just a "\n"
				{
					//9 is the asci value of a tab
					if(Character.isWhitespace(line.charAt(line.length()-3)) &&
							Character.isWhitespace(line.charAt(line.length()-2)) && line.charAt(line.length()-1)==9)
					{
						inputBitString+="10"; // if line ends in " " + " " + "\t"
					}
					else if(Character.isWhitespace(line.charAt(line.length()-3)) && line.charAt(line.length()-
							2)==9 && line.charAt(line.length()-1)==9)
					{
						inputBitString+="01"; // if line ends in " " + "\t" + "\t"
					}
					else if(Character.isWhitespace(line.charAt(line.length()-4)) &&
							Character.isWhitespace(line.charAt(line.length()-3)) && line.charAt(line.length()-2)==9 &&
							line.charAt(line.length()-1)==9)
					{
						inputBitString+="11"; // if line ends in " " + " " + "\t" +"\t"
					}
					else if(Character.isWhitespace(line.charAt(line.length()-2)) && line.charAt(line.length()-
							1)==9)
					{
						inputBitString+="00"; // if line ends in " " + "\t"
					}
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
			System.out.println("Hidden bit-vector in this text is: "+inputBitString); //print out the bit string
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
