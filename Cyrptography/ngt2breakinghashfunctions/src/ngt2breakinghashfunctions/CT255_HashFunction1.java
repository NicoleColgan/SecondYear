package ngt2breakinghashfunctions;

public class CT255_HashFunction1 {
	public static void main(String[] args) {
		long res = 0;
		int max=10000000; //max value for random function
		int min=0;
		int numCollisions=0;
		// number of hash colisions
		boolean valid = true;
		if (args != null && args.length > 0) { // Check for <input> value
			res = hashF1(args[0]); // call hash function with <input>
		if (res < 0) { // Error
			System.out.println("Error: <input> must be 1 to 64 characters long.");
		}
		else {
			System.out.println("input = " + args[0] + " : Hash = " + res);
			System.out.println("Start searching for collisions");
			// Your code to look for a hash collision starts here!
			while(valid) //only do this until valid is false
			{
				String initString = "hello"; //will append values to this string
				//generate random int with math random package
				int randomInt = (int)((Math.random()*(max-min+1))+min); 
				//add the int to the string
				String randomString = initString+randomInt; 
				long res2 = hashF1(randomString); //pass it into the hashing function
				//checking if hash value for this string is the same as for "Bamb0"
				if(res2==res) 
				{
					//if it is the same then print it out
					System.out.println("hash collision with: "+randomString+"\t Hash:"+res2);
					numCollisions++; // increases if there is a collisions
				}
				//when 10 collisions are found change valid to false and stop the loop
				if(numCollisions==10) 
					valid = false;
		
			}
			}
		}
		else  // No <input>
			System.out.println("Use: CT255_HashFunction1 <Input>");
		
		}
        
    private static long hashF1(String s){
    	//res in main must be changed to a long type
    	long ret = -1; //hash is converted to a longer value to make collisions less likely to occur
    	int i=0;
    	//added 4 to the array to make resulting has longer
    	long[] hashA = new long[]{1, 1, 1, 1, 1, 1, 1, 1}; 
    	String filler, sIn;
    	filler = new
    	String("ABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGH");
    	
    	if ((s.length() > 64) || (s.length() < 1)) { // String does not have required length
    		ret = -1;
    	}
    	else {
    		sIn = s + filler; // Add characters, now have "<input>HABCDEF..."
    		sIn = sIn.substring(0, 64); // // Limit string to first 64 characters
    		// System.out.println(sIn); // FYI
    		for (i = 0; i < sIn.length(); i++){
    			char byPos = sIn.charAt(i); // get i'th character
    			//adding each array index by a random number multiplied by a charachter
    			hashA[0] += (byPos * 17); // Note: A += B means A = A + B
    			hashA[1] += (byPos * 31);
    			hashA[2] += (byPos * 101);
    			hashA[3] += (byPos * 79);
    			hashA[4] += (byPos * 70); 
    			hashA[5] += (byPos * 12);
    			hashA[6] += (byPos * 14);
    			hashA[7] += (byPos * 90);
    		}
    		hashA[0] %= 255; // % is the modulus operation, i.e. division with rest
    		hashA[1] %= 255;
    		hashA[2] %= 255;
    		hashA[3] %= 255;
    		hashA[4] %= 255; // % is the modulus operation, i.e. division with rest
    		hashA[5] %= 255;
    		hashA[6] %= 255; //repeating steps for new array indexed
    		hashA[7] %= 255;
    		// adding new array indexes to the has value
    		ret = hashA[0] + (hashA[1] * 256) + (hashA[2] * 256 * 256) + (hashA[3] * 256 * 256 * 256)
    				+ (hashA[4] * 256 * 256 * 256 * 256) + (hashA[5] * 256 * 256 * 256 * 256 * 256)
    				+ (hashA[6] * 256 * 256 * 256 * 256 * 256 * 256)+ (hashA[7] * 256 * 256 * 256 * 256 * 256 *
    						256 * 256);
    		if (ret < 0)
    			ret *= -1;
    		}
    	return ret;
    }
    	
}
