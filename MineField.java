import java.util.Random;

class MineField{

	private boolean[][] mines,visible;
	private boolean boom;
	private final int rowMax = 5;
	private final int colMax = 10;
	
	//initialize minefield, and ads 15 mines in the minefield
	MineField(){
		
		mines=new boolean[rowMax][colMax];
		visible=new boolean[rowMax][colMax];
		boom=false;
		
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				mines[row][col]=false;
				visible[row][col]=false;
			}
		}
		
		int counter2=15;
		int randomRow,randomCol;
		Random RGenerator=new Random();
		
		while(counter2>0){
			
			randomRow=Math.abs(RGenerator.nextInt()%rowMax);
			randomCol=Math.abs(RGenerator.nextInt()%colMax);
			
			if(trymove(randomRow,randomCol)){
				counter2--;
			}
		}
	}	
	//if this row and coloum has a mine it return false 
	//and if there is no mine it sets a mine there and returns true
	private boolean trymove(int randomRow, int randomCol) {
		if(mines[randomRow][randomCol]){
			return false;
		}
		else{
			mines[randomRow][randomCol]=true;
			return true;
		}
	}
	//boom makes all rows and coloums visible, 
	//sets boom to true to show that a mine has been hit
	//and shows the field to the player
	private void boom() {
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				if(mines[row][col]){
					visible[row][col]=true;
				}
			}
		}
		boom=true;
		show();	
	}
	//returns * if there is a mine, - if a mine has been hit, and a number for showing how many mines are adjecent to this field
	private char drawChar(int row, int col) {
		int count=0;
		//counts how many bombs are adjacent to this field
		if(visible[row][col]){
			if(mines[row][col]) return '*';
			for(int irow=row-1;irow<=row+1;irow++){
				for(int icol=col-1;icol<=col+1;icol++){
					if(icol>=0&&icol<colMax&&irow>=0&&irow<rowMax){
						if(mines[irow][icol]) count++;
					}
				}
			}
		}
		else{
			if(boom){
				return '-';
			}
			{
				return '?';
			}
		}
		switch(count){
		case 0:return '0';
		case 1:return '1';
		case 2:return '2';
		case 3:return '3';
		case 4:return '4';
		case 5:return '5';
		case 6:return '6';
		case 7:return '7';
		case 8:return '8';
		
		
		default:return 'X';
		}
	}
	//returns true if a mine has been hit else it returns false
	public boolean getBoom(){
		
		return boom;
	}
	//checks if the input is a legal input
	public boolean legalMoveString(String input) {
		String[] separated=input.split(" ");
		int row;
		int col;
		try{
			row=Integer.parseInt(separated[0]);
			col=Integer.parseInt(separated[1]);
			if(row<0||col<0||row>=rowMax||col>=colMax){
				throw new java.io.IOException();
			}
		}
		catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}
		//checks if the input is legal
		if(legalMoveValue(row,col)){
			return true;
		}
		else{
			return false;
		}
	}
	//checks if you already steped in the area or if there is a mine in this area.
	private boolean legalMoveValue(int row, int col) {
		if(visible[row][col]){
			System.out.println("You stepped in allready revealed area!");
			return false;
		}
		else{
			visible[row][col]=true;
		}
		
		if(mines[row][col]){
			boom();
			return false;
		}
		
		return true;
	}
	//prints out the minefield
	public void show() {
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 ");
		System.out.println("   ---------------------");
		for(int row=0;row<rowMax;row++){
			System.out.print(row+" |");
			for(int col=0;col<colMax;col++){
				System.out.print(" "+drawChar(row,col));
				
			}
			System.out.println(" |");
		}
		System.out.println("   ---------------------");
	}
	
}
