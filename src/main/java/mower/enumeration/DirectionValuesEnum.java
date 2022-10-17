package mower.enumeration;

public enum DirectionValuesEnum {
	
	NORD ('N'),	 SUD ('S'),	 EST ('E'),	 OUEST ('W'), AVANT ('A'), GAUCHE ('G'), DROITE ('D');
	
	private final char value;

	  private DirectionValuesEnum(char value) {
	    this.value = value;
	  }

	  public char getValue() {
	    return this.value;
	  }
}
