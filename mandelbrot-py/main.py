import cmath
from math import sqrt
from numba import jit
import numpy as np
import os
from PIL import Image, ImageFont, ImageDraw
import time

@jit(nopython=True)
def findIterations(c, max_iter, bounding_box):
    n = 0
    isBounded = True
    z = complex(0, 0)

    while n < max_iter:
        z = z**2 + c
        if bounding_box < abs(z) : break
        n += 1

    return n

if __name__ == "__main__":
    starttime = time.time()
    
    x = 3840
    y = 2160
    max_iter = 80
    zoom = 10
    bounding_box = 2
    image = Image.new(mode = "RGB", size = (x, y))
    pixels = image.load()

    for i in range(x):
        for j in range(y):
            # -2.3, -1
            c = complex( -14 + (i/x) * 3, -3 + (j/y) * 2)
            c = c/zoom
            colour = (255 - (findIterations(c, max_iter, bounding_box) * 255//max_iter))
            pixels[i, j] = ((255 - colour if colour < 150 else colour), colour - 40, colour - 40)
            # colour = 0 if findIterations(c, max_iter, bounding_box) < 80 else 255
            # pixels[i, j] = (colour, colour, colour)
    
    # c = np.array([[complex( -14 + (i/x) * 3, -3 + (j/y) * 2) for j in range(y)] for i in range(x)])
    # findIter = np.frompyfunc(findIterations, 3, 1)
    # pixels = findIter(c, max_iter, bounding_box)

    draw = ImageDraw.Draw(image)
    draw.text((0, 0), f"  Computation time {time.time() - starttime}s\n  Iterations Upper Bound {max_iter}\n  Bounding Box {bounding_box}\n  Scale {zoom}", fill="red")

    print("Done")

    image.show()
    image.save(os.path.join(os.path.join(os.pardir, "mandelbrot"), input("Filename: ")), "PNG", optimize=True)