class Mandelbrot
{
  int x1, y1;
  int x2, y2;
  Complex com;
  
   public Mandelbrot(int x1, int y1, int x2, int y2) {
     this.x1 = x1;
     this.y1 = y1;
     this.x2 = x2;
     this.y2 = y2;
   }
   
   /*
   public int[] findAllIterations()
   {
     int[] iterations = new int[(this.x2-this.x1)*(this.y2-this.y1)];
     
     int n = 0;
     Complex c;
     for(int i = this.x1; i < this.x2; i+=5) {
       for (int j = this.y1; j < this.y2; j+=5) {
         c = new Complex(i, j);
         iterations[n] = this.findIterations(c, new Complex(0, 0));
         n++;
       }
     }
     
     return iterations;
   }
   */
   
   public int findIterations(Complex c)
   {
     int n = 0;
     boolean isBounded = true;
     Complex z = new Complex(0, 0);
     
     while (isBounded && (n < 80))
     {
       z = z.multi(z);
       z.setReal(z.getReal() + c.getReal());
       z.setImg(z.getImg() + c.getImg());
       if (z.abs() > 2) isBounded = false;
       n++;
     }

     return n;
   }
}
