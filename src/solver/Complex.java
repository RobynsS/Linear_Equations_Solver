package solver;

import java.util.Arrays;
import java.util.Iterator;

public class Complex {
    private Double real;
    private Double imaginary;

    Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    static Complex add(Complex c1, Complex c2){
        double real = c1.real + c2.real;
        double imaginary = c1.imaginary + c2.imaginary;

        return new Complex(real, imaginary);
    }

    static Complex substract(Complex c1, Complex c2){
        double real = c1.real - c2.real;
        double imaginary = c1.imaginary - c2.imaginary;

        return new Complex(real, imaginary);
    }

    static Complex multiply(Complex c1, Complex c2){
        double real = c1.real * c2.real - c1.imaginary * c2.imaginary;
        double imaginary = c1.imaginary * c2.real + c2.imaginary * c1.real;

        return new Complex(real, imaginary);
    }

    static Complex divide(Complex c1, Complex c2){
        double denominator = c2.real*c2.real + c2.imaginary*c2.imaginary;
        double real = (c1.real * c2.real + c1.imaginary * c2.imaginary)/denominator;
        double imaginary = (c2.real * c1. imaginary - c1.real * c2.imaginary)/denominator;

        return new Complex(real, imaginary);
    }

    static Complex parseComplex(String s){
        if(s.indexOf('i') == -1){ //No imaginary part
            return new Complex(Double.parseDouble(s), 0);
        } else {
            if(s.equals("i")){
                return new Complex(0, 1);
            }
            s = s.split("i")[0]; //Remove the "i" from the string

            String[] sSplit = s.split("(?<=\\d)(?=[+\\-])"); //Split between real (if exists) and imaginary part
            if(sSplit.length == 1){ //No real part
                    return  new Complex(0, parseImaginary(sSplit[0]));
            } else { //Real and imaginary part
                return new Complex(Double.parseDouble(sSplit[0]), parseImaginary(sSplit[1]));
            }
        }
    }

    static double parseImaginary(String s){
        if(s.equals("-")){
            return -1;
        } else if (s.equals("+")){
            return 1;
        } else {
            return Double.parseDouble(s);
        }
    }

    @Override
    public String toString() {
        String realString;
        String complexString;

        if(imaginary == 0){
            complexString = "";
        } else if (imaginary > 0 & real != 0){
            complexString = imaginary == 1 ? "+i" : "+" + imaginary.toString() + "i";
        } else if (imaginary > 0 & real == 0){
            complexString = imaginary == 1 ? "i" : imaginary.toString() + "i";
        } else {
            complexString = imaginary == -1 ? "-i" : imaginary.toString() + "i";
        }

        realString = real == 0 ? imaginary == 0 ? "0" : "" : real.toString();

        return realString + complexString;
    }
}
