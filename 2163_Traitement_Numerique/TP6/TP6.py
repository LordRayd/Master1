#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 14:03:51 2019

@author: e1604902
"""

import matplotlib.pyplot as plt
import matplotlib.image as mpimg

img = mpimg.imread('Images/lena.png')
imgplot = plt.imshow(img)