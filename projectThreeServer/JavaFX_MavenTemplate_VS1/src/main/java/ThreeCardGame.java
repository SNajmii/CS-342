import java.io.*;
import java.util.ArrayList;


public class ThreeCardGame {
	ArrayList<Card> playerHand;
	ArrayList<Card> dealerHand;
	Dealer theDealer;
	int currentAnteBet;
	int currentppb;
	int currentpwb;
	double totalWinnings;
	
	ThreeCardGame() { // default constructor...
		this.playerHand = new ArrayList<Card>();
		this.dealerHand = new ArrayList<Card>();
		this.theDealer = new Dealer();
		this.currentAnteBet = 0;
		this.currentppb = 0;
		this.currentpwb = 0;
		this.totalWinnings = 0;
		
	}
	
	ThreeCardGame(ArrayList<Card> pH,ArrayList<Card> dH, Dealer bD, int anteB, int ppb, int pwb, double tW) { // argument constructor...
		this.playerHand = new ArrayList<Card>(pH);
		this.dealerHand = new ArrayList<Card>(dH);
		this.theDealer = new Dealer();
		this.currentAnteBet = anteB;
		this.currentppb = ppb;
		this.currentpwb = pwb;
		this.totalWinnings = tW;
	}
	
	ThreeCardGame(ThreeCardGame bG) { // copy construcotor...
		this.playerHand = bG.playerHand;
		this.dealerHand = bG.dealerHand;
		this.theDealer = bG.theDealer;
		this.currentAnteBet = bG.currentAnteBet;
		this.currentppb = bG.currentppb;
		this.currentpwb = bG.currentpwb;
		this.totalWinnings = bG.totalWinnings;
	}
	
	
	// note to self: DONT FORGET TO TEST THIS FUNCTION LATER!!! whoWon has been tested but this HAS NOT! //
	public double evaluateWinnings() { // evaluate the winnings of the round
		int result = ThreeCardLogic.compareHands(playerHand, dealerHand);
		if(result == 2) { // player won, so 1:1 payout...
			return this.currentAnteBet = this.currentAnteBet;
		}
		else { // tie, so 8:1 payout...
			return this.currentAnteBet = this.currentAnteBet;
		}
	} // end of evaluate winnnings
	
	
	void clear() {
		this.playerHand = new ArrayList<Card>();
		this.dealerHand = new ArrayList<Card>();
		this.theDealer = new Dealer();
		this.currentAnteBet = 0;
		this.currentppb = 0;
		this.currentpwb = 0;
		this.totalWinnings = 0.0;
	}
	//Getters and Setters found below//
	
	ArrayList<Card> getPHand() { // get player hand
		return this.playerHand;
	}
	
	ArrayList<Card> getDHand() { // get banker hand
		return this.dealerHand;
	}
	
	void setPHand(ArrayList<Card> pH) { // set player hand
		this.playerHand = pH;
	}
	
	void setBHand(ArrayList<Card> bH) { /// set banker hand
		this.dealerHand = bH;
	}
	
	Dealer getDealer() { // get Dealer
		return this.theDealer;
	}
	
	void setDealer(Dealer bD) { // set the Dealer
		this.theDealer = bD;
	}
	
	int getCurAnteBet() { // get the current bet
		return this.currentAnteBet;
	}
	
	int getCurppBet() { // get the current bet
		return this.currentppb;
	}
	
	int getCurpwBet() { // get the current bet
		return this.currentpwb;
	}
	
	double getWinnings() { // get the total winnings
		return this.totalWinnings;
	}

	void setCurAnteBet(int cB) { // set the current bet
		this.currentAnteBet = cB;
	}
	
	void setCurppBet(int cB) { // set the current bet
		this.currentppb = cB;
	}
	
	void setCurpwBet(int cB) { // set the current bet
		this.currentpwb = cB;
	}
	
	void setWinnings(double w) { // set the winnings
		this.totalWinnings = w;
	}
}