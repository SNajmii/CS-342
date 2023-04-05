import java.util.ArrayList;
import java.util.Random;

public class Deck extends ArrayList<Card> {
	
    private ArrayList<Card> Deck;
    private Random random_number_generator = new Random();

    private enum Suit {
        C('C'), D('D'), H('H'), S('S');

        char suitChar;
        Suit(char myChar)
        {
            suitChar = myChar;
        }

        char getChar()
        {
            return suitChar;
        }
    }

    // create a new sorted deck of cards
    Deck() {
        Deck = new ArrayList <>();
        //Loop through suits and values and create a deck of 52 cards
        for(Suit suit : Suit.values())
            for (int cardValue = 2; cardValue < 15; cardValue++)
                Deck.add(new Card(suit.getChar(), cardValue));
    }

    // create a deck of 52 cards
    public void newDeck() {
        Deck = new ArrayList <>();
        for(Suit suit : Suit.values())
            for (int cardValue = 2; cardValue < 15; cardValue++)
                Deck.add(new Card(suit.getChar(), cardValue));
    }

    public Card draw_card() {
        int temp = random_number_generator.nextInt(Deck.size() - 1);
        Card set_buff = Deck.get(temp);  // get the card from deck
        Deck.remove(temp);   // remove it from the deck
        return set_buff;  // return the card
    }

    // shuffle the cards
    public void shuffle_cards() {
        ArrayList<Card> shuffle_cards = new ArrayList<>();
        System.out.println("Size: " + Deck.size());
        // choose a random card from the deck
        int deckSize = Deck.size();

        for (int j = 0; j < deckSize; j++)
        {
            int index = random_number_generator.nextInt(Deck.size());
            shuffle_cards.add(Deck.get(index));
            Deck.remove(index);
        }
        Deck = shuffle_cards;
    }
    // check to see if we need new deck
    public int getNumberOfCards()
    {
        return Deck.size();
    }
}