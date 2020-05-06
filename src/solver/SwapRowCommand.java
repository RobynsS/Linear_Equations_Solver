package solver;

public class SwapRowCommand implements Command {
    private Matrix matrix;
    private int rowIndex1;
    private int rowIndex2;

    SwapRowCommand(Matrix matrix, int rowIndex1, int rowIndex2){
        this.matrix = matrix;
        this.rowIndex1 = rowIndex1;
        this.rowIndex2 = rowIndex2;
    }

    @Override
    public void execute() {
        //Perform executions
        matrix.swapRows(rowIndex1, rowIndex2);

        //Print execution command
        System.out.println("R" + (rowIndex1 + 1) + " <-> R" + (rowIndex2 + 1));
    }
}
