import java.util.ArrayList;

public interface HuffmanADT {
	public BinaryTreeNode<Letter> getLeft();
	public BinaryTreeNode<Letter> getRight();
	public boolean isEmpty();
	public ArrayList<Code> getCodes();
	public String encode(String str);
	public String decode(String huffString);	
}
