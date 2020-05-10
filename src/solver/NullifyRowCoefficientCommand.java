package solver;

public class NullifyRowCoefficientCommand implements Command {

    private Matrix matrix;
    private int rowIndex;
    private int colIndex;

    NullifyRowCoefficientCommand(Matrix matrix, int rowIndex, int colIndex){
        this.matrix = matrix;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    @Override
    public void execute() {
        //Determine operation
        Complex multiplier = matrix.getElement(rowIndex, colIndex);

        //Determine substracted Row
        Row substractedRow = matrix.getRowCopy(colIndex);
        substractedRow.multiply(multiplier);

        //Perform execution
        matrix.substractRow(rowIndex, substractedRow);

        //Print execution command
        System.out.println(multiplier.getNegative() + " * R" + (colIndex + 1) + " + R" + (rowIndex + 1) + " -> R" + (rowIndex + 1));
    }
}
