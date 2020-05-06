package solver;

public class Solver {
    private Matrix matrix;

    Solver(Matrix matrix){
        this.matrix = matrix;
    }

    void solve(){
        //Print input matrix
        System.out.println("Input matrix:");
        matrix.printMatrix();
        System.out.println();

        //Step 1: reduce input matrix to it's row echelon form
        System.out.println("Start solving the equation");
        reduceToEchelon();

        //Print reduced matrix
        System.out.println("Matrix in reduced echelon form:");
        matrix.printMatrix();
        System.out.println();

        //Step 2: loop upwards by decoupling variables
        System.out.println("Continue solving the equation");
        decoupleVariables();

        //Print final matrix
        System.out.println("Solved matrix:");
        matrix.printMatrix();
        System.out.println();
    }

    void reduceToEchelon(){
        //Get matrix size
        int N = matrix.getSize();

        //Loop over different columns which need to be "nulled"
        for(int j = 0; j < N - 1; j++){

            //Make sure the starting index of the corresponding row equals 1
            scaleToCoefficientOne(j);

            //For every column, loop over different rows that are manipulated
            for(int i = j + 1; i < N; i++){
                //"Nullify" coefficient
                nullifyCoefficient(i, j);
            }
        }

        //Transform last row to starting index of 1 if necessary
        scaleToCoefficientOne(N-1);

        System.out.println();
    }

    void decoupleVariables(){
        //Get matrix size
        int N = matrix.getSize();

        //Loop backwards over different columns which need to be "nullified"
        for(int j = N-1; j > 0; j--){

            //For every column, loop over every row element to be "nullified"
            for(int i = 0; i < j; i++){

                nullifyCoefficient(i, j);

            }
        }

        System.out.println();
    }

    double[] getSolution(){
        return matrix.getLastElements();
    }

    void scaleToCoefficientOne(int diagonalIndex){
        if(matrix.getElement(diagonalIndex, diagonalIndex) != 1){
            double multiplier = 1/matrix.getElement(diagonalIndex, diagonalIndex);

            //Print execution command
            System.out.println(multiplier + " * R" + (diagonalIndex + 1) + " -> R"+ (diagonalIndex + 1));

            //Perform execution
            matrix.multiplyRow(diagonalIndex, multiplier);
        }
    }

    void nullifyCoefficient(int rowIndex, int colIndex){
        //Determine operation
        double multiplier = matrix.getElement(rowIndex, colIndex);

        //Determine substracted Row
        Row substractedRow = matrix.getRowCopy(colIndex);
        substractedRow.multiply(multiplier);

        //Perform execution
        matrix.substractRow(rowIndex, substractedRow);

        //Print execution command
        System.out.println(-multiplier + " * R" + (colIndex + 1) + " + R" + (rowIndex + 1) + " -> R" + (rowIndex + 1));
    }
}
