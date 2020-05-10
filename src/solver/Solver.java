package solver;

import java.util.Arrays;

public class Solver {
    private Matrix matrix;
    private CommandController controller;
    private SolutionType solutionType;

    Solver(Matrix matrix){
        this.matrix = matrix;
        this.controller = new CommandController();
    }

    void solve(){
        //Print input matrix
        System.out.println("Input matrix:");
        matrix.printMatrix();
        System.out.println();

        //Step 1: transform matrix to it's reduced row echelon form
        System.out.println("Start solving the equation");
        transformToReducedRowEchelon();

        //Check type of solutions of reduced row echelon form
        checkSolutionsType();

        //Print reduced matrix
        System.out.println("Matrix in reduced row echelon form:");
        matrix.printMatrix();
        System.out.println();

        //Step 2: decouple variables of matrix if there is only one solution
        if(solutionType == SolutionType.ONE) {
            System.out.println("Continue solving the equation");
            decoupleVariables();
            controller.undoSwaps();
        }

        //Print final matrix
        System.out.println("Solved matrix:");
        matrix.printMatrix();
        System.out.println();
    }

    void transformToReducedRowEchelon(){
        //Get matrix size
        int N = matrix.getAmountRows();

        //Loop over different columns which need to be "nullified"
        for(int j = 0; j < N; j++){

            //Check if the row coefficient of the row that corresponds with the processed column can be non-zero
            boolean nonZero = findNonZeroElement(j);

            //Scale the row coefficient of row that corresponds with the processed column to 1 if there are still non-zero elements
            if(nonZero) {
                controller.setCommand(new ScaleRowCoefficientCommand(matrix, j));
                controller.executeCommand();

                //For every column, loop over different rows that are manipulated
                for (int i = j + 1; i < N; i++) {

                    //"Nullify" coefficient
                    controller.setCommand(new NullifyRowCoefficientCommand(matrix, i, j));
                    controller.executeCommand();
                }
            }
        }

        System.out.println();
    }

    void decoupleVariables(){
        //Get matrix size
        int M = matrix.getAmountCols();

        //Loop backwards over different columns which need to be "nullified"
        for(int j = M-2; j > 0; j--){

            //For every column, loop over every row element to be "nullified"
            for(int i = 0; i < j; i++){

                //"Nullify" coefficient
                controller.setCommand(new NullifyRowCoefficientCommand(matrix, i, j));
                controller.executeCommand();
            }
        }

        System.out.println();
    }

    boolean findNonZeroElement(int index){

        //Initialize boolean
        boolean nonZero = true;

        //Only perform method if the element is originally zero
        if(matrix.getElement(index, index).equalsReal(0)){
            nonZero = false;
            int N = matrix.getAmountRows();
            int M = matrix.getAmountCols();

            //Search through rows to find non-zero element
            for(int i = index+1; i < N ; i++){
                if(!matrix.getElement(i, index).equalsReal(0)){
                    controller.setCommand(new SwapRowCommand(matrix, i, index));
                    controller.executeCommand();
                    nonZero = true;
                    break;
                }
            }

            //Search through columns to find non-zero element when none is found
            if(!nonZero){
                for(int i = index+1; i < M-1; i++){
                    if(!matrix.getElement(index, i).equalsReal(0)){
                        controller.setCommand(new SwapColCommand(matrix, i, index));
                        controller.executeCommand();
                        nonZero = true;
                        break;
                    }
                }
            }

            //Search through remaining rows and columns to find non-zero element when none is found
            if(!nonZero){
                for(int i = index + 1; i < N; i++){
                    for(int j = index + 1; j < M-1; j++){
                        if(!matrix.getElement(i, j).equalsReal(0)){
                            controller.setCommand(new SwapRowCommand(matrix, i, index));
                            controller.executeCommand();
                            controller.setCommand(new SwapColCommand(matrix, j, index));
                            controller.executeCommand();
                            nonZero = true;
                        }
                    }
                }
            }
        }
        return nonZero;
    }

    void checkSolutionsType(){

        //Initialize number of zero-rows
        int zeroRows = 0;

        //Get matrix size
        int N = matrix.getAmountRows();
        int M = matrix.getAmountCols();

        //Loop over rows
        for(int i = 0; i < N; i++){
            //Loop over columns: check if all coefficients are zero
            boolean nonZeroCoef = false;
            for(int j = 0; j < M-1; j++){
                if(!matrix.getElement(i, j).equalsReal(0)){
                    nonZeroCoef = true;
                    break;
                }
            }

            //If all coefficients are zero, check if the constant is zero
            if(!nonZeroCoef){
                if(!matrix.getElement(i, M-1).equalsReal(0)){
                    solutionType = SolutionType.NONE;
                    break;
                } else {
                    zeroRows++;
                }
            }
        }
        if(solutionType != SolutionType.NONE) {
            if (N-zeroRows != M - 1) {
                solutionType = SolutionType.MANY;
            } else {
                solutionType = SolutionType.ONE;
            }
        }
    }

    Complex[] getSolution(){
        if(solutionType == SolutionType.ONE) {
            return Arrays.copyOfRange(matrix.getLastElements(), 0, matrix.getAmountCols()-1);
        } else {
            return null;
        }
    }

    SolutionType getSolutionType(){
        return solutionType;
    }
}

enum SolutionType{
    NONE("No solutions"),
    ONE(""),
    MANY("Infinitely many solutions");

    private String text;

    SolutionType(String text){
        this.text = text;
    }

    String getText(){
        return text;
    }
}
