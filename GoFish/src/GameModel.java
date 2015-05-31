import java.io.Console;
import java.util.Iterator;
import java.util.LinkedList;

public class GameModel {
	LinkedList<Player> players;
	Deck deck;
	int numCardsToDeal;
	int currentPlayerIndex;
	Iterator<Player> itr; //= players.iterator();

	public GameModel(){
		this(7);
	}

	public GameModel(int numCardsToDeal){
		setDeck();
		this.numCardsToDeal = numCardsToDeal;
		this.players = new LinkedList<Player>();
		currentPlayerIndex = 0;
	}

	public void newGame(){
		setDeck();
	}

	public void setNumCardsToDeal(int numCardsToDeal){
		this.numCardsToDeal = numCardsToDeal;
	}

	public void addPlayer(Player player){
		players.add(player);
	}

	public void removePlayer(Player player){
		players.remove(player);
	}

	public void setDeck(){
		deck = new Deck();
	}

	public Deck getDeck(){
		return deck;
	}

	public void deal(){
		int counter = 1;
		for(int i=1; i<=numCardsToDeal; i++){
			for(Player p: players){
				System.out.println(counter++ + ". Card dealt to " + p.getPlayerName());
				p.getCard(deck.getNextCardFromDeck());
			}
		}
	}

	public void startGame(){

		// TODO before taking turns everyone needs to lay down their matching pairs
		findPairsInAllPlayersHands();
		System.out.println(showAllCardsOnTheTable());

		while (!deck.isEmpty() && allPlayersHaveCardsInTheirHand()){
			nextPlayerTakeTurn();
		}

		// the game is over, figure out who won by determining who collected the most pairs
		// need to allow for case of a tie
	}

	public void findPairsInAllPlayersHands(){
		for(Player player: players){
			findPairsInHand(player);
		}
	}
//
//	public void findPairsInHand(Player player){
//		Hand hand = player.getHand();
//		for(int i=0; i<hand.getSize(); i++){
//			Card cardToCompare = hand.getCardFromList(i);
//			for(int j=i+1; j<hand.getSize(); j++){
//				Card nextCardToCheck = hand.getCardFromList(j);
//				if(cardToCompare.equals(nextCardToCheck)){
//					player.putCardOnTable(nextCardToCheck);
//					player.putCardOnTable(cardToCompare);
//				}
//			}
//		}
//	}
//	
	public void findPairsInHand(Player player){
		System.out.println("Pairs for " + player.getPlayerName());
		Hand hand = player.getHand();
		int i = 0;
		while(i < hand.getSize()){	
			boolean matchFound = false;
			Card cardToCompare = hand.getCardFromList(i);
			for(int j=i+1; j<hand.getSize(); j++){
				Card nextCardToCheck = hand.getCardFromList(j);
				if(cardToCompare.equals(nextCardToCheck)){
					player.putCardOnTable(nextCardToCheck);
					player.putCardOnTable(cardToCompare);
					matchFound = true;
					break;
				}
			}
			if(!matchFound){
				i++;
			}
		}
	}

	public String showAllCardsOnTheTable(){
		String cardsOnTheTable = "";
		for(Player player: players){
			cardsOnTheTable += player.toString() + " " + player.cardsOnTableToString();
		}
		return cardsOnTheTable;
	}

	/**
	 * Return the next player whose turn it is.
	 * @return
	 */
	public Player getNextPlayer(){

		Player nextPlayer;

		if (players == null){
			throw new NullPlayerListException("The list of players is empty.");
		} else if(itr == null || !itr.hasNext()){
			itr = players.iterator();
		}

		nextPlayer = itr.next();
		return nextPlayer;
	}

	public boolean allPlayersHaveCardsInTheirHand(){
		for(Player player: players){
			if(player.getHand().getSize()<=0){
				return false;
			}
		}
		return true;
	}

