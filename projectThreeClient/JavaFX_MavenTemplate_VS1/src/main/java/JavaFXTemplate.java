
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;
import static javafx.geometry.Pos.TOP_CENTER;

import java.util.ArrayList;

import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

public class JavaFXTemplate extends Application {	
	private Button startbtn;
	private Button quitbtn;
	private Button restartbtn;
	private Button confirmbtn;
	private Button mainbtn;
	
	Integer increment = 0;

	boolean player1click;  // true is play, false is fold
	boolean player1click_fold;
	int newLook_count = 0;
	
	private Text t1;
	private Text t2;
	private Text t3;
	private Text t4;
	private Text t5;
	
	private TextField tf1;
	private TextField tf2;
	private TextField tf3;
	
	Player playerOne;
	Dealer theDealer;
	
	Text WelcomeText = new Text();
	Button start;
	Button play;
	Button restart_button = new Button();
	Button exit_button = new Button();
	Scene scene2, scene3;


	// scene 2 buttons
	// player Buttons and Text Field contained in Pane
	Button play1 = new Button("Play");   //play button player

	// Play or fold for player
	Button fold = new Button("Fold");  // fold player
	Button deal = new Button("Deal");
	Button Check = new Button("Check");

	// player Text Field contained in Pane
	VBox player1;
	Button player1_PairPLusBet = new Button("Pair Plus Bet");
	TextField player1_PairPLusBetText = new TextField();

	// menubar
	Menu menu = new Menu("Options");
	MenuItem exit_menu = new MenuItem("Exit");
	MenuItem fresh_start_menu = new MenuItem("Fresh Start");
	MenuItem new_look_menu = new MenuItem("NewLook");

	// Game Variables
	int player1_bets, pair_plus_bet1, player_one_wins;
	Player player_one = new Player();
	Dealer dealer = new Dealer();

//	//CardBackings
	Image card_backing = new Image("images/card_backing_red.png");
	Image card_backing_Blue = new Image("images/Blue_card_backing.jpeg");
	
	private ListView<String> listItems;
	ThreadedClient nuClient = new ThreadedClient();
	String ip = " ";
	int portNum = 0;
	double bet = 0.0;
	String betChoice = " ";
	HashMap<String,Scene> SceneMap = new HashMap<>();
	Timeline displayDelay;
	
	
	public void player_one_bet_text() {
		javafx.scene.control.TextField player_one_bet = new javafx.scene.control.TextField();
		player_one_bet.setMaxWidth(130);
		player_one_bet.setAlignment(Pos.CENTER);
		player_one_bet.toBack();

		player_one_bet.setOnMouseClicked(action -> player_one_bet.clear());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("The Game of 3 Card Poker");
		
		
		
				
		SceneMap.put("root",createTitleScreen(primaryStage)); // adding root scene to hashmap
		SceneMap.put("server", createServerScreen(primaryStage)); // adding server select screen to hashmap
		SceneMap.put("bet", createBetScreen(primaryStage)); // adding add bet screen to hashmap
		SceneMap.put("results",createResultScreen(primaryStage)); // adding the results screen to hashmap
		primaryStage.setScene(SceneMap.get("root"));
		primaryStage.show();
	}
	
	public Scene createTitleScreen(Stage S) { // screen that greets the client upon booting program up
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: seagreen");
		t1 = new Text("Welcome to 3 Card Poker!");
		t1.setStyle("-fx-font-size: 34");
		startbtn = new Button("Start Game!");
		startbtn.setOnAction(e->S.setScene(SceneMap.get("server")));
		quitbtn = new Button("Quit Game");
		quitbtn.setOnAction(e->{ // exit the program and cease execution
			System.exit(0);
			Platform.exit();
		});
		VBox myB = new VBox(75,t1,startbtn,quitbtn);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		return new Scene (pane,700,700); 
	} // end of createTitleScreen
	
