package solver;

public class SwapColCommand implements Command{
    private Matrix matrix;
    private int colIndex1;
    private int colIndex2;

    SwapColCommand(Matrix matrix, int colIndex1, int colIndex2){
        this.matrix = matrix;
        this.colIndex1 = colIndex1;
        this.colIndex2 = colIndex2;
    }

    @Override
    public void execute() {
        //Perform executions
        matrix.swapCols(colIndex1, colIndex2);

        //Print execution command
        System.out.println("C" + (colIndex1 + 1) + " <-> C" + (colIndex2 + 1));
    }

    public void undo(){
        //Perform executions
        matrix.swapCols(colIndex2, colIndex1);
    }
}
