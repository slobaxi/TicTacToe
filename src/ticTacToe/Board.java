package ticTacToe;

public class Board {
	
	private Field[][] fields = new Field[4][4];
	private boolean isXTurn;
	
	
	public Field[][] getFields() {
		return fields;
	}


	public void setFields(Field[][] fields) {
		this.fields = fields;
	}


	public Board() {
		super();
	}

	public boolean isBoardFull() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(fields[i][j]==Field.EMPTY)
					return false;
			}
		}
		return true;
	}
	
	//1 x won,-1, O won, 0 nothing
	public int checkWinner() {
		
		// check rows and columns		
		for(int i=0;i<4;i++) {		
			if(fields[i][0]==fields[i][1] && fields[i][1]==fields[i][2] 
					&& fields[i][2]==fields[i][3]) {
				if(fields[i][0]==Field.X) 
					return 1;
				if(fields[i][0]==Field.O) 
					return -1;
			}		
		}		
		for(int i=0;i<4;i++) {		
			if(fields[0][i]==fields[1][i] && fields[1][i]==fields[1][i] 
					&& fields[2][i]==fields[3][i] ) {
				if(fields[0][i]==Field.X) 
					return 1;
				if(fields[0][i]==Field.O) 
					return -1;
			}		
		}
		
		//check 2x2 squares
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(fields[0+i][0+j]==fields[0+i][1+j] && fields[0+i][1+j]==fields[1+i][0+j] 
						&& fields[1+i][0+j]==fields[1+i][1+j]) {	
					if(fields[0+i][0+j]==Field.X) 
						return 1;
					if(fields[0+i][0+j]==Field.O) 
						return -1;
				}
			}
		}
		
		//check diagonals
		
		if(fields[0][0]==fields[1][1] && fields[1][1]==fields[2][2] 
				&& fields[2][2]==fields[3][3]) {
			if(fields[0][0]==Field.X) 
				return 1;
			if(fields[0][0]==Field.O) 
				return -1;
		}
		if(fields[0][3]==fields[1][2] && fields[1][2]==fields[2][1] 
				&& fields[2][1]==fields[3][0]) {
			if(fields[0][3]==Field.X) 
				return 1;
			if(fields[0][3]==Field.O) 
				return -1;			
		}
		return 0;
	}

	/*
	private int backtrack() {
		
		int winner=checkWinner();
		
		if(winner==1) 
			return winner;
		if(winner==-1)
			return winner;	
		if(isBoardFull())
			return 0;
		
		if(isXTurn) {
			int bestMove=-100;
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(fields[i][j]==Field.EMPTY) {
						fields[i][j] = Field.X;
						isXTurn=false;
						bestMove=Math.max(bestMove,backtrack());
						fields[i][j]=Field.EMPTY;
					}					
				}
			}
			return bestMove;
		}
		else {
			int bestMove=100;
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(fields[i][j]==Field.EMPTY) {
						fields[i][j] = Field.O;
						isXTurn=true;
						bestMove=Math.min(bestMove,backtrack());
						fields[i][j]=Field.EMPTY;
					}					
				}
			}			
			return bestMove;
		}
				
	}
	
	public  int[] findBestMove() {
		int best=-1000;
		int x=-1;
		int y=-1;
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(!isXTurn) {
					fields[i][j]=Field.O;
					int moveVal = backtrack();
					fields[i][j]=Field.EMPTY;
					if(moveVal>best) {
						x=i;
						y=j;
						best=moveVal;
					}
				}
				else {
					fields[i][j] = Field.X;
					int moveVal = backtrack();
					fields[i][j]=Field.EMPTY;
					if(moveVal>best) {
						x=i;
						y=j;
						best=moveVal;
					}
				}					
			}
				
		}
		
		int[] optimala = new int[2];
		optimala[0]=x;
		optimala[1]=y;	
		return optimala;
	}
	
	public static void main(String[] args) {
		Board b = new Board();
		b.fields[1][1]=Field.X;
		b.fields[1][2]=Field.O;
		int[] optimala = b.findBestMove();
		System.out.println(optimala[0]);
		System.out.println(optimala[1]);
		
		
		System.out.println(b.checkWinner());
	}
	
	*/

}
