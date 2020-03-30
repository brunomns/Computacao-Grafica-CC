import cv2 as cv
import numpy as np
from matplotlib import pyplot as plt

def converteYcbCr(img):
    h,w = img.shape[:2]
    ycbcrImg = np.zeros((h, w, 3), np.float)
    # img = img/255
    # Conversão direta de RGB para YCbCr
    ycbcrImg[:, :, 0] = img[:, :, 2] * 0.2568 + 0.5041 * img[:, :, 1] + 0.097 * img[:, :, 0] + 16  # Y
    ycbcrImg[:, :, 1] = -img[:, :, 2] * 0.1482 - 0.2910 * img[:, :, 1] + 0.4392 * img[:, :, 0] + 128  # Cb
    ycbcrImg[:, :, 2] = img[:, :, 2] * 0.4392 - 0.3678 * img[:, :, 1] - 0.0714 * img[:, :, 0] + 16  # Cr

    Ymax = np.max(ycbcrImg[:, :, 0])
    Ymin = np.min(ycbcrImg[:, :, 0])

    # normalizando Y entre 0 e 1
    ycbcrImg[:, :, 0] = (ycbcrImg[:, :, 0] - Ymin) / (Ymax - Ymin)
    Cbmax = np.max(ycbcrImg[:, :, 1])
    Cbmin = np.min(ycbcrImg[:, :, 1])
    # normalizando Cb entre 0 e 1
    ycbcrImg[:, :, 1] = (ycbcrImg[:, :, 1] - Cbmin) / (Cbmax - Cbmin)
    Crmax = np.max(ycbcrImg[:, :, 2])
    Crmin = np.min(ycbcrImg[:, :, 2])
    ycbcrImg[:, :, 2] = (ycbcrImg[:, :, 2] - Crmin) / (Crmax - Crmin)
    yCbCr = np.zeros((h, w, 3), np.uint8)
    yCbCr = (ycbcrImg * 255).astype('uint8')
    return yCbCr

path = "D:\\imgsAula\\bird_colored.jpg"

img = cv.imread(path) #BGR
#tamanho da imagem
h,w = img.shape[:2]
cv.imshow(" Img Original ", img)
limiar = 110
limiar2 = 190
#img = converteYcbCr(img)
binaria = np.zeros((h,w),np.uint8)
#Limiar com o opencv....
th, res = cv.threshold(img[...,1],0,255,cv.THRESH_OTSU)
print(th)
cv.imshow("Binarizacao opencv", res)
#limiar percorrendo a imagem....
for i in range(h):
    for j in range(w):
        if img[i, j , 2] >= limiar:
            binaria[i,j] = 255
        if img[i,j,1] >= limiar2:
            binaria[i,j] = 255

color = ('b','g','r')
histR = cv.calcHist([img[...,2]], [0], None, [256], [0, 256])
print(histR)
plt.plot(histR,color = color[2])
plt.xlim([0,255])
histG = cv.calcHist([img[...,1]], [0], None, [256], [0, 256])
plt.plot(histG,color = color[1])
plt.xlim([0,255])
histB = cv.calcHist([img[...,0]], [0], None, [256], [0, 256])
plt.plot(histB,color = color[0])
plt.xlim([0,255])
plt.show()

#cv.imshow("Binaria ", binaria)
cv.waitKey(0)

