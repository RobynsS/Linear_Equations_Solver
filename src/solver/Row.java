package solver;

import java.util.Arrays;

public class Row {
    private Complex[] elements;

    Row(Complex[] elements){
        this.elements = elements;
    }

    Complex getElement(int index){
        return this.elements[index];
    }

    Complex getLastElement(){
        return getElement(this.elements.length - 1);
    }

    int getSize(){
        return elements.length;
    }

    void multiply(Complex multiplier){
        Util.multiplyArray(this.elements, multiplier);
    }

    Row getRowCopy(){
        //Return copied elements as new Row
        return new Row(Arrays.copyOf(this.elements, this.elements.length));
    }

    void substract(Row subtractedRow){
        if(elements.length == subtractedRow.elements.length){
            for(int i = 0; i < elements.length; i++){
                elements[i] = Complex.substract(elements[i], subtractedRow.elements[i]);
            }
        }
    }

    void swapElement(int index1, int index2){
        Complex temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    void printRow(){
        System.out.println(Arrays.toString(this.elements));
    }


}
