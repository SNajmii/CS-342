import java.io.*;
import java.util.ArrayList;


class ThreeCardInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	int antebet;
	int ppb;
	int pwb;
	double winnings;
	String whoWon;
	ArrayList<Card> pHand;
	ArrayList<Card> dHand;
	
	ThreeCardInfo() {
		antebet = 0;
		ppb = 0;
		pwb = 0;
		winnings = 0.0;
		whoWon = " ";
		pHand = new ArrayList<Card>();
		dHand = new ArrayList<Card>();
	}
	
	ThreeCardInfo(ThreeCardInfo gI) {
		this.antebet = gI.antebet;
		this.ppb = gI.ppb;
		this.pwb = gI.pwb;
		this.winnings = gI.winnings;
		this.whoWon = gI.whoWon;
		this.pHand = gI.pHand;
		this.dHand = gI.dHand;
	}

	
	void setWhoWon(String w) {
		this.whoWon = w;
	}
	
	void setAnteBet (int b) {
		this.antebet = b;
	}
	
	void setppb (int b) {
		this.ppb = b;
	}
	
	void setpwb (int b) {
		this.pwb = b;
	}
	
	void setWins(double w) {
		this.winnings = w;
	}
	
	void setPHand(ArrayList<Card> pH) {
		this.pHand = pH;
	}
	
	void setDHand(ArrayList<Card> dH) {
		this.dHand = dH;
	}
	
	String getWhoWOn() {
		return this.whoWon;
	}
	
	ArrayList<Card> getPHand() {
		return this.pHand;
	}
	
	ArrayList<Card> getDHand() {
		return this.dHand;
	}
	
	int getAnteBet() {
		return this.antebet;
	}
	
	int getppb() {
		return this.ppb;
	}
	
	int getpwb() {
		return this.pwb;
	}
	
	double getWins() {
		return this.winnings;
	}
	
	void clear() {
		this.antebet = 0;
		this.ppb = 0;
		this.pwb = 0;
		pHand.clear();
		dHand.clear();
	}
	
	
}
