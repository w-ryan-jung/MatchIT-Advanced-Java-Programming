package sudoku;

import java.util.Arrays;
import java.util.HashSet;

public class Sudoku {

    private int[][] board;

    public Sudoku(int[][] newBoard){
        this.board = newBoard;
    }

    public boolean solveSudoku(){
        //return solve(0,0);
        return solve(0);

    }

    public int[][] getBoard(){
        return board;
    }

    /*
    private boolean solve(int row, int col){

        if(col == 9){
            col=0;
            row++;
        }

        if(row==9){
            return true;
        }

        int currentNum = board[row][col];

        if(currentNum != 0){
            return solve(row,col+1);
        }else{
            for(int i = 1; i < 10; i++){
                board[row][col] = i;
                if(isValidSudoku()){
                    boolean solved = solve(row,col+1);
                    if(solved)return true;
                }
            }
            board[row][col] = 0;
            return false;
        }

    }*/

    /**
     * Backtrack Algorithm
     * until can solve sudoku it call its self, idx is increased until 81
     * @param idx start position
     * @return True if idx is 81 and solve method return true
     */
    private boolean solve(int idx){


        if(idx==81){
            return true;
        }

        int row = idx / 9;
        int col = idx % 9;
        int currentNum = board[row][col];

//        System.out.println("["+row+"]"+"["+col+"]"+":::"+idx );

        if(currentNum != 0){
            return solve(idx+1);
        }else{
            for(int i = 1; i < 10; i++){//1부터9까지 입력실
                board[row][col] = i;
                if(isValidSudoku()){
                    /**
                     * 정답을 찾을때 까지 리턴 트루일때까지 반복
                     */
                    boolean solved = solve(idx+1);
                    if(solved) {
                        return true; // 트루일때까지 반복
                    }else{
                    }
                }
            }
            board[row][col] = 0;
            return false;
        }

    }

    /**
     * Check the sudoku values if it is valid or not
     * @return true if row,column,sub grid values don't overlap from 1 to 9
     */
    private boolean isValidSudoku(){


        String[] rule = {"check row","check col","check subGrid"};

        for (int k = 0; k < rule.length; k++){

            for(int i = 0; i < 9; i++){

                HashSet<Integer> set = new HashSet<>();

                int currentNum;

                for(int j = 0; j < 9; j++){

                    if(rule[k].compareTo("check row") == 0){
                        //check row
                        currentNum = board[i][j];
                    }else if(rule[k].compareTo("check col") == 0){
                        //check col
                        currentNum = board[j][i];
                    }else{
                        //check sub grid
                        currentNum = board[i/3*3 + j/3][i%3*3 + j%3];
                    }


                    if(currentNum != 0 && !set.add(currentNum)){
                        return false;
                    }

                }
            }
        }

        return true;
    }



    /**
     * Debugging for board
     * @return board's value
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                sb.append("[");
                sb.append(board[r][c]);
                sb.append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}


