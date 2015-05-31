
public enum CardColor {
	RED("Red"),
	BLUE("Blue"),
	YELLOW("Yellow"),
	GREEN("Green"),
	PURPLE("Purple"),
	ORANGE("Orange"),
	PINK("Pink"),
	BROWN("Brown");

	private String color;

	CardColor(String color){
		this.color = color;
	}

	public String toString(){
		return color;
	}

	public CardColor getCardColorBasedOnColor(String color){
		CardColor returnCardColor;

		for(CardColor c: CardColor.values()){
			
		}
		
		switch(color.toUpperCase()){
		case "YELLOW":
			returnCardColor = CardColor.YELLOW;
			break;
		default: 
			returnCardColor = CardColor.BLUE;

		}

		return returnCardColor;
	}


}
