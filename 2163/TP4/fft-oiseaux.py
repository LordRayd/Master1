# -*- coding: utf-8 -*-
"""
Created on Wed Apr 19 14:31:07 2017

@author: sylviegibet

"""

# Saisies 
import numpy as np
from matplotlib.pyplot import *
import matplotlib.pyplot as plt
from scipy.io.wavfile import read
from scipy.fftpack import fft
from scipy import signal

#(fs,x) = read('Sons/Oiseaux/etourneau.wav')
#(fs,x) = read('Sons/Oiseaux/fauvette.wav')
#(fs,x) = read('Sons/Oiseaux/piebavarde.wav')
(fs,x) = read('Sons/Oiseaux/rossignol.wav')
#(fs,x) = read('Sons/Classique/nocturne_chopin.wav')


print('taille du fichier : ', x.size)
print('fréquence d\'échantillonnage : ', fs)
print('durée du signal : ', x.size/fs, 's')

#Nf=4096
#offset=256   

def tffAmplitude(Nf,offset):
    sf = np.zeros(Nf)
    sf[:]=x[offset:offset+Nf]
    X =fft(sf)/Nf
    F =np.linspace(0,fs,Nf)	
    mX = 2*abs(X)
    powerSpectra = 10 * np.log10(abs(X))
    pX=np.angle(X)
    print(np.size(mX))
    
    plt.figure()
    plt.xlabel('Frequency [Hz]')
    plt.ylabel('Amplitude')
    F1=F[offset:1024+offset]
    #mX1=mX[256:1024+256]
    mX1=powerSpectra[offset:1024+offset]
    plt.plot(F1,mX1)
    
def tffPhase(Nf,offset):
    sf = np.zeros(Nf)
    sf[:]=x[offset:offset+Nf]
    X =fft(sf)/Nf
    F =np.linspace(0,fs,Nf)	
    mX = 2*abs(X)
    powerSpectra = 10 * np.log10(abs(X))
    pX=np.angle(X)
    print(np.size(mX))
    
    plt.figure()
    plt.xlabel('Frequency [Hz]')
    plt.ylabel('Phase')
    F1=F[100:offset]
    pX1=pX[100:offset]
    plt.plot(F1,pX1)
    
def tffSpectre(NF,offset):
    plt.figure()
    f, t, Sxx = signal.spectrogram(x, fs)
    plt.pcolormesh(t, f, np.log(Sxx))
    plt.ylabel('Frequency [Hz]')
    plt.xlabel('Time [sec]')
    

def calculerSpectre(Nf, offset, winSize):
    plt.figure()
    f, t, Sxx = signal.spectrogram(x, fs)
    #plt.pcolormesh(t, f, np.log(Sxx))
    plt.pcolormesh(t, f[offset:winSize+offset], np.log(Sxx[offset:winSize+offset]))
    plt.ylabel('Frequency [Hz]')
    plt.xlabel('Time [sec]')
    
def calculerSpectre(echantillons,fe,fenetre,nz=2,db=False):
    N = echantillons.size
    zeros=np.zeros(nz*N)
    echantillons = np.concatenate((zeros,echantillons*signal.get_window(fenetre,N),zeros))
    spectre = np.absolute(fft(echantillons))
    spectre = spectre / spectre.max()
    NN = len(echantillons)
    if db:
        spectre = 20*np.log10(spectre)
    freq = np.arange(NN)*fe/NN
    return [freq,spectre]
    
def movingFFT(Nf, offset, winSize, winType) :
    [freq,spectre] = calculerSpectre(x,fs,winType,nz=2,db=True)
    figure(figsize=(10,4))
    plot(freq,spectre,'r')
    xlabel('f')
    ylabel('AdB')
    axis([-0.1,fs/2,-160,0])
    grid()
    
    
tffAmplitude(4096,512)
tffPhase(4096,512)
tffSpectre(4096,512)
movingFFT(4096,512,1000,"hamming")
