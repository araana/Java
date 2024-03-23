import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class HashMapTest {
	@Test
	public void testhashCode1() {
		HashMap evaluator = new HashMap();
		Long result = evaluator.hashCode("aaaa");
		assertTrue(result == 2450220);
	}
	
	
	@Test(expected=InvalidPasswordException.class)
	public void testhashCode6() {
		HashMap evaluator = new HashMap("");
		fail("InvalidPasswordException not caught");
	}
	
	@Test(expected=InvalidPasswordException.class)
	public void testhashCode7() {
		HashMap evaluator = new HashMap("aaa!");
		fail("InvalidPasswordException not caught");
	}	
	
	

	@Test
	public void testhash2() {
		HashMap evaluator = new HashMap("zzzz");
		Long result = evaluator.hash("zzzz");
		assertTrue(result == 3081720);
	}
	


	@Test(expected=InvalidPasswordException.class)
	public void testhash5() {
		HashMap evaluator = new HashMap();
		Long result = evaluator.hash("mmm");
		fail("InvalidPasswordException not caught");
	}	
	
	@Test(expected=InvalidPasswordException.class)
	public void testhash6() {
		HashMap evaluator = new HashMap();
		Long result = evaluator.hash("zzzzz");
		fail("InvalidPasswordException not caught");
	}	
	
	
	@Test
	public void testhack2() {
		HashMap evaluator = new HashMap();
		assertEquals("hard", evaluator.hack(2621439L));
	}

	
	@Test(expected=InvalidPasswordException.class)
	public void testhack4() {
		HashMap evaluator = new HashMap();
		String result = evaluator.hack(30360622L);
		fail("InvalidPasswordException not caught");

	}
	@Test
	public void testfile1() throws FileNotFoundException {
		HashMap evaluator = new HashMap();
			String hashedPasswords = "";
		String [] oneLine;
		Scanner in = new Scanner(new File("creditCardFive.txt"));
		while(in.hasNext()){
			oneLine = in.next().split(",");
			hashedPasswords += evaluator.hack(Long.parseLong(oneLine[2])) + "\n";	
		}
		assertEquals("sbpc\nckex\nqili\nxhla\nfuiq\n", hashedPasswords);
	}

}
