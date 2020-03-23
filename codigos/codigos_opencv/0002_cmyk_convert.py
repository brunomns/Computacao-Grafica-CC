import cv2
import numpy as np

path = "D:\\imgsAula\\luke.jpg"

img = cv2.imread(path)
h,w = img.shape[:2]
hn = int(h/4)
wn = int (w/4)
img = cv2.resize(img,(wn,hn))

cv2.imshow("Imagem", img)
 # numpy --->>>    (h,w,c)    [ <inicial> : <intervalo> : <passo> ]

imgCopy = img.copy()
#imagem normalizada....
imgCopy = imgCopy/255.

imgCMYK = np.zeros((hn,wn,4),np.float)
# 0 C
# 1 M
# 2 Y
# 3 K
'''
(K) Black   = minimum(1-Red,1-Green,1-Blue)
(C) Cyan    = (1-Red-Black)/(1-Black)
(M) Magenta = (1-Green-Black)/(1-Black)
(Y) Yellow  = (1-Blue-Black)/(1-Black)
'''
for i in range(hn):
    for j in range(wn):
        black = np.min([imgCopy[i,j,0],imgCopy[i,j,1],imgCopy[i,j,2]])
        if (1-black) == 0:
            black = 0.99998 #para evitar divisao por zero.
        blue = imgCopy[i,j,0]
        green = imgCopy[i, j, 1]
        red = imgCopy[i, j, 2]
        imgCMYK[i, j, 3] = black
        imgCMYK[i,j,0] = (1-red-black)/(1-black)
        imgCMYK[i, j, 1] = (1 - green - black) / (1 - black)
        imgCMYK[i, j, 2] = (1 - blue - black) / (1 - black)

imgCMYK = (imgCMYK * 255).astype('uint8')
cv2.imshow("Preto ",imgCMYK[:,:,3])
cv2.imshow("Ciano ",imgCMYK[:,:,0])
cv2.imshow("Magenta ",imgCMYK[:,:,1])
cv2.imshow("Amarelo ",imgCMYK[:,:,2])
cv2.waitKey(0)