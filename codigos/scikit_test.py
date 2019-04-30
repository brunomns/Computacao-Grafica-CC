import skimage.io as ski
from skimage.transform import resize
from skimage import feature
import os.path as osp
from skimage.color import rgb2gray

path = "D:\\imgsAula\\frutas.jpg"
imgMatrix = ski.imread(path)
pastaresult = "D:\\imgsAula\\res\\"

width = len(imgMatrix)
height = len(imgMatrix[0])

#imprime tamanho da imagem
print("w:" + str(width) + " h:" + str(height))

#Pega o nome do arquivo da imagem
imgname = osp.basename(path)

#Redimensiona Imagem para 200x200
newpatch = resize(imgMatrix, (200, 200))
ski.imsave(pastaresult + '\\rssk' + imgname, newpatch)

imgnew = rgb2gray(imgMatrix)
ski.imsave(pastaresult + '\\gray_' + imgname, imgnew)
print (imgnew.shape)

#aplica filtro de deteccao de bordas de Canny, usando limiares 200 e 70
edges = feature.canny(imgnew,sigma=3)
ski.imsave(pastaresult + '\\bordask_' + imgname, edges)

