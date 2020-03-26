import cv2 as cv
import numpy as np


path = "D:\\imgsAula\\cardinal.jpg"

img = cv.imread(path) #BGR
#tamanho da imagem
h,w = img.shape[:2]
cv.imshow(" Img Original ", img)
#criando outra matriz (imagem) para o YCbCr
ycbcrImg = np.zeros((h,w,3),np.float)
#img = img/255
#Conversão direta de RGB para YCbCr
ycbcrImg[:,:,0] = img[:,:,2]*0.2568 + 0.5041*img[:,:,1] + 0.097*img[:,:,0] +16 #Y
ycbcrImg[:,:,1] = -img[:,:,2]*0.1482 - 0.2910*img[:,:,1] + 0.4392*img[:,:,0] +128 #Cb
ycbcrImg[:,:,2] = img[:,:,2]*0.4392 - 0.3678*img[:,:,1] - 0.0714*img[:,:,0] +16 #Cr

Ymax = np.max(ycbcrImg[:,:,0])
Ymin = np.min(ycbcrImg[:,:,0])

#normalizando Y entre 0 e 1
ycbcrImg[:,:,0] = (ycbcrImg[:,:,0] - Ymin)/(Ymax - Ymin)

Cbmax = np.max(ycbcrImg[:,:,1])
Cbmin = np.min(ycbcrImg[:,:,1])

#normalizando Cb entre 0 e 1
ycbcrImg[:,:,1] = (ycbcrImg[:,:,1] - Cbmin)/(Cbmax - Cbmin)

Crmax = np.max(ycbcrImg[:,:,2])
Crmin = np.min(ycbcrImg[:,:,2])

ycbcrImg[:,:,2] = (ycbcrImg[:,:,2] - Crmin)/(Crmax - Crmin)

yCbCr = np.zeros((h,w,3),np.uint8)
yCbCr = (ycbcrImg * 255).astype('uint8')

cv.imshow("Imagem YCbCbr na seq B G R",yCbCr)

cv.imshow("Vermelho - Cr", yCbCr[:,:,2] )
cv.imshow("Azul - Cb", yCbCr[:,:,1] )
cv.imshow("Luminancia - Y", yCbCr[:,:,0] )

novaImg = np.zeros((h,w,3),np.uint8)
novaImg[:,:,0] = yCbCr[:,:,1] #B
novaImg[:,:,1] = yCbCr[:,:,0] #G
novaImg[:,:,2] = yCbCr[:,:,2] #R
cv.imshow("Imagem YCbCr - G B R",novaImg)
cv.waitKey(0)
