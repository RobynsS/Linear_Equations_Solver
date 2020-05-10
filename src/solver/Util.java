package solver;

public class Util {

    static void multiplyArray(Complex[] arr, Complex multiplier){
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Complex.multiply(arr[i], multiplier);
        }
    }
}
