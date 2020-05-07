package solver;

public class Complex {
    private Double real;
    private Double imaginary;

    Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    static Complex add(Complex c1, Complex c2){
        Double real = c1.real + c2.real;
        Double imaginary = c1.imaginary + c2.imaginary;

        return new Complex(real, imaginary);
    }

    static Complex substract(Complex c1, Complex c2){
        Double real = c1.real - c2.real;
        Double imaginary = c1.imaginary - c2.imaginary;

        return new Complex(real, imaginary);
    }

    static Complex multiply(Complex c1, Complex c2){
        Double real = c1.real * c2.real - c1.imaginary * c2.imaginary;
        Double imaginary = c1.imaginary * c2.real + c2.imaginary * c1.real;

        return new Complex(real, imaginary);
    }

    static Complex divide(Complex c1, Complex c2){
        Double denominator = c2.real*c2.real + c2.imaginary*c2.imaginary;
        Double real = (c1.real * c2.real + c1.imaginary * c2.imaginary)/denominator;
        Double imaginary = (c2.real * c1. imaginary - c1.real * c2.imaginary)/denominator;

        return new Complex(real, imaginary);
    }

    @Override
    public String toString() {
        String realString;
        String complexString;

        if(imaginary == 0){
            complexString = "";
        } else if (imaginary > 0){
            complexString = imaginary == 1 ? "+i" : "+" + imaginary.toString() + "i";
        } else {
            complexString = imaginary == -1 ? "-i" : imaginary.toString() + "i";
        }

        realString = real == 0 ? "" : real.toString();

        return realString + complexString;
    }
}
