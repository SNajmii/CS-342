import java.util.ArrayList;

public class Player {
    public ArrayList<Card> hand;
    public int anteBet;
    public int playBet;
    public int pairPlusBet;
    public int totalWinnings;
    public int balance;

    // create player constructor
    Player() {
        hand = new ArrayList <>(3);
        totalWinnings = 0;
        anteBet = 0;
        playBet = 0;
        balance = 1000;
    }

    // get cards dealer received
    public void getDealt(ArrayList<Card> dealt) {
        hand = dealt;
    }

    // get players bet
    public int getBet() {
        return anteBet;
    }

    // set players bet
    public void setBet(int ante) {
        balance -= ante;
        anteBet += ante;
        playBet += ante;
    }

    // Get the PPB
    public int getPairPlusBet()
    {
        return pairPlusBet;
    }

    //Set the PPB
    public void set_pair_plus_bet(int pair_PB) {
        pairPlusBet= pair_PB;
        balance -= pair_PB;
    }

    // return all game winnings
    public int get_total_winnings()
    {
        return totalWinnings;
    }

    // add up all total game winnings and adjust balance
    public void set_all_winnings(int pot) {
        totalWinnings += pot;
        balance += pot;
    }

    // get player balance
    public int get_player_balance()
    {
        return balance;
    }

    // print out current hand
    public void show_current_hand() {
        for (Card x : hand)
            System.out.println("Suit: " + x.getSuit() + " Value: " + x.getValue());
    }


    // find players hand
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    // clear bets after each round
    public void clearBets() {
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
    }

    // each suit character
    public String suit_string(char suit) {
        if (suit == 'S')
            return "Spade";
        else if (suit == 'C')
            return "Club";
        else if (suit == 'H')
            return "Heart";
        else if (suit == 'D')
            return "Diamond";

        return "";
    }

    // get the type of card and value
    public String getCardsAsString() {
        StringBuilder cards = new StringBuilder();
        for (Card i : getHand()) {
            if (Integer.toString(i.getValue()).equals("11"))
                cards.append("Jack of ").append(suit_string(i.getSuit())).append("s\n");
            else if (Integer.toString(i.getValue()).equals("12"))
                cards.append("Queen of ").append(suit_string(i.getSuit())).append("s\n");
            else if (Integer.toString(i.getValue()).equals("13"))
                cards.append("King of ").append(suit_string(i.getSuit())).append("s\n");
            else if (Integer.toString(i.getValue()).equals("14"))
                cards.append("Ace of ").append(suit_string(i.getSuit())).append("s\n");
            else
                cards.append(i.getValue()).append(" of ").append(suit_string(i.getSuit())).append("s\n");
        }
        return cards.toString();
    }

    // reset the player's stats when a button menu option is clicked....can implement this in javatemplate
    public void reset_player_stats() {
        hand = new ArrayList <>(3);
        totalWinnings = 0;
        balance = 1000;
        anteBet = 0;
        playBet = 0;
    }
}
