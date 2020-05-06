package solver;

public class Main {

    public static void main(String[] args) {

        // Read input file and store elements in Matrix object
        double[][] inputs = Reader.readElementsFromInput(args[1]);
        Matrix matrix = new Matrix(inputs);

        // Solve the linear equations
        Solver solver = new Solver(matrix);
        solver.solve();
        SolutionType solutionType = solver.getSolutionType();
        double[] solution = solver.getSolution();

        //Write solution to File
        Writer.readSolutionToOutput(args[3], solution, solutionType);
    }
}
