import cv2 as cv

flags = [i for i in dir(cv) if i.startswith('COLOR_RGB')]
print( flags )

path = "D:\\imgsAula\\cardinal.jpg"

img = cv.imread(path)
h,w = img.shape[:2]
hnew = int(h/1)
wnew = int(w/1)

resized2 = cv.resize(img,(wnew,hnew), interpolation=cv.INTER_AREA)

imgHLS = cv.cvtColor(resized2,cv.COLOR_BGR2HLS)
imgYUV = cv.cvtColor(resized2,cv.COLOR_BGR2YUV)

cv.imshow(" RGB ", resized2)
cv.imshow(" HLS ", imgHLS)
cv.imshow(" HLS H ", imgHLS[:,:,0])
cv.imshow(" HLS L ", imgHLS[:,:,1])
cv.imshow(" HLS S ", imgHLS[:,:,2])


cv.imshow(" YUV ", imgYUV)
cv.imshow(" YUV Y ", imgYUV[:,:,0])
cv.imshow(" YUV U ", imgYUV[:,:,1])
cv.imshow(" YUV V ", imgYUV[:,:,2])

cv.waitKey(0)


