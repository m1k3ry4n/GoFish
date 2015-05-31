

public class Player {
	private Hand hand;
	private String playerName;
	private CardCollection cardsOnTheTable;
	
	public Player(String playerName){
		setPlayerName(playerName);
		setHand(new Hand());
		cardsOnTheTable = new CardCollection();
	}
	
	public Player(Hand hand){
		setHand(hand);
	}
	
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public void setHand(Hand hand){
		this.hand = hand;
	}
	
	public Hand getHand(){
		return hand;
	}
	
	public void getCard(Card card){
		hand.addCard(card);
	}
	
	public void getCardAndPutMatchingPairOnTable(Card card){
		putCardOnTable(card);
		putCardOnTable(giveUpCardWithColor(card));
	}
	
	public String toString(){
		String player = "Player: " + playerName;
		player += " , Hand: " + hand.toString();
		return player;
	}
	
	public int getNumCardsOnTheTable(){
		return cardsOnTheTable.getSize();
	}
	
	public boolean isHandEmpty(){
		return hand.getSize() == 0;
	}
	
	public void putCardOnTable(Card card){
		System.out.println("Putting card on table: " + card.toString());
		cardsOnTheTable.addCard(hand.removeCard(card));
	}
	
	public String cardsOnTableToString(){
		String cardsOnTableString = "";
		for(Card card: cardsOnTheTable){
			cardsOnTableString += card.toString() + " ";
		}
		return cardsOnTableString;
	}
	
	public boolean hasCardColor(CardColor color){
		for(Card card: hand){
			if(card.hasCardColor(color)){
				return true;
			}
		}
		return false;
	}
	
	public Card giveUpCard(Card card){
		return hand.removeCard(card);
	}
	
	public Card giveUpCardWithColor(CardColor color){
		for(Card card: hand){
			if(card.hasCardColor(color)){
				return card;
			}
		}
		return null;
	}
	
	public Card giveUpCardWithColor(Card card){
		return giveUpCardWithColor(card.getCardColor());
	}
}
