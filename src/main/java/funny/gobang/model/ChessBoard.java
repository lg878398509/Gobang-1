package funny.gobang.model;

import static funny.gobang.model.ChessType.*;

public class ChessBoard implements Cloneable{
	
		private final int[][] board;
		
		public final int capacity;
		
		public ChessBoard(int capacity){
			this.capacity = capacity;
			this.board = new int[capacity][capacity];
			clear();
		}
		
		public ChessBoard(int capacity, int[][] board){
			this.capacity = capacity;
			this.board = board;
		}
		
		public static ChessBoard valueOf(int[][] board){
			return new ChessBoard(board[0].length, board);
		}
		
		public synchronized int[][] getBoard() {
			int[][] old = board;
			final int[][] current = new int[capacity][capacity];
			for(int i=0; i<capacity; i++)
				  for(int j=0; j<capacity; j++)
					  current[i][j]=old[i][j];
			return current;
		}

		@Override
		public synchronized ChessBoard clone(){
			int[][] board = this.getBoard();
			return new ChessBoard(capacity, board);
		} 


		public int getCapacity() {
			return capacity;
		}

		public synchronized boolean canDown(Point point){
			int x = point.getX();
			int y = point.getY();
			return board[x][y] == EMPTY;
		}
		
		public synchronized boolean canDown(int x, int y){
			return board[x][y] == EMPTY;
		}

		public synchronized void clear(){
			for (int i=0; i < capacity; i++){
				for(int j=0; j < capacity; j++){
					board[i][j] = EMPTY;
				}
			}
		}
		
		public synchronized int getChessType(Point point){
			return board[point.getX()][point.getY()];
		}
		
		public synchronized int getChessType(int x,int y){
			return board[x][y];
		}
		
		public synchronized int remove(Point point){
			int x = point.getX();
			int y = point.getY();
			int chessType = board[x][y];
			board[x][y] = EMPTY;
			return chessType;
		}
		
		public synchronized void downChess(Point point,int chessType){
			int x = point.getX();
			int y = point.getY();
			board[x][y] = chessType;
		}
	
}
