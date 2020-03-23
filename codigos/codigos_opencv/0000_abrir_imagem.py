import cv2
from matplotlib import pyplot as plt

path = "D:\\imgsAula\\fire1.jpg"

img = cv2.imread(path)
print(img.shape)

h,w = img.shape[:2]
print("Tamanho da Imagem \n Largura: "+str(w)+ " Altura: " +str(h))
hnew = int(h/4)
wnew = int(w/4)

#resized = cv2.resize(img,(wnew,hnew), interpolation=cv2.INTER_AREA)
resized2 = cv2.resize(img,(wnew,hnew), interpolation=cv2.INTER_AREA)

imgR = resized2[:,:,2]
cv2.imshow("Vermelho",imgR)
imgG = resized2[:,:,1]
cv2.imshow("Verde",imgG)
imgB = resized2[:,:,0]
cv2.imshow("Azul",imgB)
#Calculando Histogramas pelo OpenCV

color = ('b','g','r')
histR = cv2.calcHist([imgR], [0], None, [256], [0, 256])
print(histR)
plt.plot(histR,color = color[2])
plt.xlim([0,255])
histG = cv2.calcHist([imgG], [0], None, [256], [0, 256])
plt.plot(histG,color = color[1])
plt.xlim([0,255])
histB = cv2.calcHist([imgB], [0], None, [256], [0, 256])
plt.plot(histB,color = color[0])
plt.xlim([0,255])
plt.show()

#cv2.imshow("Imagem ",resized)
cv2.imshow("Imagem2 ",resized2)
cv2.waitKey(0)