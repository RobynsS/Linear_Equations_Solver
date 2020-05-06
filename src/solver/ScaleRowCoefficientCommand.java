package solver;

public class ScaleRowCoefficientCommand implements Command {

    private Matrix matrix;
    private int rowIndex;

    ScaleRowCoefficientCommand(Matrix matrix, int rowIndex){
        this.matrix = matrix;
        this.rowIndex = rowIndex;
    }

    @Override
    public void execute() {

        //Only perform method if row is not already scaled
        if(matrix.getElement(rowIndex, rowIndex) != 1){
            //Determine multiplier
            double multiplier = 1/matrix.getElement(rowIndex, rowIndex);

            //Perform execution
            matrix.multiplyRow(rowIndex, multiplier);

            //Print execution command
            System.out.println(multiplier + " * R" + (rowIndex + 1) + " -> R"+ (rowIndex + 1));
        }
    }
}
