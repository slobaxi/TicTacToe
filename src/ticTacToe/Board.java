package ticTacToe;

public class Board {
	
	private Field[][] fields = new Field[4][4];
	private boolean isXTurn;
	private int boardCap;		
	public Board() {
		super();
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				fields[i][j]=Field.EMPTY;
			}
		}	
		isXTurn=true;
		boardCap=0;
	}
	//checks if a board is full
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
			if(fields[0][i]==fields[1][i] && fields[1][i]==fields[2][i] 
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
			
	//Calls backtrack for every empty field and returns optimal move  
	public  int[] findBestMove() {
		if(boardCap<3) {
			
			switch(boardCap) {
			case 0:
				boardCap++;
				return new int[] {1,1};
			case 1:
				boardCap++;
				if(fields[1][1]==Field.EMPTY) 
					return new int[] {1,1};
				else 
					return new int[] {2,2};
			case 2:
				boardCap++;				
				if(fields[0][0]==Field.O) 
					return new int[] {1,2};
				if(fields[0][1]==Field.O) 
					return new int[] {2,2};
				if(fields[0][2]==Field.O) 
					return new int[] {2,1};
				if(fields[2][0]==Field.O) 
					return new int[] {1,2};
				if(fields[1][0]==Field.O) 
					return new int[] {2,2};
				else 
					return new int[] {0,0};
			default :
				break;
			}										
		}
		int best=-1000;
		int x=-1;
		int y=-1;
		boolean pom=isXTurn;
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(fields[i][j]==Field.EMPTY) {
					int move;
					fields[i][j]=Field.X;
					move =backtrack(false,0,-100,100);	
					fields[i][j]=Field.EMPTY;
					
					if(move>best) {
						best=move;
						x=i;
						y=j;
					}
				}
			}
		}
		
		isXTurn=pom;
		
		return new int[] {x,y};
		
	}
	//MinMax with  
	private int backtrack(boolean isMax,int depth,int alpha,int beta) {		
		int score = checkWinner();		
		if(score==-1) 
			return score;
		if(score==1)
			return score;
		if(isBoardFull())
			return 0;		
		if(isMax) {			
			int best=-100;		
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(fields[i][j]==Field.EMPTY) {
						fields[i][j]=Field.X;
						best = Math.max(best,backtrack(!isMax,depth+1,alpha,beta));
						fields[i][j]=Field.EMPTY;
						alpha=Math.max(best,alpha);				
						if(alpha>=beta) {
							break;
						}
					}
				}
			}
			return alpha;
		}		
		else {
			int best=100;			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(fields[i][j]==Field.EMPTY) {
						fields[i][j]=Field.O;
						best=Math.min(best,backtrack(!isMax,depth+1,alpha,beta));
						fields[i][j]=Field.EMPTY;
						beta=Math.min(best,beta);
						if(alpha>=beta) {
							break;
						}
					}
				}
			}
			return beta;
		}
	}			
	//Getters and setters
	public boolean isXTurn() {
		return isXTurn;
	}
	public void setXTurn(boolean isXTurn) {
		
		this.isXTurn = isXTurn;
	}	
	public int getBoardCap() {
		return boardCap;
	}
	public void setBoardCap(int boardCap) {
		this.boardCap = boardCap;
	}
	public Field[][] getFields() {
		return fields;
	}
	public void setFields(Field[][] fields) {
		this.fields = fields;
	}
}
