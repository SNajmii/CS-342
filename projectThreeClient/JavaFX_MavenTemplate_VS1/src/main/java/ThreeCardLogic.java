import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ThreeCardLogic {
    // sort the hand here
    private static ArrayList<Integer> sorting_cards(ArrayList<Card> hand) {
        ArrayList<Integer> number = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            number.add(hand.get(i).getValue());
        }
        Collections.sort(number);
        return number;
    }


    // check to see if the hand is a straight
    private static boolean hand_is_straight(ArrayList<Card> hand) {
        if (numMatch(hand) != 0) {
            return false;
        }

        // Sort current hand
        ArrayList<Integer> number = sorting_cards(hand);

        // got a regular straight
        if (((number.get(2) - number.get(1)) == 1) &&
                ((number.get(1) - number.get(0)) == 1))
            return true;

            // got a low straight
        else if (((number.get(2) - number.get(1)) == 11) &&
                ((number.get(1) - number.get(0)) == 1))
            return true;
        // otherwise return false
        return false;
    }

    // If the hand turns out to be a flush...
    private static boolean isFlush(ArrayList<Card> hand) {
        boolean card_flush = false;

        if ((hand.get(0).getSuit() == hand.get(1).getSuit()) &&
                (hand.get(1).getSuit() == hand.get(2).getSuit()))
            card_flush = true;

        return card_flush;
    }

    // if the current hand is a three of a kind or a pair
    private static int numMatch(ArrayList<Card> hand) {
        int yay = 0;
        // sort hand
        ArrayList<Integer> number = sorting_cards(hand);
        for (int i = 0; i < 2; i++)
            if (number.get(i).equals(number.get(i + 1)))
                yay++;

        return yay;
    }

    private static int highPair(ArrayList<Card> dealer, ArrayList<Card> player) {
        int player_high_card;
        int dealer_high_card;
        int Player3rd;
        int Dealer3rd;

        //Sort current hands
        ArrayList<Integer> playerHand = sorting_cards(player);
        ArrayList<Integer> dealerHand = sorting_cards(dealer);
        dealer_high_card = dealerHand.get(1);
        player_high_card = playerHand.get(1);

        if (player_high_card < dealer_high_card) {
            return 1;
        } else if (player_high_card > dealer_high_card) {
            return 2;
        }


        //The pairs are the same
        if (playerHand.get(0) != player_high_card)
            Player3rd = playerHand.get(0);
        else
            Player3rd = playerHand.get(2);
        if (dealerHand.get(0) != dealer_high_card)
            Dealer3rd = dealerHand.get(0);
        else
            Dealer3rd = dealerHand.get(2);

        if (Player3rd > Dealer3rd)
            return 2;
        else if (Player3rd < Dealer3rd)
            return 1;

        return 0;
    }


    // evaluate the hand, don't mind my rhymes
    public static int evalHand(ArrayList<Card> hand) {
        // straight flush! awesome hand!
        if (hand_is_straight(hand) && isFlush(hand))
            return 1;
            // 3 Of A Kind, more than fine!
        else if (numMatch(hand) == 2)
            return 2;
            // Straight, great!
        else if (hand_is_straight(hand))
            return 3;
            // Flush, don't rush
        else if (isFlush(hand))
            return 4;
            // pair, be fair!
        else if (numMatch(hand) == 1)
            return 5;

        // if you get a high card then return 0
        return 0;
    }

    // If pair plus bet is made, calculate the outcome of the bet
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        if (evalHand(hand) == 1)
            return 40 * bet;
        else if (evalHand(hand) == 2)
            return 30 * bet;
        else if (evalHand(hand) == 3)
            return 6 * bet;
        else if (evalHand(hand) == 4)
            return 3 * bet;
        else if (evalHand(hand) == 5)
            return bet;

        return -1;
    }

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int DealerCard = evalHand(dealer);
        int PlayerCard = evalHand(player);

        int[] dealerHighCards = HighestVal(dealer);
        int[] playerHighCards = HighestVal(player);
        int[] DealerHighPair = HighestPairVal(dealer);
        int[] PlayerHighPair = HighestPairVal(player);
        int winner = -1;

        // dealer wins against player
        if (PlayerCard < DealerCard  && PlayerCard == 0) {
            winner = 1;
        } else if (PlayerCard< DealerCard  && PlayerCard != 0) {
            winner = 2;  // player wins
        } else if (PlayerCard > DealerCard  && DealerCard  == 0) {
            winner = 2;  // player wins
        } else if (PlayerCard > DealerCard  && DealerCard != 0) {
            winner = 1;
        } else {   // this means it's a tie
            if ((PlayerCard== 1 || PlayerCard == 2 || PlayerCard == 3) &&
                    (playerHighCards[0] > dealerHighCards[0])) {
                winner = 2;
            } else if ((PlayerCard== 1 || PlayerCard == 2 || PlayerCard == 3) &&
                    (playerHighCards[0] < dealerHighCards[0])) {
                winner = 1;
            } else if ((PlayerCard == 1 || PlayerCard == 2 || PlayerCard == 3) &&
                    (playerHighCards[0] == dealerHighCards[0])) {
                winner = 0;
            } else if ((PlayerCard == 0 || PlayerCard == 4) &&
                    (playerHighCards[2] > dealerHighCards[2])) {
                winner = 2;
                // player loses because of high card, flush
            } else if ((PlayerCard == 0 || PlayerCard == 4) &&
                    (playerHighCards[2] < dealerHighCards[2])) {
                winner = 1;
            } else if ((PlayerCard == 0 || PlayerCard == 4) &&
                    (playerHighCards[1] > dealerHighCards[1])) {
                winner = 2;
            } else if ((PlayerCard == 0 || PlayerCard == 4) &&
                    (playerHighCards[1] < dealerHighCards[1])) {
                winner = 1;
            } else if ((PlayerCard == 0 || PlayerCard == 4) &&
                    (playerHighCards[0] < dealerHighCards[0])) {
                winner = 1;
            } else if ((PlayerCard == 0 || PlayerCard== 4) &&
                    (playerHighCards[0] > dealerHighCards[0])) {
                winner = 2;
            } else if ((PlayerCard == 0 || PlayerCard== 4) &&
                    (playerHighCards[0] < dealerHighCards[0])) {
                winner = 1;
            } else if ((PlayerCard == 0 || PlayerCard == 4) &&
                    (playerHighCards[1] == dealerHighCards[1])) {
                winner = 0;
            }
            else if ((PlayerCard == 5) &&
                    (PlayerHighPair[0] > DealerHighPair[0])) {
                winner = 2;
            } else if ((PlayerCard == 5) &&
                    (PlayerHighPair[0] < DealerHighPair[0])) {
                winner = 1;
            } else if ((PlayerCard == 5) &&
                    (PlayerHighPair[1] > DealerHighPair[1])) {
                winner = 2;
            } else if ((PlayerCard == 5) &&
                    (PlayerHighPair[1] < DealerHighPair[1])) {
                winner = 1;
            } else if ((PlayerCard== 5) &&
                    (PlayerHighPair[1] == DealerHighPair[1])) {
                winner = 0;
            }
        }
        return winner;
    }

    public static int[] HighestVal(ArrayList<Card> hand) {
        int[] highestCardValues = new int[3];

        for (int i = 0; i < hand.size(); i++) {
            highestCardValues[i] = hand.get(i).getValue();
        }
        Arrays.sort(highestCardValues);
        return highestCardValues;
    }

    public static int[] HighestPairVal(ArrayList<Card> hand) {
        int max;
        int notPair;
        int[] High_values = new int[2];
        int cardOne = hand.get(0).getValue();
        int cardTwo = hand.get(1).getValue();
        int cardThree = hand.get(2).getValue();

        if (cardOne == cardThree) {
            max = cardOne;
            notPair = cardTwo;
        }else if (cardTwo == cardThree) {
            max = cardTwo;
            notPair = cardOne;
        } else if (cardOne == cardTwo)  {
            max = cardOne;
            notPair = cardThree;
        }  else {
            max = -1;
            notPair = -1;
        }

        High_values[0] = max;
        High_values[1] = notPair;
        return High_values;
    }

}