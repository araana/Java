/* This HuffmanTree class has been created from a scaled down version of
 * LinkedBinaryTree class from the textbook.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HuffmanTree implements HuffmanADT
{
    protected BinaryTreeNode<Letter> root; 
    protected int modCount;
	ArrayList<Letter> list = new ArrayList<Letter>();
    
    /**
     * Creates a Huffman tree using the character set and frequency distribution
     * given in the file with name passed as a parameter.
     * 
     */
    public HuffmanTree(String filename) 
    {
        root = null;
		// Read frequency data from file to create list of Letter objects
        try {
        		Scanner letter_file = new Scanner (new File(filename));
        		while (letter_file.hasNext()) {
        			String line = letter_file.nextLine();
        			list.add(new Letter(line.charAt(0), Double.parseDouble(line.substring(2))));
        		}
        		// Construct the Huffman tree using the list of Letters
        		build();
        }
        catch (FileNotFoundException e) {
        		System.out.println("Frequency file not found.");
        		System.exit(0);
        }

    }
       
    private void build() {
    		// Make leaf nodes in the tree/forest for each character
    		ArrayList<BinaryTreeNode<Letter>> nodeList = new ArrayList<BinaryTreeNode<Letter>>();
    		BinaryTreeNode<Letter> temp = null;
    		for (int i=0; i<list.size(); i++) {
    			temp = new BinaryTreeNode<Letter>(new Letter(list.get(i).getLetter(), 
    					     list.get(i).getFrequency()));
    			nodeList.add(temp);
    		}
    		/* Use standard idea of Huffman encoding to build tree from leaf nodes.
    		 * Repeatedly, find the two subtrees with minimum weight and join them.
    		 * Internal nodes don't use the "letter" field, so just make them a constant
    		 * (#).  The frequency used for the internal node is the sum of the frequency
    		 * of the two children.  Stop when one node left in forest--it is a tree!
    		 */
    		BinaryTreeNode<Letter> node1, node2;
    		while (nodeList.size() > 1) {
    			node1 = getMin(nodeList);
    			node2 = getMin(nodeList);
    			Letter internalElement = new Letter('#', node1.getElement().getFrequency() +
	                    node2.getElement().getFrequency());
    			BinaryTreeNode<Letter> internal = new 
    					BinaryTreeNode<Letter>(internalElement); 
    			internal.setLeft(node1);
    			internal.setRight(node2);
    			nodeList.add(internal);
    		}
    		// The one remaining node is the root
    		root = nodeList.get(0);
    }
    
    private BinaryTreeNode<Letter> getMin(ArrayList<BinaryTreeNode<Letter>> nodes) {
    	     int min=0;   // index of min
    	     // Find the node in the forest with the least frequency
    	     for (int i=1; i<nodes.size(); i++) {
    	    	      if (nodes.get(i).getElement().getFrequency() < 
    	    	    		  nodes.get(min).getElement().getFrequency()) {
    	    	    	  		min = i;
    	    	      }
    	     }
    	     // Save, remove, then return the smallest node
    	     BinaryTreeNode<Letter> smallest = nodes.get(min);
    	     nodes.remove(min);
    	     return smallest;
    }
    
    public Letter getRootElement() throws EmptyCollectionException
    {
        if (root == null)
        		throw new EmptyCollectionException("Huffman Tree");
        else return root.getElement();
    }
    
    protected BinaryTreeNode<Letter> getRootNode() throws EmptyCollectionException
    {
        if (root == null)
        		throw new EmptyCollectionException("Huffman Tree");
        else return root;
    }
    
    public BinaryTreeNode<Letter> getLeft()
    {
        return root.left;
    }
    
    public BinaryTreeNode<Letter> getRight()
    {
        return root.right;
    }
    
    public boolean isEmpty() 
    {
        return (root == null);
    }

    /* This method returns an ArrayList of the codes in the Huffman Tree.
     * The Code class has a character and its corresponding encoding.  In the
     * tree, left edges are designated as 0 and right edges as 1.
     * 
     * DO NOT CHANGE THIS METHOD, but you need to write the traverse method.
     */
    public ArrayList<Code> getCodes() {
    		ArrayList<Code> code = new ArrayList<Code>();
    		if (root == null) return null;
    		traverse(code, root.left, "0");
    		traverse(code, root.right, "1");
    		return code;
    }

    /* Recursive method to traverse the Huffman tree, and for each leaf node,
     * add a Code record to the ArrayList.
     */
    private void traverse(ArrayList<Code> code, BinaryTreeNode<Letter> node,
    		             String prefix) {
    		// TODO:  Fill in this method

    	if(node==null) return;
    		
        if (node!=null){
            traverse(code, node.left, prefix+"0");//update prefix every time with +"0" you traverse left
        }
        if (node!=null){
            traverse(code, node.right, prefix+"1");//update prefix every time with +"1" you traverse right
        }
        
        //if the letter is not '#'(internal node), then add the letter to the code arryaList
        if(node.getElement().getLetter() != '#')
         code.add(new Code(node.getElement().getLetter(),prefix ));
    		
    }

    

    /* The encode method can use the getCodes method above to produce a look up
     * table, then step through the parameter string and simply "look up" the code 
     * for each character and append the code to the end of the encoded string.  A
     * sequential search through the ArrayList for each character is fine since 
     * there are only 27 elements in the list.
     */
	@Override
	public String encode(String str) {
		if(str.length()==0){
			throw new InvalidHoffmanCodeException();	
			// TODO: fill in this method
		String encodedString = "";

        // iterate through given string
       for (int x = 0; x < str.length(); x++) {
       	
            // iterate through the code arrayList
            for (int i = 0; i<getCodes().size(); i++) {
           	 
                // if char in string matches code ch, add codes to the encodedString
            	if(str.charAt(x)== getCodes().get(i).getCh()){
           	   
           	   //create a look up table using hashmaps and getCodes() method
            		Map<Character, String> map = new HashMap<>();
           	 
            		map.put(getCodes().get(i).getCh(), getCodes().get(i).getCode());
               
            		encodedString+= map.get(getCodes().get(i).getCh());
            	}
            
            }
       }
        
       return encodedString;
	}
	
    /*
     * The decode method accepts a string of 0's and 1's and uses the Huffman
     * tree to determine the original string.  Because it is a prefix code, you
     * can start at the root of the Huffman tree and traverse left for 0 and right
     * for a 1 until a leaf is reached.  The character associated with that code
     * (stored in the node) can be appended to the decoded string, then
     * reset back to the root and continue stepping through the huffString 
     * parameter until the end of that string is reached.
     * 
     */
	@Override
	public String decode(String huffString) {
		// TODO: Fill in this method
		String decodedString = "";
	     BinaryTreeNode<Letter> c = root;
	    
	     //iterate through the Huffman String
	     for( int i = 0; i<huffString.length(); i++){
		 
	    	 if(huffString.charAt(i)=='0'&& c.left!=null){
	    		 c = c.left;
	    	 }
		 
	    	 if(huffString.charAt(i)=='1' && c.right!= null){
	    		 c = c.right;
	    	 }
		 
	    	 if(c.left==null && c.right == null ){
			
	    		 decodedString+= c.getElement().getLetter();
			 
	    		 //update the root after you get the element
	    		 c = root;
	    	 }
			 
	     }
		return decodedString;
	
	}
}

