
public class Letter {
	char letter;
	double frequency;
	
	public Letter(char letter, double frequency) {
		super();
		this.letter = letter;
		this.frequency = frequency;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "Letter [letter=" + letter + ", frequency=" + frequency + "]" + "\n";
	}

}
