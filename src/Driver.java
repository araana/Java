import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		HashMap hash1 = new HashMap();
		
		Long pwdHash = hash1.hash("yarn");
		System.out.println("Found hashed password: " + pwdHash);
	
		String pwd = hash1.hack((long)2624641);
		System.out.println("\nHash 2624641 is the password " + pwd);
		
		HashMap hash2 = new HashMap("aaaa");
		hash2.hash("aaaa");
		System.out.println(hash2);
		hash2.toString();
		long h=0;
	
		
	System.out.println();
		Scanner scan;
		try {
			scan = new Scanner(new File("creditCardFive.txt"));
		
		while(scan.hasNextLine()) {
			String [] token = scan.nextLine().split(",");
			 h=Long.parseLong(token[2]);
			HashMap newhash3=new HashMap();
			System.out.println(token[0]+","+newhash3.hack(h)+","+token[2]);
		}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}		
	}


