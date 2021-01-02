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
	private JLabel text = new JLabel("Tic tac toe");
	private boolean playerTurn;
	private Board ticTacTou = new Board();
	
	
	
	public Graphic() {				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(800,800);
		frame.setVisible(true);
						
		addButtons();
		addTitle();
						
		frame.add(titlePanel,BorderLayout.NORTH);		
		frame.add(buttonPanel,BorderLayout.CENTER);	
		
		firstTurn();
	}
	
	
	
	private void firstTurn() {				
		Random r = new Random();
		if(r.nextInt(2)==1) {
			playerTurn=true;
		}
		else {
			playerTurn=false;
		}
	}
	
	//Adding title 
	private void addTitle() {
		text.setBackground(Color.DARK_GRAY);
		text.setForeground(Color.BLUE);
		text.setFont(new Font("Ink Free",Font.BOLD,75));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setOpaque(true);
				
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 150);
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
					if(playerTurn) {
						buttons[i][j].setText("X");
						buttons[i][j].setEnabled(false);
						ticTacTou.getFields()[i][j]=Field.X;
						playerTurn=false;
						if(ticTacTou.checkWinner()==1) {
							text.setText("X won");
							disableButtons();
						}
					}
					else {
						buttons[i][j].setText("O");
						buttons[i][j].setEnabled(false);
						ticTacTou.getFields()[i][j]=Field.O;
						playerTurn=true;
						if(ticTacTou.checkWinner()==-1) {
							text.setText("O won");
							disableButtons();
						}
					}
				}					
			}
		}		
	}
}
