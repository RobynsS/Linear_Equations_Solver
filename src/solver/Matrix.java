package solver;

public class Matrix {
    private Row[] rows;

    Matrix(double[][] elements){
        rows = new Row[elements.length];
        int rowIndex = 0;

        for (double[] row: elements){
            rows[rowIndex] = new Row(row);
            rowIndex++;
        }
    }

    double getElement(int index1, int index2){
        return rows[index1].getElement(index2);
    }

    void printMatrix(){
        for(Row row: rows){
            row.printRow();
        }
    }

    int getSize(){
        return rows.length;
    }

    void multiplyRow(int index, double multiplier){
        rows[index].multiply(multiplier);
    }

    void substractRow(int index, Row substractedRow){
        rows[index].substract(substractedRow);
    }

    Row getMultipliedRow(int index, double multiplier) {
        return rows[index].getMultipliedRow(multiplier);
    }

    double[] getLastElements(){
        double[] arr = new double[rows.length];
        int index = 0;

        for(Row row: rows){
            arr[index] = row.getLastElement();
            index++;
        }

        return arr;
    }
}
