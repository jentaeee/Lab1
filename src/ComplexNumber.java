public class ComplexNumber {
    private final double real;
    private final double imaginary;

    public ComplexNumber(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }


    public ComplexNumber add(ComplexNumber other){
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }


    public ComplexNumber subtract(ComplexNumber other){
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }


    public ComplexNumber multiply(ComplexNumber other){
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(newReal, newImaginary);
    }


    public ComplexNumber division(ComplexNumber other){
        double denominator = other.real * other.real + other.imaginary * other.imaginary;

        if (denominator == 0) {
            throw new ArithmeticException("Деление на ноль невозможно!");
        }

        double numeratorReal = (this.real * other.real + this.imaginary * other.imaginary);
        double numeratorImaginary = (this.imaginary * other.real - this.real * other.imaginary);

        return new ComplexNumber(numeratorReal / denominator, numeratorImaginary / denominator);
    }


    public ComplexNumber negate(){
        return new ComplexNumber(-this.real, -this.imaginary);
    }

    @Override
    public String toString(){
        return real + " + " + imaginary + "i";
    }
}