import java.util.Iterator;
import java.util.LinkedList;

public class CardCollection implements Iterable<Card> {

	LinkedList<Card> cardList;

	public CardCollection(){
		cardList = new LinkedList<Card>();
	}

	public void addCard(Card card){
		cardList.add(card);
	}

	public Card removeCard(Card card){
		Card cardInList = null;
		if (cardList.contains(card)){
			cardInList = cardList.get(cardList.indexOf(card));
			cardList.remove(card);
		}
		return cardInList;
	}

	public boolean hasCardColor(CardColor cardColor){
		Card searchCard = new Card(cardColor);
		return cardList.contains(searchCard);
	}
	
	public String toString(){
		String cardCollectionString = "";
		Iterator<Card> cardMapItr = cardList.iterator();
		
		while(cardMapItr.hasNext()){
			cardCollectionString += cardMapItr.next().getCardColor().toString();
			if(cardMapItr.hasNext()){
				cardCollectionString += ", ";
			}
		}
		return cardCollectionString;
	}
	
	public int getSize(){
		return cardList.size();
	}
	
	public Card getCardFromList(int index){
		return cardList.get(index);
	}

	@Override
	public Iterator<Card> iterator() {
		return cardList.iterator();
	}
}
