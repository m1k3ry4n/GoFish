import java.util.Iterator;
import java.util.LinkedList;


public class ComputerPlayer extends Player{

	public ComputerPlayer(String playerName){
		super(playerName);
	}

	/**
	 * If there are cards in the computer's hand, the computer just asks for 
	 * the first card in its hand.
	 */
	public CardColor askForColor() {
		if (!isHandEmpty()){
			return getHand().getCardFromList(0).getCardColor();
		} else {
			return null;
		}
	}
	
	public Player chooseAPlayerToAsk(LinkedList<Player> players){
		Iterator<Player> playerItr = players.iterator();
		Player playerToAsk = null;
		while(playerItr.hasNext() && playerToAsk == null){
			Player nextPlayer = playerItr.next(); 
			if(!this.equals(nextPlayer)){
				playerToAsk = nextPlayer;
			}
		}
		return playerToAsk;
	}
}
