import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.Scanner;

class Mnd
{
    public static void main(String[] args)
    {
		/*
		int x = 3840;
		int y = 2160;

		long sTime = System.nanoTime();
		boolean[] pixels = new boolean[x * y];

		try {
			FileOutputStream f = new FileOutputStream(".\\mnd.txt");
			DataOutputStream ds = new DataOutputStream(f);
			
			for(int i = 0; i < x; i++) {
				for(int j = 0; j < y; j++) {
					Complex c = new Complex(-2 + ((double) i/ (double) x) * 3, -1 + ((double) j/ (double) y) * 2);
					// String s = String.format("%d, %d, %d\n", i, j, mandelbrot(c, Math.sqrt(2)));
					ds.writeBoolean(mandelbrot(c, Math.sqrt(2)) < 80 ? true : false);
				}
			}

			ds.flush();
			ds.close();
			f.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(String.format("Execution Time : %d", (System.nanoTime() - sTime)/1000000000));
		*/

		ForkJoinPool fjp = new ForkJoinPool();

		int x = 30720;
		int y = 17280;

		long sTime = System.nanoTime();

		Mandelbrot m = new Mandelbrot(0, x, 0, y, x, y);
		// for (boolean val : m.invoke()) System.out.print(val);
		/*
		try { 
			FileOutputStream f = new FileOutputStream(".\\mnd.txt");
			DataOutputStream ds = new DataOutputStream(f);
			for (boolean val : m.invoke()) ds.writeBoolean(val); 
		}
		catch (IOException e) {}
		// m.invoke();
		*/
		System.out.println(String.format("Execution Time : %fs", ((double) (System.nanoTime() - sTime))/1000000000));
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
