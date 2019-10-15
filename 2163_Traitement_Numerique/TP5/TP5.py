#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct 15 08:34:39 2019

@author: samuel le berre
"""

import numpy as np
from scipy import signal
import matplotlib.pyplot as plt

def fonctionFir(num,cut,w,wn,p,nyquist):
    f = signal.firwin( window=wn,pass_zero=p,nyq=nyquist)
    plt.stem(f)
    plt.xlabel("n")
    plt.ylabel("b")
    plt.grid()

    #Réponse fréquentielle
    w,H=signal.freqz(f)
    
    plt.subplot(211)
    plt.plot(w/(2*np.pi),20*np.log10(np.absolute(H)))
    plt.xlabel("f/fe")
    plt.ylabel("GdB")
    plt.grid()
    plt.subplot(212)      
    plt.plot(w/(2*np.pi),np.unwrap(np.angle(H)))
    plt.xlabel("f/fe")
    plt.ylabel("phase")
    plt.grid()


def fonctionIir(ordre,taille,typeB,typeF):
    b,a = signal.iirfilter(N=ordre,Wn=taille,btype=typeB,ftype=typeF)
    w,H=signal.freqz(b,a)

    plt.subplot(211)
    plt.plot(w/(2*np.pi),20*np.log10(np.absolute(H)))
    plt.xlabel("f/fe")
    plt.ylabel("GdB")
    plt.grid()
    plt.subplot(212)
    plt.plot(w/(2*np.pi),np.unwrap(np.angle(H)))
    plt.xlabel("f/fe")
    plt.ylabel("phase")
    plt.grid()
    
    (zeros,poles,gain) = signal.tf2zpk(b,a) 
    print(np.absolute(poles))
    
Nf = 8000
fonctionFir(101,[500/Nf],None,'nuttall',True,0.5)
fonctionFir(101,[500/Nf,2000/Nf],None,'nuttall',False,0.5)
fonctionFir(101,[2000/Nf,3999/Nf],None,'nuttall',False,0.5)
plt.figure()

fonctionIir(10,[500/Nf],"lowpass","butter")
fonctionIir(10,[500/Nf,2000/Nf],"bandpass","butter")
fonctionIir(10,[4000/Nf],"highpass","butter")
