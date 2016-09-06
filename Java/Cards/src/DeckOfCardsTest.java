


public class DeckOfCardsTest {
	public static void main(String[] args)//main method
	{
		Card[] Hand = new Card[5];//creates two hands
		Card[] Hand2 = new Card[5];
		DeckOfCards myDeckOfCards = new DeckOfCards();//creates deck
		myDeckOfCards.shuffle();//shuffles deck
		System.out.println("Your hand is:\n");
		for(int i = 0; i<5;i++)//gets your hand from the first 5 cards of the deck
		{
			Hand[i] = myDeckOfCards.getDeck(i);
		}
		
		for(int i = 0;i<5;i++)
		{
			myDeckOfCards.sortHand(Hand, 0,4);
			System.out.printf("%s    ", Hand[i]);
		}
		myDeckOfCards.compareTypes(Hand);//gets the types that you have in your hand
		myDeckOfCards.printValues();//prints the values
		System.out.println();
		System.out.println("Their hand is:\n");
		for(int i = 0;i<5;i++)//gets the opponents hand from the next 5 cards in the deck
		{
			Hand2[i] = myDeckOfCards.getDeck(i+5);
		}
		for(int i = 0;i<5;i++)
		{
			myDeckOfCards.sortHand(Hand2, 0, 4);
			System.out.printf("%s    ", Hand2[i]);
		}
		myDeckOfCards.compareTypes2(Hand2);//gets the types that the opponent has
		myDeckOfCards.printValues2();//prints the values
		myDeckOfCards.determineWin();//find the winner
		myDeckOfCards.tellWinnner();//print the winner
	}
}
