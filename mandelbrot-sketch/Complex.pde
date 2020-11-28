public class Complex {
    double real;   // the real part
    double img;   // the imaginary part

    public Complex(double real, double img) {
        this.real = real;
        this.img = img;
    }

    public Complex multi(Complex b) {
        double real = (this.real * b.real) - (this.img * b.img);
        double img = (this.real * b.img) + (this.img * b.real);
        return new Complex(real, img);
    }
    
    public double getReal()
    {
      return this.real;
    }
    
    public void setReal(double real)
    {
      this.real = real;
    }
    
    public double getImg()
    {
      return this.img;
    }
    
    public void setImg(double img)
    {
      this.img = img;
    }
    
    public double abs()
    {
      // println(this.real);
      return Math.sqrt(this.real*this.real + this.img*this.img);
    }
}
