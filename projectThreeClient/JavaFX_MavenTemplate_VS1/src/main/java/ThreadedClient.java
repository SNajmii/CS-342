import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;



public class ThreadedClient extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	ThreeCardInfo gI;
	String IP;
	int portNum;
	ArrayList<Card> dealer;
	ArrayList<Card> player;
	
	private Consumer<Serializable> callback;
	
	ThreadedClient(){
		
		
	}
	
	ThreadedClient(Consumer<Serializable> call,String ip, int pN, int b, int ppbb, int pwbb){
	
		callback = call;
		this.IP = ip;
		this.portNum = pN;
		gI = new ThreeCardInfo();
		gI.setAnteBet(b);
		gI.setppb(ppbb);
		gI.setpwb(pwbb);
		gI.setWins(0);

	}
	
	public void clear() {
		gI.clear();
	}
	
	public void run() {
		
		try {
		socketClient= new Socket(IP,portNum);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {
			System.out.println(e);
			callback.accept("SOMETHING WENT WRONG");
		}
		
			 
			try {
				out.writeObject(gI);
				String result = in.readObject().toString();
				callback.accept(result);
				ThreeCardInfo gI2 = new ThreeCardInfo((ThreeCardInfo)in.readObject());
				if(ThreeCardLogic.compareHands(dealer, player) == 2) {
					callback.accept("Player has won the game!");
				}else {
					callback.accept("Player has lost the game!");
				}
				
//				
//				if(gI2.getPHand().size() == 3 ) { // player got an extra card, notify the client
//					callback.accept("Players hand was: " + gI2.getPHand().get(0).getValue() + " of " + gI2.getPHand().get(0).getSuit() + " and " + gI2.getPHand().get(1).getValue() + " of " + gI2.getPHand().get(0).getSuit() + " and and extra card: " + gI2.getPHand().get(2).getValue() + " of " + gI2.getPHand().get(0).getSuit());
//					callback.accept("Bankers hand was: " + gI2.getBHand().get(0).getValue() + " of " + gI2.getBHand().get(0).getSuit() + " and " + gI2.getBHand().get(1).getValue() + " of " + gI2.getBHand().get(0).getSuit());
//					callback.accept(gI2.getWhoWOn() + " Wins!");
//				}
//				else if(gI2.getBHand().size() == 3) { // banker got an extra card, notify the client
//					callback.accept("Bankers hand was: " + gI2.getBHand().get(0).getValue() + " of " + gI2.getBHand().get(0).getSuit() + " and " + gI2.getBHand().get(1).getValue() + " of " + gI2.getBHand().get(0).getSuit() + " and and extra card: " + gI2.getBHand().get(2).getValue() + " of " + gI2.getBHand().get(0).getSuit());
//					callback.accept("Players hand was: " + gI2.getPHand().get(0).getValue() + " of " + gI2.getPHand().get(0).getSuit() + " and " + gI2.getPHand().get(1).getValue() + " of " + gI2.getPHand().get(0).getSuit());
//					callback.accept(gI2.getWhoWOn() + " Wins!");
//				}
//				else if(gI2.getPHand().size() == 3 && gI2.getBHand().size() == 3) { // both got an extra card, notify the client
//					callback.accept("Players hand was: " + gI2.getPHand().get(0).getValue() + " of " + gI2.getPHand().get(0).getSuit() + " and " + gI2.getPHand().get(1).getValue() + " of " + gI2.getPHand().get(0).getSuit() + " and and extra card: " + gI2.getPHand().get(2).getValue() + " of " + gI2.getPHand().get(0).getSuit());
//					callback.accept("Bankers hand was: " + gI2.getBHand().get(0).getValue() + " of " + gI2.getBHand().get(0).getSuit() + " and " + gI2.getBHand().get(1).getValue() + " of " + gI2.getBHand().get(0).getSuit() + " and and extra card: " + gI2.getBHand().get(2).getValue() + " of " + gI2.getBHand().get(0).getSuit());
//					callback.accept(gI2.getWhoWOn() + " Wins!");
//				}
//				else { // no extra card for either or one extra card for one and not the other
//					callback.accept("Players hand was: " + gI2.getPHand().get(0).getValue() + " of " + gI2.getPHand().get(0).getSuit() + " and " + gI2.getPHand().get(1).getValue() + " of " + gI2.getPHand().get(0).getSuit());
//					callback.accept("Bankers hand was: " + gI2.getBHand().get(0).getValue() + " of " + gI2.getBHand().get(0).getSuit() + " and " + gI2.getBHand().get(1).getValue() + " of " + gI2.getBHand().get(0).getSuit());
//					callback.accept(gI2.getWhoWOn() + " Wins!");
//				}
//				if(gI.getWins() > gI2.getWins()) { // if the old winnings are greater than previous winnings, we have to have lost
//					callback.accept("Sorry! you've lost your bet of: " + gI.getBet());
//					return;
//				}
//				else { // otherwise we had to have won!
//					callback.accept("Congrats! you've won: " + gI2.getWins() + " in addition to your current bet!");
//					return;
//				}
					
				
			}
			catch(Exception e) {}
		
	
    }
	
	
	public void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}