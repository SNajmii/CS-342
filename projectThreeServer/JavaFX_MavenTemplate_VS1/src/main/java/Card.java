//
//public class Card
//{
//    public char suit;
//    public  int value;
//
//    // Every card has a suit and value
//    Card(char suit, int value)
//    {
//        this.suit = suit;
//        this.value = value;
//    }
//    protected char getSuit()
//    {
//        return suit;
//    }
//    protected int getValue()
//    {
//        return value;
//    }
//}


import java.io.Serializable;

// Joseph Lenaghan | CS342 | Project Three Server Program | UIN:676805596
// Card class for Baccarat
// has two parameters to hold the suite of the card as well as its value
// 




public class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char suit; // suite of the card, 4 possible suites
	int value; // value of the card, possible values are 0 thru 9, 10, jack, queen, and king are zeros
	
//	Card() { // default constructor
//		this.suit = ;
//		this.value = 0;
//	}
//	Card(Card card) { // copy constructor
//		this.suit = card.suit;
//		this.value = card.value;
//	}
	Card(char theSuite, int theValue) { // argument constructor
		this.suit = theSuite;
		this.value = theValue;
	}
	
	char getSuit(){ // return the suite of the card
		return this.suit;
	}
	
	int getValue() { // return the value of the card
		return this.value;
	}
	
}