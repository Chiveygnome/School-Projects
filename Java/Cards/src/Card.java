
public class Card {//This is the basic class of this program that contains the varibles for face, values and suit of the card objects
	private String face;
	private String suit;
	private int value;
	
	public Card( String cardFace, String cardSuit,int cardValue)
	{
		face = cardFace;
		value = cardValue;
		suit = cardSuit;
	}
	
	public String toString()
	{
		return face + " of " + suit;
	}
	public String getFace()
	{
		return face;
	}
	public String getSuit()
	{
		return suit;
	}
	public int getValue()
	{
		return value;
	}

}
