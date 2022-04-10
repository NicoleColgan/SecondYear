
public class rainbowTable {
	public static void main(String[] args) {
		long res = 0;
		int i;
		String start=args[0]; //first pass in args[0]
		long hash1 = 895210601874431214L, hash2 = 750105908431234638L, 
				hash3 =111111111115664932L, hash4 = 977984261343652499L;
		
		if (args != null && args.length > 0) { // Check for <input> value
			start = args[0];
			if (start.length() != 8) {
				System.out.println("Input " + start + " must be 8 characters long -Exit");
			}
		else { //valid input
			for (i=0; i<10000; i++) //stop after 10'000 iterations
			{
				//first pass args[0] into function then pass in the reduced string
				res=hashFunction(start);
				//check if any of the hashes returned from the function match those above
				if(res==hash1){
					//if they match print the string that resulted in this hash and the
					//original password entered (that produced this chain)
					System.out.println("Hash: "+hash1+" matches password: "+start
							+"\nPassword at the start of the chain was: "+args[0]);
				}
				else if(res==hash2)
				{
					System.out.println("Hash: "+hash2+" matches password: "+start
							+"\nPassword at the start of the chain was: "+args[0]);
				}
				else if(res==hash3)
				{
					System.out.println("Hash: "+hash3+" matches password: "+start
							+"\nPassword at the start of the chain was: "+args[0]);
				}
				else if(res==hash4)
				{
					System.out.println("Hash: "+hash4+" matches password: "+start
							+"\nPassword at the start of the chain was: "+args[0]);
				}
				start = reductionFunction(res,i); //pasing hashed value and index into reduction function
			}
			//System.out.println("start value: "+args[0]+"\t end value: "+ start);
			//print initial value and end value
			}
		}
		else { // No <input>
			System.out.println("Use: RainbowTable <Input>");
		}

	private static long hashFunction(String s){
        long ret = 0;
        int i;
        long[] hashA = new long[]{1, 1, 1, 1};
        
        String filler, sIn;
        
        int DIV = 65536;
        
        filler = new String("ABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGH");
        
        sIn = s + filler; // Add characters, now have "<input>HABCDEF..."
        sIn = sIn.substring(0, 64); // // Limit string to first 64 characters

        for (i = 0; i < sIn.length(); i++) {
            char byPos = sIn.charAt(i); // get i'th character
            hashA[0] += (byPos * 17111); // Note: A += B means A = A + B
            hashA[1] += (hashA[0] + byPos * 31349);
            hashA[2] += (hashA[1] - byPos * 101302);
            hashA[3] += (byPos * 79001);
        } 
           
        ret = (hashA[0] + hashA[2]) + (hashA[1] * hashA[3]);
        if (ret < 0) ret *= -1;
        return ret;
    } 
    
    private static String reductionFunction(long val, int round) {  // Note that for the first function call "round" has to be 0, 
        String car, out;                                            // and has to be incremented by one with every subsequent call. 
        int i;                                                      // I.e. "round" created variations of the reduction function.
        char dat;                                                  
        
        car = new String("0123456789ABCDEFGHIJKLMNOPQRSTUNVXYZabcdefghijklmnopqrstuvwxyz!#");
        out = new String("");
      
        for (i = 0; i < 8; i++) {
            val -= round;
            dat = (char) (val % 63);
            val = val / 83;
            out = out + car.charAt(dat);
        }
        
        return out;
    }
}

