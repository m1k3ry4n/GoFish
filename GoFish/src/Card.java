
public class Card {
	
	private CardColor cardColor;
	
	public Card(CardColor cardColor){
		setCardColor(cardColor);
	}
	
	public void setCardColor(CardColor cardColor){
		this.cardColor = cardColor;
	}
	
	public CardColor getCardColor(){
		return cardColor;
	}
	
	public String toString(){
		return cardColor.toString();
	}
	
	public boolean equals(Card c){
		return cardColor == c.getCardColor();
	}
	
	public boolean hasCardColor(CardColor color){		
		return color == cardColor ? true : false;
	}
}
