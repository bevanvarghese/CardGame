import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 
 * @author Bevan Varghese
 * Class name: GambleGame
 * This class contains all variables and functions required to run A Simple Card Game. 
 * It implements the ActionListener interface. 
 */
public class GambleGame implements ActionListener {
	
	/**
	 * Instance variable descriptions:
	 * MainPanel - JPanel holding the main frame
	 * DealerPanel - JPanel holding the dealer's cards
	 * PlayerPanel - JPanel holding the player's cards
	 * RpCardBtnPanel - JPanel holding the "Replace Card" buttons
	 * ButtonPanel - JPamel holding the betting field and the "Start" and "Result" buttons
	 * InfoPanel - JPanel holding the information about betting amount, remaining money, etc.
	 * menuBar - JMenuBar holding the menu bar
	 * control - JMenu option on the menu bar, holding "Exit"
	 * help - JMenu option on the menu bar, holding "Instructions"
	 * exit - JMenuItem that exits the game
	 * rules - JMenuItem that displays game rules
	 * dealCard1, dealCard2, dealCard3 - JLabels holding the dealer's three cards
	 * playCard1, playCard2, playCard3 - JLabels holding the player's three cards
	 * replace1, replace2, replace3 - JButtons to replace each of the player's three cards
	 * bet - JLabel showing "Bet: $" besides the betting field
	 * info1, info2 - JLabels showing the first  and second phrases of information in InfoPanel
	 * inputBet - JTextField, holds the user's bet amount
	 * start, result - JButtons to start the game and declare the result
	 * back - ImageIcon component holding the card's back design (card_back.gif)
	 * wallet - int, user's remaining money
	 * betAmt - int, user's bet amount
	 * repCounter - int, keeps track of how many cards have been replaced in each round
	 * frame - JFrame holding together all GUI components
	 * cards - String[], holds eight different cards from the deck
	 *         Cards 1, 2 and 3 are the player's initial three cards.
	 *         Cards 4 and 5 are cards that will replace the player's initial cards (if used).
	 *         Cards 6, 7 and 8 are the dealer's three cards.
	 * cardValues - int[], holds the face value of each card in cards[]
	 * playFaces - int, number of special cards the player has
	 * playRem - int, remainder after dividing the sum of the player's non-special cards by 10
	 * dealFaces - int, number of special cards the dealer has
	 * dealRem - int, remainder after dividing the sum of the dealer's non-special cards by 10
	 */	
	JPanel MainPanel, DealerPanel, PlayerPanel, RpCardBtnPanel, ButtonPanel, InfoPanel;
	JMenuBar menuBar;
	JMenu control, help;
	JMenuItem exit, rules;
	JLabel dealCard1, dealCard2, dealCard3, playCard1, playCard2, playCard3;
	JButton replace1, replace2, replace3;
	JLabel bet, info1, info2;
	JTextField inputBet;
	JButton start, result;
	ImageIcon back;
	int wallet, betAmt, repCounter;
	JFrame frame;
	String cards[];
	int cardValues[];
	int playFaces, playRem, dealFaces, dealRem;
	private int C1index, C2index, C3index;
	
	
	/**
	 * Constructor name: GambleGame
	 * Description: Initializes instance variables of the class and sets up the GUI components.
	 */
	GambleGame() {
		MainPanel = new JPanel();
		DealerPanel = new JPanel();
		PlayerPanel = new JPanel();
		RpCardBtnPanel = new JPanel();
		ButtonPanel = new JPanel();
		InfoPanel = new JPanel();
		dealCard1 = new JLabel();
		dealCard2 = new JLabel();
		dealCard3 = new JLabel();
		playCard1 = new JLabel();
		playCard2 = new JLabel();
		playCard3 = new JLabel();
		replace1 = new JButton("Replace Card 1");
		replace2 = new JButton("Replace Card 2");
		replace3 = new JButton("Replace Card 3");
		replace1.setEnabled(false);
		replace2.setEnabled(false);
		replace3.setEnabled(false);
		replace1.addActionListener(this);
		replace2.addActionListener(this);;
		replace3.addActionListener(this);
		bet = new JLabel("Bet :$");
		info1 = new JLabel("Please place your bet! ");
		info2 = new JLabel("Amount of money you have: $100");
		inputBet = new JTextField(10);
		start = new JButton("Start");
		start.addActionListener(this);
		result = new JButton("Result");
		result.setEnabled(false);
		result.addActionListener(this);
		back = new ImageIcon("card_back.gif");
		wallet = 100;
		dealCard1.setIcon(back);
		dealCard2.setIcon(back);
		dealCard3.setIcon(back);
		playCard1.setIcon(back);
		playCard2.setIcon(back);
		playCard3.setIcon(back);
		DealerPanel.add(dealCard1);
		DealerPanel.add(dealCard2);
		DealerPanel.add(dealCard3);
		PlayerPanel.add(playCard1);
		PlayerPanel.add(playCard2);
		PlayerPanel.add(playCard3);
		RpCardBtnPanel.add(replace1);
		RpCardBtnPanel.add(replace2);
		RpCardBtnPanel.add(replace3);
		ButtonPanel.add(bet);
		ButtonPanel.add(inputBet);
		ButtonPanel.add(start);
		ButtonPanel.add(result);
		InfoPanel.add(info1);	
		InfoPanel.add(info2);
		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);
		menuBar = new JMenuBar();
		control = new JMenu("Control");
		help = new JMenu("Help"); 
		exit = new JMenuItem("Exit");
		rules = new JMenuItem("Instructions");
		exit.addActionListener(this);
		rules.addActionListener(this);
		control.add(exit);
		help.add(rules);
		menuBar.add(control);
		menuBar.add(help);
		MainPanel.setLayout(new GridLayout(5,11));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);
		frame = new JFrame();
		cards = new String[8];
		cardValues = new int[8];
		repCounter = 0;
		playFaces = 0;
		dealFaces = 0;
		playRem = 0;
		dealRem = 0;
	}
	
	/**
	 * Function name: startGame
	 * Description: When the player presses "Start", shuffle() is called and relevant buttons are enabled. 
	 */
	public void startGame() {
		start.setEnabled(false);
		shuffle();
		replace1.setEnabled(true);
		replace2.setEnabled(true);
		replace3.setEnabled(true);
		result.setEnabled(true);
		info1.setText("Your current bet is: $"+betAmt);
		repCounter = 0;
		playFaces = 0;
		playRem = 0;
		dealFaces = 0;
		dealRem = 0;
	}
	
	/**
	 * Function name: shuffle
	 * Description: Shuffles the deck and takes out eight cards.
	 *              Stores these cards in cards[] and their face values in cardValues[].
	 *              Displays the player's first three cards.
	 */
	public void shuffle() {
		Random random = new Random();
		int suit, num, i=0, flag;
		String temp;
		while(i<8) {
			flag = 0;
			//generating a random card
			suit = Math.abs(random.nextInt())%4+1;
			num = Math.abs(random.nextInt())%13+1;
			temp = Integer.toString(suit)+Integer.toString(num);
			for(int j=0;j<i;j++) {
				//checking to see if a generated card has already been drawn
				if(cards[j].equals(temp)) {
					flag = 1;
				}
			}
			if(flag!=1) {
				cards[i] = temp;
				cardValues[i] = num;
				i++;
			}
		}
		playCard1.setIcon(new ImageIcon("card_"+cards[0]+".gif"));
		playCard2.setIcon(new ImageIcon("card_"+cards[1]+".gif"));
		playCard3.setIcon(new ImageIcon("card_"+cards[2]+".gif"));
		C1index = 0;
		C2index = 1;
		C3index = 2;
	}
	
	/**
	 * Function name: replacementLimit()
	 * Description: Disables all the "Replace Card" buttons if two cards have been replaced in a round.
	 */
	public void replacementLimit() {
		if(repCounter == 2) {
			replace1.setEnabled(false);
			replace2.setEnabled(false);
			replace3.setEnabled(false);
		}
	}
	
	/**
	 * Function name: isFace
	 * Parameter: int i - face value of a card
	 * Return type: boolean
	 * Description: Checks if a card is special by checking its face value.
	 *              Returns true if it's a special card, false if not. 
	 */
	public boolean isFace(int i) {
		if(i==11 || i==12 || i==13) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Function name: playerCalc
	 * Description: Calculates the number of special cards the player has and stores it in playFaces.
	 *              Calculates the remainder after adding the player's non-special cards and dividing by 10, stores it in playRem.
	 */
	public void playerCalc() {
		if(isFace(cardValues[C1index])) {
			playFaces++;
		} else {
			playRem += cardValues[C1index];
		}
		if(isFace(cardValues[C2index])) {
			playFaces++;
		} else {
			playRem += cardValues[C2index];
		}
		if(isFace(cardValues[C3index])) {
			playFaces++;
		} else {
			playRem += cardValues[C3index];
		}
		playRem %= 10;
	}
	
	/**
	 * Function name: dealerCalc
	 * Description: Calculates the number of special cards the dealer has and stores it in dealFaces.
	 *              Calculates the remainder after adding the dealer's non-special cards and dividing by 10, stores it in dealRem.
	 */
	public void dealerCalc() {
		for(int i=5;i<=7;i++) {
			if(isFace(cardValues[i])) {
				dealFaces++;
			} else {
				dealRem += cardValues[i];
			}
		}
		dealRem %= 10;
	}
	
	/**
	 * Function name: playerWins
	 * Description: Displays the relevant message and awards the player with their bet amount.
	 */
	public void playerWins() {
		JOptionPane.showMessageDialog(frame, "Congratulations! You win this round!");
		wallet += betAmt;
		betAmt = 0;
	}
	
	/**
	 * Function name: dealerWins
	 * Description: Displays the relevant messages and removes the bet amount from tbe player's wallet. 
	 */
	public void dealerWins() {
		JOptionPane.showMessageDialog(frame,  "Sorry! The Dealer wins this round");
		wallet -= betAmt;
		betAmt = 0;
	}
	
	/**
	 * Function name: results
	 * Description: Reveals the dealer's cards and determines the result of the game.
	 *              Enables the "start" button and disables the other buttons. 
	 *              Changes the information labels to relevant messages.
	 *              Ends the game if the player is out of money.
	 */
	public void results() {
		dealCard1.setIcon(new ImageIcon("card_"+cards[5]+".gif"));
		dealCard2.setIcon(new ImageIcon("card_"+cards[6]+".gif"));
		dealCard3.setIcon(new ImageIcon("card_"+cards[7]+".gif"));
		dealerCalc();
		playerCalc();
		replace1.setEnabled(false);
		replace2.setEnabled(false);
		replace3.setEnabled(false);
		result.setEnabled(false);
		if(playFaces > dealFaces) {
			playerWins();
		} else if(dealFaces > playFaces) {
			dealerWins();
		} else {
			if(playRem > dealRem) {
				playerWins();
			} else {
				dealerWins();
			}
		}
		if(wallet == 0) {
			start.setEnabled(false);
			info1.setText("You have no more monney! ");
			info2.setText("Please start a new game!");
			JOptionPane.showMessageDialog(frame, "Game over!\nYou have no more money!\nPlease start a new game!");
			return;
		}
		playCard1.setIcon(back);
		playCard2.setIcon(back);
		playCard3.setIcon(back);
		dealCard1.setIcon(back);
		dealCard2.setIcon(back);
		dealCard3.setIcon(back);
		info1.setText("Please place your bet! ");
		info2.setText("Amount of money you have: $"+wallet);
		inputBet.setText("");
		start.setEnabled(true);
	}
	
	/**
	 * Function name: actionPerformed
	 * Description: Action listener, performs relevant operations if a certain button is pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		//Exits the program if "Exit" is pressed
		if(e.getSource() == exit) {
			System.exit(0);
		}
		//Displays game instructions if "Instructions" is pressed
		else if(e.getSource() == rules) {
			JOptionPane.showMessageDialog(frame, "Rules to determine who has better cards:\nJ, Q, K are regarded as special cards.\nRule 1: The one with more special cards wins.\nRule 2: If both have the same number of special cards, add the face values of the other card(s) and take the remainder after dividing the sum by 10. The one with a bigger remainder wins. (Note: Ace = 1).\nRule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.");
		}
		//Starts the round if "Start" is pressed
		else if(e.getSource() == start) {
			if(!inputBet.getText().isEmpty()) {
				String temp = inputBet.getText();
				if(wallet != 0) {
					if(temp.contains("-") || temp.contains(".")) {
						JOptionPane.showMessageDialog(frame,  "WARNING: The bet you place must be a positive integer!");
						inputBet.setText("");
					}
					else {
						betAmt = Integer.parseInt(temp);
						if(betAmt>wallet) {
							JOptionPane.showMessageDialog(frame, "WARNING: The bet you place cannot be more than the money you have!");
							inputBet.setText("");
						} else {
							startGame();
						}
					}	
				}
			}
			else {
				return;
			}
		}
		//Replaces the player's first card if "Replace Card 1" is pressed
		else if(e.getSource() == replace1) {
			repCounter++;
			replacementLimit();
			replace1.setEnabled(false);
			C1index = 2+repCounter;
			playCard1.setIcon(new ImageIcon("card_"+cards[2+repCounter]+".gif"));
		}
		//Replaces the player's second card if "Replace Card 2" is pressed
		else if(e.getSource() == replace2) {
			repCounter++;
			replacementLimit();
			replace2.setEnabled(false);
			C2index = 2+repCounter;
			playCard2.setIcon(new ImageIcon("card_"+cards[2+repCounter]+".gif"));
		}
		//Replaces the player's third card if "Replace Card 3" is pressed
		else if(e.getSource() == replace3) {
			repCounter++;
			replacementLimit();
			replace3.setEnabled(false);
			C3index = 2+repCounter;
			playCard3.setIcon(new ImageIcon("card_"+cards[2+repCounter]+".gif"));
		}
		//Determines the result of a round if "Result" is pressed
		else if(e.getSource() == result) {
			results();
		}
	}
	
	/**
	 * Function name: main
	 * Description: Main method. Creates an object of the class and runs the game. 
	 */
	public static void main(String args[]) {
		GambleGame gg = new GambleGame();
		gg.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gg.frame.getContentPane().add(gg.MainPanel);
		gg.frame.setTitle("A Simple Card Game");
		gg.frame.setJMenuBar(gg.menuBar);
		gg.frame.setSize(400, 700);
		gg.frame.setVisible(true);
		
	}
	
}
