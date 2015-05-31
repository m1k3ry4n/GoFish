import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class Deck extends CardCollection{

	Stack<Card> deck;
	int DEFAULT_NUMBER_OF_EACH_CARD = 5;

	public Deck(){
		super();
		fillCardList();
		deck = new Stack<Card>();
		shuffleDeck();
	}

	/**
	 * Default method for determining the cards that will be in the deck. 
	 * Fills the cardMap, which will be used to fill the deck.
	 */
	public void fillCardList(){

		// get array with every color
		CardColor[] cardColorArray = CardColor.values();

		// add an arbitrary default number of each color
		for (int i=0; i<cardColorArray.length ; i++){
			for(int j=1; j<=DEFAULT_NUMBER_OF_EACH_CARD; j++){
				addCard(new Card(cardColorArray[i]));
			}
		}
	}

	/**
	 * Resets deck to its original values and shuffles it.
	 */
	public void shuffleDeck(){
		setDeck();
		Collections.shuffle(deck);
	}

	public void setDeck(){
		deck = new Stack<Card>();
		Iterator<Card> itr = cardList.iterator();
		while(itr.hasNext()){
			deck.push(itr.next());
		}
	}

	public Card getNextCardFromDeck(){
		if(deck.isEmpty()){
			return null;
		} else {
			return deck.pop();
		}
	}

	/**
	 * Returns the deck by popping each element from the stack. Note that popping all
	 * elements off of the stack destroys it, so a temporary stack holds all elements
	 * popped off of the deck. When the deck is empty it is refilled by popping all
	 * elements of the temporary stack back into the deck.
	 * @return
	 */
	public String deckToString(){
		Stack<Card> tempDeck = new Stack<Card>();
		String deckContents = "";

		if(deck.isEmpty()){
			deckContents += "THE DECK IS EMPTY";
		} else {
			deckContents += "Deck size: " + deck.size() + ", Colors: ";
		}

		while(!deck.isEmpty()){
			Card tempCard = deck.pop();
			tempDeck.push(tempCard);
			deckContents += tempCard.toString();
			if(!deck.isEmpty()){
				deckContents += ", ";
			}
		}

		while(!tempDeck.isEmpty()){
			deck.push(tempDeck.pop());
		}
		
		return deckContents;
	}
	
	public boolean isEmpty(){
		return deck.isEmpty();
	}
}
