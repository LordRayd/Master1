# -*- coding: utf-8 -*-
"""
Éditeur de Spyder

Ceci est un script temporaire.
"""

import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import matplotlib as mat
import numpy as np
from scipy import signal
from scipy import misc

#%%
img = mpimg.imread('Images/chimpanze.jpg')
F1 = np.fft.fft2(img)
M1 = np.abs(F1)
PH1 = np.angle(F1)

plt.figure()
plt.title('Magnetude')
plt.plot(M1)

plt.figure()
plt.title('Phase')
plt.plot(PH1)

img2 = np.fft.ifft2(M1 * np.exp(1j * PH1)).real
plt.figure()
plt.title('Image reconstituer')
plt.imshow(img2,cmap='gray')

#%%
img1 = mpimg.imread('Images/chimpanze.jpg')
F1 = np.fft.fft2(img1)
M1 = np.abs(F1)
PH1 = np.angle(F1)

img2 = mpimg.imread('Images/lena_grey.png')[:,:,0]
F2 = np.fft.fft2(img2)
M2 = np.abs(F2)
PH2 = np.angle(F2)

img1recon = np.fft.ifft2(M1 * np.exp(1j * PH2)).real
plt.figure()
plt.title('Image reconstituer')
plt.imshow(img1recon,cmap='gray')

img2recon = np.fft.ifft2(M2 * np.exp(1j * PH1)).real
plt.figure()
plt.title('Image reconstituer')
plt.imshow(img2recon,cmap='gray')

#%%
img = mpimg.imread('Images/chimpanze.jpg')

filtre = np.array([
            [1,3,1],
            [3,5,3],
            [1,3,1]])
img2 = signal.convolve2d(img,filtre)

plt.figure()
plt.title('Image convolution')
plt.imshow(img2,cmap='gray')

filtre = np.array([
            [0,-1,0],
            [-1,5,-1],
            [0,-1,0]])
img3 = signal.convolve2d(img,filtre)

plt.figure()
plt.title('Image convolution')
plt.imshow(img3,cmap='gray')

#%%
img = mpimg.imread('Images/chimpanze.jpg')
im_fft = np.fft.fft2(img)

plt.figure()
plt.plot(im_fft)
imfft_passe_bas = np.copy(im_fft)
imfft_passe_haut = np.copy(im_fft)

for i in range(0,125):
    imfft_passe_bas[i] = 0
    

for i in range(125,256):
    imfft_passe_haut[i] = 255
    

plt.figure()
plt.plot(imfft_passe_bas)
M1 = np.abs(imfft_passe_bas)
PH1 = np.angle(imfft_passe_bas)
im_recon_bas =  np.fft.ifft2(M1 * np.exp(1j * PH1)).real

plt.figure()
plt.plot(imfft_passe_haut)
M2 = np.abs(imfft_passe_haut)
PH2 = np.angle(imfft_passe_haut)
im_recon_haut =  np.fft.ifft2(M2 * np.exp(1j * PH2)).real

plt.figure()
plt.title("Normale")
plt.imshow(img,cmap='gray')
plt.figure()
plt.title("Passe Bas")
plt.imshow(im_recon_bas,cmap='gray')
plt.figure()
plt.title("Passe Haut")
plt.imshow(im_recon_haut,cmap='gray')

#%% 3
M = misc.imread('Images/chimpanze.jpg')
salt_value = 95
noise = np.random.randint(salt_value+1, size=(256, 256))

#---------- Pepper ----------#
indexe = np.where(noise == 0)
A = indexe[0]
B = indexe[1]
M[A,B] = 0

#---------- Salt ----------#
indexe = np.where(noise == salt_value)
A = indexe[0]
B = indexe[1]
M[A,B] = 255

#---------- Plot & Save ----------#
fig = plt.figure()
plt.imshow(M,cmap='gray')
plt.show()


filtre1 = np.array([1/9,1/9,1/9,1/9,1/9,1/9,1/9,1/9,1/9])
filtre = filtre1.reshape((3, 3))
img = signal.convolve2d(M,filtre)
plt.figure()
plt.title('Image bruitée filtrée par un moyenneur (3 x 3)')
plt.imshow(img,cmap='gray')

#%%
