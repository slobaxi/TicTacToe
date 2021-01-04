package ticTacToe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*; 


public class Graphic extends JPanel implements ActionListener  {
	
	private JFrame frame=new JFrame();  
	private JPanel buttonPanel=new JPanel(); 
	private JPanel titlePanel=new JPanel();
	private JButton[][] buttons = new JButton[4][4];
	private JLabel text = new JLabel("\t Tic tac toe \t");
	private boolean playerFirst;
	private Board ticTacToe = new Board();
	private JButton start = new JButton("START");
	private JButton restart = new JButton("RESTART");
	
	
	
	public Graphic() {				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(800,800);
		frame.setVisible(true);	
		addRestartButton();
		addButtons();
		addTitle();
		addStartButton();						
		frame.add(titlePanel,BorderLayout.NORTH);		
		frame.add(buttonPanel,BorderLayout.CENTER);			
	}	
	//adding start and restart button
	private void addRestartButton() {
		restart.addActionListener(e->{
			ticTacToe = new Board();
			text.setText("Tic tac toe");
			start.setEnabled(true);
			start.doClick();
		});
		restart.setEnabled(false);
		titlePanel.add(restart);
	}	
	private void addStartButton() {						
		start.addActionListener( e-> {	
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					buttons[i][j].setText("");
					buttons[i][j].setEnabled(true);
				}
			} 
				firstTurn();
				start.setEnabled(false);
				restart.setEnabled(true);
				if(playerFirst) {						
					int[] optimala = ticTacToe.findBestMove();
					buttons[optimala[0]][optimala[1]].setText("X");
					buttons[optimala[0]][optimala[1]].setEnabled(false);
					ticTacToe.getFields()[optimala[0]][optimala[1]]=Field.X;
					ticTacToe.setXTurn(false);;
				}	
		});
		start.setSize(30,20);
		titlePanel.add(start);		
	}
	
	//Decides who is playing first
	private void firstTurn() {				
		Random r = new Random();
		if(r.nextInt(2)==1) {
			playerFirst=true;
		}
		else {
			playerFirst=false;
		}
	}
	
	//Adding title 
	private void addTitle() {
		text.setBackground(Color.DARK_GRAY);
		text.setForeground(Color.BLUE);
		text.setSize(150,100);;
		text.setFont(new Font("Ink Free",Font.BOLD,75));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setOpaque(true);
				
		titlePanel.setBounds(0, 0, 800,150);
		titlePanel.setBackground(Color.DARK_GRAY);
		titlePanel.add(text);
		
	}
	
	//Adding Buttons
	private void addButtons() {
		buttonPanel.setLayout(new GridLayout(4,4));		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setFont(new Font("MV Boli",Font.BOLD,75));
				buttons[i][j].setFocusable(false);
				buttons[i][j].addActionListener(this);
				buttonPanel.add(buttons[i][j]);
				buttons[i][j].setEnabled(false);
			}
		}		
	}
	
	public static void main (String[] args) {		
		new Graphic();				
	}	
	private void disableButtons() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				buttons[i][j].setEnabled(false);
			}	
		}
	}
	
	@Override			
	public void actionPerformed(ActionEvent e) {									
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(e.getSource()==buttons[i][j]) {							
							buttons[i][j].setText("O");
							buttons[i][j].setEnabled(false);
							ticTacToe.getFields()[i][j]=Field.O;
							ticTacToe.setXTurn(true);
							ticTacToe.setBoardCap(ticTacToe.getBoardCap()+1);
							if(ticTacToe.checkWinner()==-1) {
								text.setText("\t O won \t");
								disableButtons();
							}
							else if(ticTacToe.isBoardFull()) {
								text.setText("\t Draw\t");;
							}
							else {
								int[] optimala = ticTacToe.findBestMove();
								buttons[optimala[0]][optimala[1]].setText("X");
								buttons[optimala[0]][optimala[1]].setEnabled(false);
								ticTacToe.getFields()[optimala[0]][optimala[1]]=Field.X;
								ticTacToe.setXTurn(false);
								ticTacToe.setBoardCap(ticTacToe.getBoardCap()+1);
								if(ticTacToe.checkWinner()==1) {
									text.setText("\t X won \t");
									disableButtons();
								}
								if(ticTacToe.isBoardFull()) {
									text.setText("\t Draw \t");
								}
							}																							
				}					
			}
		}		
	}
}
