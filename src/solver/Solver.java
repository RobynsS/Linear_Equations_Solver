package solver;

public class Solver {
    private Matrix matrix;
    private CommandController controller;

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

        //Print reduced matrix
        System.out.println("Matrix in reduced row echelon form:");
        matrix.printMatrix();
        System.out.println();

        //Step 2: decouple variables of matrix
        System.out.println("Continue solving the equation");
        decoupleVariables();

        //Print final matrix
        System.out.println("Solved matrix:");
        matrix.printMatrix();
        System.out.println();
    }

    void transformToReducedRowEchelon(){
        //Get matrix size
        int N = matrix.getSize();

        //Loop over different columns which need to be "nullified"
        for(int j = 0; j < N - 1; j++){

            //Scale the row coefficient of row that corresponds with the processed column to 1
            controller.setCommand(new ScaleRowCoefficientCommand(matrix, j));
            controller.executeCommand();

            //For every column, loop over different rows that are manipulated
            for(int i = j + 1; i < N; i++){

                //"Nullify" coefficient
                controller.setCommand(new NullifyRowCoefficientCommand(matrix, i, j));
                controller.executeCommand();
            }
        }

        //Scale last row
        controller.setCommand(new ScaleRowCoefficientCommand(matrix, N-1));
        controller.executeCommand();

        System.out.println();
    }

    void decoupleVariables(){
        //Get matrix size
        int N = matrix.getSize();

        //Loop backwards over different columns which need to be "nullified"
        for(int j = N-1; j > 0; j--){

            //For every column, loop over every row element to be "nullified"
            for(int i = 0; i < j; i++){

                //"Nullify" coefficient
                controller.setCommand(new NullifyRowCoefficientCommand(matrix, i, j));
                controller.executeCommand();

            }
        }

        System.out.println();
    }

    double[] getSolution(){
        return matrix.getLastElements();
    }
}
