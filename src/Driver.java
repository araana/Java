import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Start");
		// Instantiate LinkedBinaryTree and build Huffman tree from frequency file
		HuffmanTree huff = new HuffmanTree("letter_frequency.txt");
		
		// Get the codes generated and print table
		ArrayList<Code> codes = huff.getCodes();
		System.out.println("Size of codes is " + codes.size());
		for (Code c: codes) {
			System.out.println(c);
		}

		
		// Test the encoding
		Scanner in = new Scanner(System.in);
		boolean done = false;
		do {
			System.out.print("Enter a string (END to exit) --> ");
			String input = in.nextLine();
			input = input.toLowerCase();
			// Remove everything except lowercase letters: a-z & spaces: \s
			// ^ means any character but the following...
			input = input.replaceAll("[^a-z\\s]", "");
			
			if (!input.equals("end")) {				
				// Get and display encoding of string, with length
			    String encoding = huff.encode(input);
			    System.out.printf("Original string length is %d\n", input.length());
			    System.out.printf("Encoded string (length %d), compressed %5.2f\n",
			    		encoding.length(), encoding.length()/16.0);   // Recall that characters are normally 2 bytes
			    System.out.println(encoding);
				// Get and display decoding of encoded string
			    System.out.println();
			    System.out.println("Now we try to reconstruct the original from the encoded string");
			    String original = huff.decode(encoding);
			    if (original.equals(input)) {
			    		System.out.println("It worked! String is: " + original);
			    }
			    else {
			    		System.out.println("Oops. There was a problem.  Decoded string is: " + original);
			    }
			}
			else done = true;
		} while (!done);
		
		
	}
}
