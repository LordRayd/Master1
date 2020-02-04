#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 14:03:51 2019

@author: e1604902
"""

import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import matplotlib as mat
import numpy as np

img = mpimg.imread('Images/chimpanze.jpg')
imgplot = plt.imshow(img,cmap='gray')

zoom = img[50:100,50:100]
plt.imshow(zoom)
plt.imsave('Images/chimpanzezoom.jpg', zoom)


img_carre = np.zeros(np.shape(img))
#np.copy(img)
for i in range(50,200):
    for j in range(50,200):
        img_carre[i,j] = img[i,j]
plt.imshow(img_carre,cmap='gray')

def echantillonage(image,reso):
    shape = np.shape(image)
    divby = int(shape[0]/reso)
    result = image[0:shape[0]:divby,0:shape[1]:divby]
    return result
    
plt.figure()
plt.subplot(221)
chimpa128 = echantillonage(img,128)
plt.imshow(chimpa128,cmap='gray')

plt.subplot(222)
chimpa64 = echantillonage(img,64)
plt.imshow(chimpa64,cmap='gray')
plt.subplot(223)
chimpa32 = echantillonage(img,32)
plt.imshow(chimpa32,cmap='gray')
plt.subplot(224)
chimpa16 = echantillonage(img,16)
plt.imshow(chimpa16,cmap='gray')

def histogramme(image,norme):
    ret = np.zeros(256)
    shape = np.shape(image)
    for i in range(0,shape[0]):
        for j in range(0,shape[1]):
            ret[image[i,j]] += 1
    for i in range(0,255):
        ret[i] = ret[i]/norme
    return ret;

imgHisto = histogramme(img,1)
plt.figure()
plt.hist(imgHisto, normed=1)
plt.xticks( np.arange(1) )

imgHisto = histogramme(img,65)
plt.figure()
plt.hist(imgHisto, normed=1)
plt.xticks( np.arange(1) )

imgHisto = histogramme(img,img.size)
plt.figure()
plt.plot(imgHisto)
"""
plt.hist(imgHisto, normed=1)
plt.xticks( np.arange(1) )"""

def convolution(image,filtre):
    shape = np.shape(image)
    ret = np.copy(image)
    shapeF = np.shape(filtre)
    ylen = int((shapeF[1]-1)/2)
    xlen = int((shapeF[0]-1)/2)
    for x in range (0,shape[0]):
        for y in range(0,shape[1]):
            sum = 0
            parcoursX = 0
            for i in range(x-xlen,x+xlen):
                if(i>=0 and i<shape[0] and parcoursX <= shapeF[0]):
                    parcoursY = 0
                    for j in range(y-ylen,y+ylen):
                        if(j>=0 and j<shape[1] and parcoursY <= shapeF[1]):
                            sum += image[i,j] * filtre[parcoursX,parcoursY]
                        parcoursY += 1
                parcoursX += 1
            ret[i,j] = sum
    return ret

filtre1 = np.array([-1,-1,-1,-1,8,-1,-1,-1,-1])
filtre = filtre1.reshape((3, 3))
imgConvo = convolution(img,filtre)
plt.figure()
plt.imshow(imgConvo,cmap='gray')

filtre1 = np.array([0,-1,0,-1,5,-1,0,-1,0])
filtre = filtre1.reshape((3, 3))
imgConvo = convolution(img,filtre)
plt.figure()
plt.imshow(imgConvo,cmap='gray')

#2.1 filtre moyenneur qui resort un flou
filtre1 = np.array([1/9,1/9,1/9,1/9,1/9,1/9,1/9,1/9,1/9])
filtre = filtre1.reshape((3, 3))
imgConvo = convolution(img,filtre)
plt.figure()
plt.imshow(imgConvo,cmap='gray')
plt.figure()
plt.imshow(img,cmap='gray')

#2.2
fftimg = np.fft.fft2(img)
fftimg = np.fft.fftshift(fftimg)
magne = 20*np.log(np.abs(fftimg))
phase = np.angle(fftimg)

plt.figure()
plt.title("Magnetude")
plt.imshow(magne,cmap='gray')
plt.figure()
plt.title("Phase")
plt.imshow(phase,cmap='gray')

#augmente les fréquence basse tels que le blanc par rapport au noir
f =  np.array([0,-4,0,-4,18,-4,0,-4,-0])
filtre = f.reshape((3, 3))
imgConvo = convolution(img,filtre)
plt.figure()
plt.title("Passe-haut")
plt.imshow(imgConvo,cmap='gray')

#augmente les fréquence basse tels que le noir par rapport au blanc
f =  np.array([1,1,1,1,6,1,1,1,1])
filtre = f.reshape((3, 3))
imgConvo = convolution(img,filtre)
plt.figure()
plt.title("Passe-bas")
plt.imshow(imgConvo,cmap='gray')