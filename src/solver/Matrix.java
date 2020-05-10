package solver;

public class Matrix {
    private Row[] rows;

    Matrix(Complex[][] elements){
        rows = new Row[elements.length];
        int rowIndex = 0;

        for (Complex[] row: elements){
            rows[rowIndex] = new Row(row);
            rowIndex++;
        }
    }

    Complex getElement(int index1, int index2){
        return rows[index1].getElement(index2);
    }

    void printMatrix(){
        for(Row row: rows){
            row.printRow();
        }
    }

    int getAmountRows(){
        return rows.length;
    }

    int getAmountCols(){
        return rows[0].getSize();
    }

    void multiplyRow(int index, Complex multiplier){
        rows[index].multiply(multiplier);
    }

    void substractRow(int index, Row substractedRow){
        rows[index].substract(substractedRow);
    }

    Row getRowCopy(int index) {
        return rows[index].getRowCopy();
    }

    void swapRows(int index1, int index2){
        Row tempRow = rows[index1].getRowCopy();
        rows[index1] = rows[index2];
        rows[index2] = tempRow;
    }

    void swapCols(int index1, int index2){
        for(Row row: rows){
            row.swapElement(index1, index2);
        }
    }

    Complex[] getLastElements(){
        Complex[] arr = new Complex[rows.length];
        int index = 0;

        for(Row row: rows){
            arr[index] = row.getLastElement();
            index++;
        }

        return arr;
    }

}
