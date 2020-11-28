public class Complex
{
	double real;
	double img;

	public Complex(double real, double img)
	{
		this.real = real;
		this.img = img;
	}

	public Complex multi(Complex b)
	{
		double r = (this.real * b.real) - (this.img * b.img);
		double i = (this.real * b.img) + (this.img * b.real);
		return new Complex(r, i);
	}

	public double abs()
	{
		return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.img, 2));
	}

	public double getReal () { return this.real; }

	public void setReal (double real) { this.real = real; }

	public double getImg () { return this.img; }

	public void setImg (double img) { this.img = img; }
}