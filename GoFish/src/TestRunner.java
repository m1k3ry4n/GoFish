
public class TestRunner {

	public static void main(String[] args) {
		GameModel gameModel = new GameModel();
		Player phoebe = new HumanPlayer("Phoebe");
		Player computer = new ComputerPlayer("Computer");
		gameModel.addPlayer(phoebe);
		gameModel.addPlayer(computer);
		System.out.println("Deck contains: " + gameModel.getDeck().deckToString());
		gameModel.deal();
		System.out.println("After dealing Deck contains: " + gameModel.getDeck().deckToString());
		System.out.println("Players: ");
		System.out.println(phoebe.toString());
		System.out.println(computer.toString());
		System.out.println("Put all your pairs on the table");
		gameModel.findPairsInAllPlayersHands();
		System.out.println("Players: ");
		System.out.println(phoebe.toString());
		System.out.println(computer.toString());
		if(computer.hasCardColor(CardColor.BLUE)){
			gameModel.takeCardFromPlayer(phoebe, computer, CardColor.BLUE);
		} else {
			System.out.println(computer.getPlayerName() + " does not have any Blue. Go fish.");
		}
		System.out.println(phoebe.toString());
		System.out.println(computer.toString());
	}

}
