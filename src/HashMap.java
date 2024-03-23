import java.util.*;
public class HashMap implements HashMapADT{
	private String password;
	
	private long hash;
	private int key;
	private final int passwordLength = 4;
	ArrayList<ArrayList<PasswordMap>> rainbow;
	
	public HashMap() {
		password = "";
		hash = -1L;
		key = -1;
		initializeHashMap();
	}
	public HashMap(String str) {
		password = str;
		hash = hashCode(str);
		key = (int)(hash % 29);
		initializeHashMap();
		
	}
	
	private void initializeHashMap() {
		//Initialize an ArrayList of 29 elements - when dividing by 29 only remainders 0 - 28 are possible
		rainbow = new ArrayList<>();
		for(int i = 0; i < 29; i++) {
			rainbow.add(new ArrayList<PasswordMap>());
		}
		compute();		
	}
	
	public Long hashCode(String str) throws InvalidPasswordException{
		this.hash = 0L;
		if(str.length()!=passwordLength){
			throw new InvalidPasswordException();
		}
		
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)<97 || str.charAt(i)>122){
				throw new InvalidPasswordException();
			}
		}
		int l=str.length()-1;
		for(int i=0;i<str.length();i++) {
		hash+=(long) ((Math.pow(29, l--)*str.charAt(i)));
		}
		return hash;  
	}
	
	public void compute() {
		
		//TODO:  Add code to compute all possible 4-letter passwords - store in rainbow
		
		for(int a=0;a<26;a++){
			for(int b=0;b<26;b++){
				for(int c=0;c<26;c++){
					for(int d=0;d<26;d++){
						String temp=(char)(97+a)+""+(char)(97+b)+""+(char)(97+c)+""+(char)(97+d)+"";
						int k=(int)(hashCode(temp)%29);
						rainbow.get(k).add(new PasswordMap(hashCode(temp),temp));
					 
						
				}
			}
		}
		}
	

		
			}
	
			
		
		// Begin your possible passwords with aaaa and end with zzzz
		// Use hashCode(pwd) % 29 to determine the key (index) of where this element should be added to rainbow's ArrayList of ArrayLists
		// You will need to cast as int, such as key = (int) hashCode(pwd)%29  - key is the index of where to add this element in the rainbow ArrayList of ArrayLists


	public Long hash(String pwd) {
		
		//TODO:  Return the hashcode for a given password
		// First, hash the password: int key = (int)(hashCode(pwd) % 29);
		if(pwd.length()!=passwordLength){
			throw new InvalidPasswordException();
		}
		for(int i=0;i<pwd.length();i++){
			if(pwd.charAt(i)<97 || pwd.charAt(i)>122){
				throw new InvalidPasswordException();
			}
		}
	
		int x=0;
		
		key=(int)(hashCode(pwd)%29);
		
		ArrayList<PasswordMap> temp = rainbow.get(key);
	    for(int i=0; i<temp.size(); i++){
	      if((pwd).equals(rainbow.get(key).get(i).getPassword())){
	        x=i;
	      }
	      
	    }
		// Use this k9ey to determine which element in the rainbow table you should be traversing to find the hash code
		// Recall rainbow is an ArrayList of ArrayLists!!
		
		 return rainbow.get(key).get(x).getHash();// temp - delete once hash method is implemented.x`
	
	}
	
	
	public String hack(Long pwdHash) {
		String pwd="";
		int j=0;
		int key=(int)((pwdHash)%29);
		
		if(pwdHash<hash("aaaa") || pwdHash>hash("zzzz")) {
			throw new InvalidPasswordException();
		}
		ArrayList<PasswordMap> temp=rainbow.get(key);
		for(int i=0;i<temp.size();i++){
			if((Math.abs(pwdHash - rainbow.get(key).get(i).getHash())<.001)){
				j=i;
			}
			}
		
		//TODO:  Given a hashed password, pwdHash, determine the password
		// When identifying a correct hashed password, you will need to look at a difference RATHER THAN ==
		// That is,
		
		//if (Math.abs(pwdHash - rainbow.get(key).get(i).getHash())<.001)  -  you've found your password!!
		// Note:  key is the location of the rainbow list you should be traversing:  key = (int)((pwdHash) % 29);
		

		return rainbow.get(key).get(j).getPassword();
	}
	
	@Override
	public String toString() {
		return password + ": " + hash;
	}
}
