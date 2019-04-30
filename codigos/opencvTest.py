#cv2 é o opencv
import cv2
import os.path as osp

path = "D:\\imgsAula\\frutas.jpg"
imgMatrix = cv2.imread(path)
pastaresult = "D:\\imgsAula\\res\\"

width = len(imgMatrix)
height = len(imgMatrix[0])

#imprime tamanho da imagem
print("w:" + str(width) + " h:" + str(height))

#Pega o nome do arquivo da imagem
imgname = osp.basename(path)

#Redimensiona Imagem para 200x200
newpatch = cv2.resize(imgMatrix, (200, 200))
cv2.imwrite(pastaresult + '\\rs' + imgname, newpatch)

#aplica filtro de deteccao de bordas de Canny, usando limiares 200 e 70
edges = cv2.Canny(imgMatrix,200,70)
cv2.imwrite(pastaresult + '\\borda_' + imgname, edges)

