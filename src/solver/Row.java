package solver;

import java.util.Arrays;

public class Row {
    private double[] elements;

    Row(double[] elements){
        this.elements = elements;
    }

    double getElement(int index){
        return this.elements[index];
    }

    double getLastElement(){
        return getElement(this.elements.length - 1);
    }

    void multiply(double multiplier){
        Util.multiplyArray(this.elements, multiplier);
    }

    Row getRowCopy(){
        //Return copied elements as new Row
        return new Row(Arrays.copyOf(this.elements, this.elements.length));
    }

    void substract(Row subtractedRow){
        if(elements.length == subtractedRow.elements.length){
            for(int i = 0; i < elements.length; i++){
                elements[i] -= subtractedRow.elements[i];
            }
        }
    }

    void printRow(){
        System.out.println(Arrays.toString(this.elements));
    }


}
