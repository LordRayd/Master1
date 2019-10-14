# -*- coding: utf-8 -*-
"""
Éditeur de Spyder

Ceci est un script temporaire.
"""
import numpy as np
import matplotlib.pyplot as plt
from scipy import signal as sg

def question_1_2(N):
    R = np.zeros(N)
    for i in range(N):
        if(0 < i and i < np.pi):
            R[i] = 1
        elif(-np.pi < i and i < 0):
            R[i] = -1
    plt.figure(1)
    plt.plot(R)
    plt.stem(R)

def calculTerme(ordre, x):
    if((ordre%2) != 0):
        return (2*np.sin(ordre*x))/(ordre*(np.pi)) 
    return 0

def serieFourier (ordre, N) :         
    R = np.zeros(N)
    t = np.linspace(0,((N-1)/N)*2*(np.pi),N)   
    for i in range(N):         
        x=t[i]
        y = 0
        for k in range(1,ordre+1):
            terme = calculTerme(k,x)          
            y = y + terme;         
            R[i]=y 
    return R 
        
def ecritureSuper(ordre,N):
    plt.figure(3)
    for i in range(ordre):
        R = serieFourier(i,N)
        plt.plot(R)
        
result = serieFourier(1000,1000)  
plt.figure(2)
plt.plot(result)

plt.figure(5)
test = sg.square(2 * np.pi)

ecritureSuper(100,1000)