package solver;

public class Util {

    static double[] multiplyArray(double[] arr, double multiplier){
        for(int i = 0; i < arr.length; i++){
            arr[i] *= multiplier;
        }
        return arr;
    }
}