	public Scene createServerScreen(Stage S) { // screen where the client will input IP and port number
		BorderPane pane = new BorderPane();
		confirmbtn = new Button("Confirm"); // button to take user to the game screen
		confirmbtn.setOnAction(e->S.setScene(SceneMap.get("bet")));
		pane.setStyle("-fx-background-color: seagreen");
		tf1 = new TextField();
		tf1.setPrefWidth(250);
		tf1.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)) {
			ip =((tf1.getText().toString())); // setting the IP, user can re-enter as many times as necessary
			tf1.clear();
		}});
		tf2 = new TextField();
		tf2.setPrefWidth(250);
		tf2.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)) {
			portNum =(Integer.valueOf(tf2.getText())); // setting the portNum, can be re-entered
			tf2.clear();
		}});
		t2 = new Text("To Connect to a Server");
		t2.setStyle("-fx-font-size: 45");
		t3 = new Text("Enter IP here and press Enter");
		t3.setStyle("-fx-font-size: 20");
		t4 = new Text("Enter port number here and press Enter");
		t4.setStyle("-fx-font-size: 20");
		t5 = new Text("Enter your port and IP before you confirm!");
		t5.setStyle("-fx-font-size: 30");
		VBox myB1 = new VBox(15,t3,tf1);
		VBox myB2 = new VBox(15,t4,tf2);
		HBox myHB = new HBox(35,myB1,myB2);
		VBox myB3 = new VBox(75,t2,myHB,t5,confirmbtn);
		myB1.setAlignment(Pos.CENTER);
		myB2.setAlignment(Pos.CENTER);
		myHB.setAlignment(Pos.CENTER);
		myB3.setAlignment(Pos.CENTER);
		pane.setCenter(myB3);
		return new Scene(pane,700,700);
		
	} // end of create server screen
	
	public Scene createBetScreen(Stage S) { // create the screen where the user selects their bet amount and who they're betting on
		playerOne = new Player();
		theDealer = new Dealer();
		BorderPane pane = new BorderPane();
		ListView EventlistView = new ListView();
		
		EventlistView.setEditable(false);
		EventlistView.getItems().add("Player turn!");
		
		Text dealer_title = new Text("Dealer");
		dealer_title.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC,  30));
		dealer_title.setFill(Color.WHITE);
		
		VBox dealerName = new VBox(dealer_title);
		dealerName.setAlignment(TOP_CENTER);
		dealerName.setSpacing(40);
		
		MenuBar menubar = new MenuBar();
		menu.getItems().addAll(exit_menu, fresh_start_menu, new_look_menu);
		menubar.getMenus().addAll(menu);
		exit_menu.setOnAction(e-> Platform.exit());
		
		TextArea infoBox = new TextArea();
		infoBox.setStyle("-fx-opacity: 0.40");
		infoBox.setWrapText(true);
		infoBox.setPrefHeight(500);
		infoBox.setPrefWidth(200);
		infoBox.setEditable(false);
		
		infoBox.appendText("Welcome to 3 card poker!\n");
		infoBox.appendText("Player has joined the game..\n");
		infoBox.appendText("-----------------------------\n");
		// pair plus wager rules
		infoBox.appendText("\nPair Plus Bet Payout:\n");
		infoBox.appendText("Straight Flush: 40 to 1\n");
		infoBox.appendText("Three of a Kind: 30 to 1\n");
		infoBox.appendText("Straight: 6 to 1\n");
		infoBox.appendText("Flush: 3 to 1\n");
		infoBox.appendText("Pair: 1 to 1\n");
		infoBox.appendText("\n-----------------------------\n");
		
		
		Rectangle player1card = new Rectangle(110, 130);
		player1card.setFill(new ImagePattern(card_backing));
		Rectangle player1card2 = new Rectangle(110, 130);
		player1card2.setFill(new ImagePattern(card_backing));
		Rectangle player1card3 = new Rectangle(110, 130);
		player1card3.setFill(new ImagePattern(card_backing));
		
		Rectangle dealer = new Rectangle(110, 130);
		dealer.setFill(new ImagePattern(card_backing));
		Rectangle dealer2 = new Rectangle(110, 130);
		dealer2.setFill(new ImagePattern(card_backing));
		Rectangle dealer3 = new Rectangle(110, 130);
		dealer3.setFill(new ImagePattern(card_backing));
		
		HBox playerCards = new HBox(5, player1card, player1card2, player1card3);
		playerCards.setLayoutX(90);  // left to right
		playerCards.setLayoutY(400);
		
		HBox dealerCards = new HBox(5, dealer, dealer2, dealer3);
		dealerCards.setLayoutX(90);  // left to right
		dealerCards.setLayoutY(400);
		
		//Create a new VBox that holds the buttons and betting text box and set it under Player 2's cards
		TextField Player1Text;
		//Create new text field
		Player1Text = new TextField();
		Player1Text.setMaxWidth(150);
		Player1Text.setAlignment(CENTER);
		Player1Text.toBack();
		
		Player1Text.setOnMouseClicked(action -> Player1Text.clear());

		// Play or fold for player
		Button play1 = new Button("Play");
		play1.setPrefWidth(70);
		play1.setDisable(true);

		// Play or fold for player
		Button fold = new Button("Fold");
		fold.setPrefWidth(70);
		fold.setDisable(true);
		
     	// deal button
		Button deal = new Button("Deal");
		deal.setPrefWidth(100);
		deal.setLayoutY(40);
		
		TextField textField1 = new TextField("");
		Text label1 = new Text("Ante Bet:");
		label1.setFont(Font.font ("Comic Sans MS", 12));
		label1.setFill(Color.WHITE);
		
		TextField textField2 = new TextField("");
		Text label2 = new Text("Pair Plus:");
		label2.setFont(Font.font ("Comic Sans MS", 12));
		label2.setFill(Color.WHITE);
		
		TextField textField3 = new TextField("");
		Text label3 = new Text("Play Wager:");
		label3.setFont(Font.font ("Comic Sans MS", 12));
		label3.setFill(Color.WHITE);
		
		TextArea displayTotalWinnings = new TextArea("");
		Text displayTotalWinnings_label = new Text("Total Winnings: ");
		displayTotalWinnings_label.setFont(Font.font ("Comic Sans MS", 10));
		displayTotalWinnings_label.setFill(Color.WHITE);
		
		displayTotalWinnings.setText("Player: \n");
		Player player1 = new Player();
		displayTotalWinnings.appendText("Balance: $" + player1.get_player_balance());
		
		
		VBox game_txt_field = new VBox(5, new HBox(5, label1, textField1),new HBox(5, label3,textField3), new HBox(5, label2, textField2));
		
		
		HBox TotalWinnings = new HBox(5, new VBox(5, displayTotalWinnings_label, displayTotalWinnings));
		TotalWinnings.setAlignment(CENTER);
		
		Text dealerText = new Text("Dealer");
		dealerText.setFont(Font.font ("Comic Sans MS", 30));
		dealerText.setFill(Color.WHITE);
		HBox dealerCard_box = new HBox(dealerCards); // contain dealer cards
		dealerCard_box.setAlignment(CENTER);
		VBox dealerBox = new VBox(dealerText, dealerCard_box);
		HBox dealBox = new HBox(deal, play1, fold);
		dealBox.setAlignment(CENTER);
		dealerBox.setAlignment(CENTER);
		
		Text playerOneText = new Text("Player");
		playerOneText.setFont(Font.font ("Comic Sans MS", 30));
		playerOneText.setFill(Color.WHITE);

		// HBox containing player 1 cards
		HBox playerOneCards = new HBox(playerCards); // contains player 1 cards
		playerOneCards.setAlignment(CENTER);
		VBox playerOneBox = new VBox(dealBox, playerOneText, playerOneCards);
		VBox game_txtField = new VBox(5,game_txt_field);
		playerOneBox.setAlignment(CENTER);


		// VBOX for check
		VBox check = new VBox((Check));
		check.setAlignment(CENTER);
		
		HBox game_txt = new HBox(5, game_txtField, infoBox);
		game_txt.setAlignment(CENTER_LEFT);


		VBox whole_scene = new VBox(dealerBox,dealBox,playerOneBox, game_txt,check, TotalWinnings);
		
		
		BorderPane pane2 = new BorderPane();
		pane2.setTop(menubar);
		pane.setStyle("-fx-background-color: seagreen");
		
		
		Check.setOnAction(action -> {

			theDealer.dealersHand = theDealer.dealHand();
			playerOne.hand = theDealer.dealHand();   // get back array of 3 cards. Each card have 1 value and a suit

			int player1_ante = Integer.parseInt(textField1.getText());  // ante bet for player 1
			int  player1_PPB = Integer.parseInt(textField2.getText());  // PP wager player 1
			//disable buttons
			play1.setDisable(true);
			fold.setDisable(true);
			// if all buttons are disabled
			if (play1.isDisabled() && fold.isDisabled()) {
				//	reveal dealers cards + calculate winnings and display winnings information.
				String Dealer_path = ("images/" + Integer.toString(theDealer.dealersHand.get(0).value)
						+ String.valueOf(theDealer.dealersHand.get(0).suit) + ".png");
				Image Dealer_card1_image = new Image(Dealer_path);
				dealer.setFill(new ImagePattern(Dealer_card1_image));

				String Dealer_path2 = ("images/" + Integer.toString(theDealer.dealersHand.get(1).value)
						+ String.valueOf(theDealer.dealersHand.get(1).suit) + ".png");
				System.out.println(Dealer_path);
				Image Dealer_card1_image2 = new Image(Dealer_path2);
				dealer2.setFill(new ImagePattern(Dealer_card1_image2));

				String Dealer_path3 = ("images/" + Integer.toString(theDealer.dealersHand.get(2).value)
						+ String.valueOf(theDealer.dealersHand.get(2).suit) + ".png");
				System.out.println(Dealer_path);
				Image Dealer_card1_image3 = new Image(Dealer_path3);
				dealer3.setFill(new ImagePattern(Dealer_card1_image3));

				int player1_Rank = ThreeCardLogic.evalHand(playerOne.getHand());

				//calculate winnings here:
//				Check PPB for player. Set the winnings
				if ((player1click = true) && (player1_PPB != 0) && (ThreeCardLogic.evalHand(playerOne.getHand()) > 0)) {
					if ((player1_PPB != 0) && (ThreeCardLogic.evalHand(playerOne.getHand()) > 0)) {
						infoBox.appendText("Player 1 has won the PPB ");
						if (player1_Rank == 1)
							infoBox.appendText("with a straight flush!\n");
						if (player1_Rank == 2)
							infoBox.appendText("with a three of a kind!\n");
						if (player1_Rank == 3)
							infoBox.appendText("with a straight!\n");
						if (player1_Rank == 4)
							infoBox.appendText("with a flush!\n");
						if (player1_Rank == 5)
							infoBox.appendText("with a pair!\n");
						playerOne.get_player_balance();
						playerOne.setBet(player1_ante);
						playerOne.set_all_winnings((ThreeCardLogic.evalPPWinnings(playerOne.getHand(),
								playerOne.getPairPlusBet()) + playerOne.getPairPlusBet()));


					}


					int player1_Wins = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);


					//Evaluate player 1's game
					if (player1_Wins == 0) {
						infoBox.appendText("\nPlayer has lost with:\n");
						infoBox.appendText("\nPlayer has lost with:\n" + playerOne.getCardsAsString() + "\n");
						if (player1_Wins == 0) {
							// no one wins
							infoBox.appendText("\nNo wins:(\n");
							playerOne.setBet(player1_ante);
						}
						playerOne.set_all_winnings((ThreeCardLogic.evalPPWinnings(playerOne.getHand(), playerOne.getPairPlusBet()) + playerOne.getPairPlusBet()));

					} else if (player1_Wins == 1) {
						playerOne.set_all_winnings(playerOne.getBet() * 2);
						infoBox.appendText("\nPlayer has won with:\n");
					    infoBox.appendText("\nPlayer has won with:\n" + playerOne.getCardsAsString() + "\n");
						playerOne.setBet(player1_ante);
						playerOne.set_all_winnings((ThreeCardLogic.evalPPWinnings(playerOne.getHand(), playerOne.getPairPlusBet()) + playerOne.getPairPlusBet()));
						playerOne.get_total_winnings();
					}
					player1_Wins = 0;

				}
				playerOne.set_all_winnings(playerOne.getBet());
				S.setScene(SceneMap.get("results"));

			}

			displayTotalWinnings.appendText("\nTotal Balance of player: $"+ String.valueOf(playerOne.get_player_balance()));
			displayTotalWinnings.appendText("\nTotal winnings of player: $"+ String.valueOf(playerOne.get_total_winnings()));

			System.out.println("\nGET BET: $"+ String.valueOf(playerOne.getBet()));
			System.out.println("\nTOTAL WINNINGS: $"+ String.valueOf(playerOne.get_total_winnings()));
			// enable/disable buttons again
			play1.setDisable(false);
			fold.setDisable(false);
			Check.setDisable(true);
			deal.setDisable(false);

			//clear bets for players
			playerOne.clearBets();
		});



		

		// If player hits play
		play1.setOnAction(action -> {

			int player1_ante = Integer.parseInt(textField1.getText());  // ante bet for player 1
			int  player1_play_wager = Integer.parseInt(textField3.getText());  // play wager player 1
			int player1_Wins;

			// boolean data member to keep track that players chose play or fold

			if (player1_ante ==  player1_play_wager) {
				play1.setDisable(true);
				fold.setDisable(true);
				player1click = true;  // create a variable to use in check event
				Check.setDisable(false);

			} else {
				infoBox.appendText("Player: Play wager is not equal to ante bet. Try again.!\n");
			}

		});
		
		fold.setOnAction(e->{nuClient.send("you lost");});
		fold.setOnAction(action -> {

			S.setScene(SceneMap.get("results"));
			
		});


		// player hits deal button
		deal.setOnAction(action -> {

			// ante bet from 5 to 25 (deal cards for player and dealer)
			int player1_ante = Integer.parseInt(textField1.getText());  // player 1 ante bet

			if (player1_ante >=5 && player1_ante <=25) {
				play1.setDisable(false);
				fold.setDisable(false);
				deal.setDisable(true);
				theDealer.dealersHand = theDealer.dealHand();
				playerOne.hand = theDealer.dealHand();   // get back array of 3 cards. Each card have 1 value and a suit

				//set dealer cards to face down
				dealer.setFill(new ImagePattern(card_backing));
				dealer2.setFill(new ImagePattern(card_backing));
				dealer3.setFill(new ImagePattern(card_backing));
				infoBox.appendText("Both bets were valid\n");

				// display cards player 1
				String path = ("images/" + Integer.toString(playerOne.hand.get(0).value)
						+ String.valueOf(playerOne.hand.get(0).suit) + ".png");
				Image card1_image = new Image(path);
				player1card.setFill(new ImagePattern(card1_image));

				String path2 = ("images/" + Integer.toString(playerOne.hand.get(1).value)
						+ String.valueOf(playerOne.hand.get(1).suit) + ".png");
				Image card2_image = new Image(path2);
				player1card2.setFill(new ImagePattern(card2_image));

				String path3 = ("images/" + Integer.toString(playerOne.hand.get(2).value)
						+ String.valueOf(playerOne.hand.get(2).suit) + ".png");
				Image card3_image = new Image(path3);
				player1card3.setFill(new ImagePattern(card3_image));


			} else {
				if (player1_ante <5 && player1_ante >25) {
					infoBox.appendText("\nInvalid ante wager. Please place a bet between $5-$25\n");
				}
				
			}
 
		});
		
		
		
		new_look_menu.setOnAction(actionEvent ->{
			pane.setStyle("-fx-background-color: grey");
			pane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#778899"), new CornerRadii(4.5), new Insets(10))));
			whole_scene.setBackground(new Background(new BackgroundFill(Paint.valueOf("#778899"), new CornerRadii(4.5), new Insets(10))));

			player1card.setFill(new ImagePattern(card_backing_Blue));
			player1card2.setFill(new ImagePattern(card_backing_Blue));
			player1card3.setFill(new ImagePattern(card_backing_Blue));

			//set the backings of the cards for the dealer
			dealer.setFill(new ImagePattern(card_backing_Blue));
			dealer2.setFill(new ImagePattern(card_backing_Blue));
			dealer3.setFill(new ImagePattern(card_backing_Blue));

	});

	// menu handler for fresh start
	fresh_start_menu.setOnAction(actionEvent ->{
		//reset all buttons
		deal.setDisable(false);
		play1.setDisable(true);
		fold.setDisable(true);
		check.setDisable(true);
		
		player1card.setFill(new ImagePattern(card_backing));
		player1card2.setFill(new ImagePattern(card_backing));
		player1card3.setFill(new ImagePattern(card_backing));

		//set the backings of the cards for the dealer
		dealer.setFill(new ImagePattern(card_backing));
		dealer2.setFill(new ImagePattern(card_backing));
		dealer3.setFill(new ImagePattern(card_backing));

		//clear all text fields
		textField1.clear();
		textField2.clear();
		textField3.clear();
		displayTotalWinnings.clear();
		// clear the info box
		infoBox.clear();
		infoBox.appendText("The game has been reset!\n");
		infoBox.appendText("Player has joined the game\n");
		infoBox.appendText("-------------------------------\n");
		infoBox.appendText("\nPair Plus Bet Payout:\n");
		infoBox.appendText("Straight Flush: 40 to 1\n");
		infoBox.appendText("Three of a Kind: 30 to 1\n");
		infoBox.appendText("Straight: 6 to 1\n");
		infoBox.appendText("Flush: 3 to 1\n");
		infoBox.appendText("Pair: 1 to 1\n");
		infoBox.appendText("\n-------------------------------\n");

		//reset player variables

		// reset to original scene
		scene2.setFill(Paint.valueOf("2B2A33"));
		pane.setBackground(new Background(new BackgroundFill(Paint.valueOf("2B2A33"), new CornerRadii(4.5), new Insets(10))));
		whole_scene.setBackground(new Background(new BackgroundFill(Paint.valueOf("2B2A33"), new CornerRadii(4.5), new Insets(10))));

		// get new deck from dealer
		theDealer.new_deck();

	});
		
		pane.setTop(pane2);
		pane.setCenter(whole_scene);
		return new Scene(pane,700,700);
		
	}
	
	
	
	public Scene createResultScreen(Stage S) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: seagreen");
		Button exit = new Button("Exit");
		restartbtn = new Button("Play again!");
		exit.setOnAction(e->{
			System.exit(0);
			Platform.exit();
		});
		restartbtn.setOnAction(e->{
			S.setScene(SceneMap.get("bet"));
		});
		mainbtn = new Button("Main Menu");
		t4 = new Text("-~Game Results!~-");
		t4.setStyle("-fx-font-size: 45");
		mainbtn.setOnAction(e->S.setScene(SceneMap.get("root")));
		listItems = new ListView<String>();
		listItems.prefWidth(400);
		VBox myB = new VBox(75,t4,listItems,mainbtn,restartbtn, exit);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		pane.setPadding(new Insets(85));
		return new Scene(pane,700,700);
	}

}
