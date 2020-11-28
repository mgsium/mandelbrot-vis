int x1, y1;
int x2, y2;
int c, n;
int iter;
Complex com;
Mandelbrot m;

void setup()
{
  x1 = 0;
  y1 = 0;
  x2 = 640;
  y2 = 360;
  m = new Mandelbrot(x1, y1, x2, y2);
  
  size(640, 360);
  background(255);
  
  int res = -2;
  int reend= 1;
  int ims = -1;
  int imend = 1;
  
  n = 0;
  for(int i = 0; i < x2; i++) {
     for (int j = 0; j < y2; j++) {
       com = new Complex(
                 res + ((double) i/ (double)x2) * (reend-res), 
                 ims + ((double) j/ (double) y2) * (imend-ims)
              );
       
       iter = m.findIterations(com);
       
       c = (int) (255 - (iter * (255/80)));
       
       // print(iter + " " + c);
       
       set(i, j, color(c));
       n++;
     }
   }  
   
   println("Done");
   // print((new Complex(1, 1)).multi(new Complex(1, 1)).getImg());
  
}

void draw()
{
}
