import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class HuffmanTreeTest {


	@Test
	public void testcode3() {
		HuffmanTree evaluator = new HuffmanTree("letter_frequency.txt");
		ArrayList<Code> codes = evaluator.getCodes();
		assertEquals("Code [ch=s, code=11111]", codes.get(26).toString());
	}	
	
	

	@Test
	public void encode3() {
		HuffmanTree evaluator = new HuffmanTree("letter_frequency.txt");
		ArrayList<Code> codes = evaluator.getCodes();
		assertEquals("00", evaluator.encode(" "));
	}

	@Test
	public void encode5() {
		HuffmanTree evaluator = new HuffmanTree("letter_frequency.txt");
		ArrayList<Code> codes = evaluator.getCodes();
		assertEquals("1111010011101110110001000110110011011110111100010", evaluator.encode("hotty toddy"));
	}
	@Test
	public void encode6() {
		HuffmanTree evaluator = new HuffmanTree("letter_frequency.txt");
		ArrayList<Code> codes = evaluator.getCodes();
		assertEquals("110001100111100011101001111111100110011110101000101001101011100111110101100000010011000100010010101011101111110010101000111001", evaluator.encode("four score and seven years ago"));
	}


	@Test(expected = InvalidHuffmanCodeException.class)
	public void encode9() {
		HuffmanTree evaluator = new HuffmanTree("letter_frequency.txt");
		ArrayList<Code> codes = evaluator.getCodes();
		String result = evaluator.encode("");
		fail("Did not catch invalid Huffman Code Exception");
	}

	
	@Test
	public void decode1() {
		HuffmanTree evaluator = new HuffmanTree("letter_frequency.txt");
		ArrayList<Code> codes = evaluator.getCodes();
		assertEquals("q", evaluator.decode("1100001011"));
	}
	@Test
	public void decode2() {
		HuffmanTree evaluator = new HuffmanTree("letter_frequency.txt");
		ArrayList<Code> codes = evaluator.getCodes();
		assertEquals("i", evaluator.decode("0111"));
	}
	@Test
	public void decode3() {
		HuffmanTree evaluator = new HuffmanTree("letter_frequency.txt");
		ArrayList<Code> codes = evaluator.getCodes();
		assertEquals("work hard play harder", evaluator.decode("110010100111101110000110011110101011101101110010000110110101010001000111101010111011011101011101"));
	}	


}
