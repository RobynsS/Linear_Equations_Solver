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
        multiplyArray(this.elements, multiplier);
    }

    Row getMultipliedRow(double multiplier){
        //Get copy of elements of Row
        double[] arr = Arrays.copyOf(this.elements, this.elements.length);

        //Return multiplied elements as Row
        return new Row(multiplyArray(arr, multiplier));
    }

    private double[] multiplyArray(double[] arr, double multiplier){
        for(int i = 0; i < arr.length; i++){
            arr[i] *= multiplier;
        }
        return arr;
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