	public void nextPlayerTakeTurn(){

		Player nextPlayer = getNextPlayer();
		Player playerToAsk;
		CardColor colorToAskFor;
		boolean continueTurn = true;

		while(continueTurn){
			if(nextPlayer instanceof HumanPlayer){
				playerToAsk = askForPlayer(nextPlayer);
				colorToAskFor = askForColor();
			} else if (nextPlayer instanceof ComputerPlayer){
				// call computer player's nextTurn method
				ComputerPlayer computerPlayer = (ComputerPlayer) nextPlayer;
				playerToAsk = computerPlayer.chooseAPlayerToAsk(players);
				colorToAskFor = computerPlayer.askForColor();
			} else {
				throw new UndefinedPlayerException("This player is neither a human player or a computer player: " + nextPlayer.toString());
			}

			// ask the playerToAsk for the colorToAskFor
			// if player has the color and no player has an empty hand, the player goes again
			if(playerToAsk.hasCardColor(colorToAskFor) && allPlayersHaveCardsInTheirHand()){
				takeCardFromPlayer(nextPlayer, playerToAsk, colorToAskFor);
			} else {
				continueTurn = false;
			}
		}
	}

	public void takeCardFromPlayer(Player playerTo, Player playerFrom, CardColor color){
		playerTo.getCardAndPutMatchingPairOnTable(playerFrom.giveUpCardWithColor(color));
		
	}
	
	public CardColor askForColor() {

		CardColor cardColorToReturn = null;
		Console c = System.console();
		
		while(cardColorToReturn == null){
			String request = c.readLine("Which color are you looking for?: ");
			try{
				cardColorToReturn = CardColor.valueOf(request.toUpperCase());
			} catch (Exception e){
				if (e instanceof IllegalArgumentException){
					System.out.println("You did not enter an acceptable color. Please try again.");
				} else if (e instanceof NullPointerException) {
					System.out.println("You did not enter anything. Please try again.");
				} else if (request.toUpperCase() == "Q") {
					// TODO quit game
				} else {
					throw e;
				}
			}
		}
		return cardColorToReturn;
	}

	/**
	 * Decide which player to ask for a card.
	 * @return the player to ask
	 */
	public Player askForPlayer(Player player){
		Player playerToChoose = null;
		Console c = System.console();
		LinkedList<Player> listOfPlayersToChooseFrom = getListOfPlayersThatExcludesThisPlayer(player);
		
		while(playerToChoose == null){
			System.out.println("The players you can ask are: ");
			System.out.println(allPlayersExceptThisOneWithTheirIndicesToString(listOfPlayersToChooseFrom));
			String request = c.readLine("Which player do you want to ask?: ");
			try{
				// TODO check that it is an integer that is < listOfPlayersToChooseFrom.size()
				// if it's not, ask again
				// otherwise use the input to return a player from the listOfPlayersToChooseFrom
			} catch (Exception e){
				if (e instanceof IllegalArgumentException){
					System.out.println("You did not enter an acceptable color. Please try again.");
				} else if (e instanceof NullPointerException) {
					System.out.println("You did not enter anything. Please try again.");
				} else if (request.toUpperCase() == "Q") {
					// TODO quit game
				} else {
					throw e;
				}
			}
		}
		return playerToChoose;
	}
	
	public LinkedList<Player> getListOfPlayersThatExcludesThisPlayer(Player player){
		LinkedList<Player> listOfPlayers = new LinkedList<Player>();
		for(Player existingPlayer : players){
			if(!existingPlayer.equals(player)){
				listOfPlayers.add(existingPlayer);
			}
		}
		return listOfPlayers;
	}
	
	public String allPlayersExceptThisOneWithTheirIndicesToString(LinkedList<Player> playerList){
		String returnString = "";
		for(int i=0; i<playerList.size(); i++){
			returnString += i + ": " + playerList.get(i).toString();
		}
		return returnString;
	}

	@SuppressWarnings("serial")
	public class NullPlayerListException extends RuntimeException {

		public NullPlayerListException(){
			super();
		}

		public NullPlayerListException(String message){
			super(message);
		}
	}

	@SuppressWarnings("serial")
	public class UndefinedPlayerException extends RuntimeException {

		public UndefinedPlayerException(){
			super();
		}

		public UndefinedPlayerException(String message){
			super(message);
		}
	}
}
