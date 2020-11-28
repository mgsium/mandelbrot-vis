import java.util.concurrent.RecursiveTask; 

public class Mandelbrot extends RecursiveTask<boolean[]>
{
	final int seqThreshold = 500;
	boolean pixels[];
	int xL, xH;
	int yL, yH;
	int x, y;

	public Mandelbrot(int xL, int xH, int yL, int yH, int x, int y)
	{
		this.xL = xL;
		this.xH = xH;
		this.yL = yL;
		this.yH = yH;
		this.x = x;
		this.y = y;
		this.pixels = new boolean[(yH - yL) * (xH - xL)];
	}

	protected boolean[] compute()
	{
		if ((this.yH - this.yL) < this.seqThreshold) {
			if ((this.xH - this.xL) < this.seqThreshold){
				boolean[] pixels = new boolean[(this.yH - this.yL) * (this.xH - this.xL)];

				for(int i = this.xL; i < this.xH; i++) {
					for(int j = this.yL; j < this.yH; j++) {
						Complex c = new Complex(-2 + ((double) i/ (double) this.x) * 3, -1 + ((double) j/ (double) this.y) * 2);
						pixels[i + j] = (mandelbrot(c, Math.sqrt(2)) < 80 ? true : false);
					}
				}
			} else {
				int xMid = (this.xL + this.xH) / 2;

				Mandelbrot mA = new Mandelbrot(this.xL, xMid, this.yL, this.yH, this.x, this.y);
				Mandelbrot mB = new Mandelbrot(xMid, this.xH, this.yL, this.yH, this.x, this.y);

				mA.fork();
				mB.fork();

				boolean[] mApixels = mA.join();
                		boolean[] mBpixels = mB.join();
                		System.arraycopy(this.pixels, 0, mApixels, 0, mApixels.length);
                		System.arraycopy(this.pixels, mApixels.length, mBpixels, 0, mBpixels.length);
			}
		} else {
			int yMid = (this.yL + this.yH) / 2;

			Mandelbrot mA = new Mandelbrot(this.xL, this.xH, this.yL, yMid, this.x, this.y);
			Mandelbrot mB = new Mandelbrot(this.xL, this.xH, yMid, this.yH, this.x, this.y);

			mA.fork();
			mB.fork();

            		boolean[] mApixels = mA.join();
            		boolean[] mBpixels = mB.join();
			System.arraycopy(this.pixels, 0, mApixels, 0, mApixels.length);
			System.arraycopy(this.pixels, mApixels.length, mBpixels, 0, mBpixels.length);
		}

		return pixels;
	}

	private static int mandelbrot(Complex c, double bound)
	{
		int n = 0;
		boolean isBounded = true;
		Complex z = new Complex(0, 0);

		while (isBounded && (n < 80))
		{
			z = z.multi(z);
			z.setReal(z.getReal() + c.getReal());
			z.setImg(z.getImg() + c.getImg());
			if (z.abs() > bound) { isBounded = false; }
			n++;
		}

		return n;
	}
}
