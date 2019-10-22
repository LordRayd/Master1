#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 14:03:51 2019

@author: e1604902
"""

import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import numpy as np

img = mpimg.imread('Images/chimpanze.jpg')
imgplot = plt.imshow(img,cmap='gray')

zoom = img[50:100,50:100]
plt.imshow(zoom)
plt.imsave('Images/chimpanzezoom.jpg', zoom)
def create_circular_mask(h, w, center=None, radius=None):

    if center is None: # use the middle of the image
        center = [int(w/2), int(h/2)]
    if radius is None: # use the smallest distance between the center and image walls
        radius = min(center[0], center[1], w-center[0], h-center[1])

    Y, X = np.ogrid[:h, :w]
    dist_from_center = np.sqrt((X - center[0])**2 + (Y-center[1])**2)

    mask = dist_from_center <= radius
    return mask

h, w = img.shape[:2]
radius = h/3
center = [int(w/3), int(h/4)]
mask = create_circular_mask(h, w, center=center, radius=radius)
masked_img = img.copy()
masked_img[~mask] = 0

plt.imshow(masked_img,cmap='gray')

def echantillonage(image,reso):
    shape = np.shape(image)
    size = image.size
    divby = int(size/reso)
    result = image[0:shape[0]:2,0:shape[1]:2]
    return result
    
plt.figure()
plt.subplot(221)
chimpa128 = echantillonage(img,128)
plt.imshow(chimpa128,cmap='gray')
plt.subplot(222)
chimpa64 = echantillonage(chimpa128,64)
plt.imshow(chimpa64,cmap='gray')
plt.subplot(223)
chimpa32 = echantillonage(chimpa64,32)
plt.imshow(chimpa32,cmap='gray')
plt.subplot(224)
chimpa16 = echantillonage(chimpa32,16)
plt.imshow(chimpa16,cmap='gray')
