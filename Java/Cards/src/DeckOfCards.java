
import java.util.Random;
public class DeckOfCards {//Initialize all private variables for DeckOfCards class
	private boolean youWin = false,theyWin = false;
	private int pairs=0, threeKind=0, fourKind=0, flush=0, straight=0,fullHouse=0;
	private int pairs2=0, threeKind2=0, fourKind2=0, flush2=0, straight2=0,fullHouse2=0;
	private Card[]  deck;
	private int currentCard;
	private static final int NUMBER_OF_CARDS = 52;
	private static final Random randomNumbers = new Random();
	
	public DeckOfCards()//This method creates a deck of 52 cards and assigns the correct suit and face values to them
	{
		String[] faces = {"Ace","Deuce","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
		int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
		
		deck = new Card[NUMBER_OF_CARDS];
		currentCard = 0;
		
		for(int count = 0;count<deck.length;count++)
		{
			deck[count] = new Card(faces[count%13], suits[count/13],values[count%13]);
		}
	}
	public Card getDeck(int counter)//this method accesses the card at the deck position of counter
	{
		return deck[counter];
	}
	public void shuffle()//This method shuffles all the cards in the deck
	{
		currentCard = 0;
		for(int first = 0; first<deck.length; first++)
		{
			int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
			Card temp = deck[first];
			deck[first]=deck[second];
			deck[second] = temp;
		}
	}
	public Card dealCard()//This is a relic method
	{
		if(currentCard<deck.length)
			return deck[currentCard++];
		else
			return null;
	}
	public void compareTypes(Card[] myCard)//This method takes your hand and finds what types of groups you have and assigns the correct number to the correct variable.
	{
		for(int i =0;i<5;i++)
		{
			
			if(i==4)
				continue;
			if(myCard[i].getValue()==myCard[i+1].getValue())
			{
				pairs++;
				if(i==3)
					continue;
				if(myCard[i].getValue()==myCard[i+2].getValue())
				{
					threeKind++;
					pairs--;
					if(i==2)
						continue;
					if(myCard[i].getValue()==myCard[i+3].getValue())
					{
						threeKind--;
						fourKind++;
						continue;
					}

				}

			}
		}
	
		if(myCard[0].getSuit()==myCard[1].getSuit())
		{
			if(myCard[0].getSuit()==myCard[2].getSuit())
			{
				if(myCard[0].getSuit()==myCard[3].getSuit())
				{
					if(myCard[0].getSuit()==myCard[4].getSuit())
					{
						flush++;
					}
				}
			}
		}
		if((myCard[0].getValue())+1 ==myCard[1].getValue())
		{
			if((myCard[0].getValue()+2)==myCard[2].getValue())
			{
				if((myCard[0].getValue())+3==myCard[3].getValue())
				{
					if((myCard[0].getValue())+4==myCard[4].getValue())
					{
						straight++;
					}
				}
			}
		}
		if(pairs == 1)
		{
			if(threeKind == 1)
			{
				fullHouse++;
			}
		}
	}
	public void compareTypes2(Card[] myCard)//This is a copy of compareTypes, but with variables for the opponent.
	{
		for(int i =0;i<5;i++)
		{
			if(i==4)
				continue;
			if(myCard[i].getValue()==myCard[i+1].getValue())
			{
				pairs2++;
				if(i==3)
					continue;
				if(myCard[i].getValue()==myCard[i+2].getValue())
				{
					threeKind2++;
					pairs2--;
					if(i==2)
						continue;
					if(myCard[i].getValue()==myCard[i+3].getValue())
					{
						threeKind2--;
						fourKind2++;
						continue;
					}

				}

			}
		}
		if(myCard[0].getSuit()==myCard[1].getSuit())
		{
			if(myCard[0].getSuit()==myCard[2].getSuit())
			{
				if(myCard[0].getSuit()==myCard[3].getSuit())
				{
					if(myCard[0].getSuit()==myCard[4].getSuit())
					{
						flush2++;
					}
				}
			}
		}
		if((myCard[0].getValue())+1 ==myCard[1].getValue())
		{
			if((myCard[0].getValue()+2)==myCard[2].getValue())
			{
				if((myCard[0].getValue())+3==myCard[3].getValue())
				{
					if((myCard[0].getValue())+4==myCard[4].getValue())
					{
						straight2++;
					}
				}
			}
		}
		if(pairs2 == 1)
		{
			if(threeKind2 == 1)
			{
				fullHouse2++;
			}
		}
	}
	public void printValues()//Prints the number of types
	{
		System.out.println();
		System.out.println("Pairs Three Four flush straight Full house");
		System.out.println();
		System.out.print(pairs);
		System.out.print("     ");
		System.out.print(threeKind);
		System.out.print("     ");
		System.out.print(fourKind);
		System.out.print("     ");
		System.out.print(flush);
		System.out.print("     ");
		System.out.print(straight);
		System.out.print("       ");
		System.out.print(fullHouse);
	}
	public void printValues2()//Prints the number of types of the opponents
	{
		System.out.println();
		System.out.println("Pairs Three Four flush straight Full house");
		System.out.println();
		System.out.print(pairs2);
		System.out.print("     ");
		System.out.print(threeKind2);
		System.out.print("     ");
		System.out.print(fourKind2);
		System.out.print("     ");
		System.out.print(flush2);
		System.out.print("     ");
		System.out.print(straight2);
		System.out.print("       ");
		System.out.print(fullHouse2);
	}
	public void determineWin()//Determine the winner by comparing the amount of types
	{
		if(fullHouse == fullHouse2)
		{
			if(straight == straight2)
			{
				if(flush == flush2)
				{
					if(fourKind == fourKind2)
					{
						if(threeKind == threeKind2)
						{
							if(pairs == pairs2)
							{
								
							}
							else if(pairs>pairs2)
								youWin = true;
							else
								theyWin = true;
						}
						else if(threeKind>threeKind2)
							youWin = true;
						else 
							theyWin =true;
					}
					else if(fourKind>fourKind2)
						youWin = true;
					else
						theyWin = true;
				}
				else if(flush>flush2)
					youWin = true;
				else
					theyWin = true;
			}
			else if(straight>straight2)
				youWin =true;
			else
				theyWin = true;
		}
		else if(fullHouse>fullHouse2)
			youWin =true;
		else
			theyWin = true;
	}
	public void tellWinnner()//Simply compares the boolean variables that were set in the previous method and print the winner.
	{
		if(youWin)
			System.out.println("\nYou Won!");
		else if(theyWin)
			System.out.println("\nYou Lost...");
		else
			System.out.println("\nIt's a Tie!");
	}
	public void sortHand(Card[] Hand,int start, int end)
	{
		int i = start;
		int k = end;
		if(end - start >= 1)
		{
			Card l = Hand[start];
			 while(k>i)
			 {
				 while(Hand[i].getValue()<=l.getValue() && i<=end && k>i)
				 {
					i++; 
				 }
				 while(Hand[k].getValue()> l.getValue() && k >= start && k>= i)
				 {
					 k--;
				 }
				 if(k>i)
				 {
					 swap(Hand,i,k);
				 }
				 
			 }
			 swap(Hand,start,k);
			 sortHand(Hand, start,k-1);
			 sortHand(Hand, k+1,end);
		}
		else
			return;
	}
	public void swap(Card[] Hand, int a, int b)
	{
		Card temp = Hand[a];
		Hand[a] = Hand[b];
		Hand[b] = temp;
	}
	
}


