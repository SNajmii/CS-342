import java.util.ArrayList;

public class Dealer
{
    Deck theDeck;
    ArrayList<Card> dealersHand;

    // Constructor declares a new deck and sets the dealers hand to a capacity of 3
    Dealer() {
        theDeck = new Deck();
        dealersHand = new ArrayList<>(3);
    }
//    Dealer(ArrayList<Card> d){ // argument constructor
//		this.theDeck = d;
//	}
//	Dealer(Dealer bD){ // copy constructor
//		this.theDeck = bD.deck;
//	}

    public ArrayList<Card> dealHand() {
        // Take 3 cards from the deck, and hold it before giving it to a player
        ArrayList<Card> starter_cards = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            starter_cards.add(theDeck.draw_card());
        }
        return starter_cards;
    }

    // Resets and shuffles the deck
    public void new_deck() {
        theDeck.newDeck();
        theDeck.shuffle_cards();
    }

    // Get the cards that were dealt to the dealer
    public void dealer_dealt_cards(ArrayList<Card> dealer_dealt_cards)
    {
        dealersHand = dealer_dealt_cards;
    }

    // show current hand
    public void show_hand() {
        for (Card i : dealersHand)
            System.out.println("Suit: " + i.getSuit() + " Value: " + i.getValue());
    }

    // get dealers hand
    public ArrayList<Card> getHand()
    {
        return dealersHand;
    }

    // return # cards in deck
    public int getDeckSize()
    {
        return theDeck.getNumberOfCards();
    }

    // suit character names
    public String getSuitString(char suit)
    {
        if (suit == 'C')
            return "Club";
        else if (suit == 'D')
            return "Diamond";
        if (suit == 'H')
            return "Heart";
        if (suit == 'S')
            return "Spade";
        return "";
    }

//    // Get the string suit and value of a card for dealer
    public String getCardsAsString() {
        StringBuilder cards = new StringBuilder();
        for (Card i : getHand())
        {
            if (Integer.toString(i.getValue()).equals("14"))
                cards.append("Ace of ").append(getSuitString(i.getSuit())).append("s\n");
            else if (Integer.toString(i.getValue()).equals("13"))
                cards.append("King of ").append(getSuitString(i.getSuit())).append("s\n");
            else if (Integer.toString(i.getValue()).equals("12"))
                cards.append("Queen of ").append(getSuitString(i.getSuit())).append("s\n");
            else if (Integer.toString(i.getValue()).equals("11"))
                cards.append("Jack of ").append(getSuitString(i.getSuit())).append("s\n");
            else
                cards.append(i.getValue()).append(" of ").append(getSuitString(i.getSuit())).append("s\n");

        }
        return cards.toString();
    }
}