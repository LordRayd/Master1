import numpy as np
import matplotlib.pyplot as plt

def GenSig(N,k,type):
    A = np.zeros(N)
    if(type == 'I'):
        A[k] = 1
    if(type == 'S'):
        for i in range(k,N):
            A[i] = 1
    if(type == 'R'):
        for i in range(k,N):
            A[i] = i
    return A

def question_1_2():
    R = GenSig(20,5,'I') 
    plt.figure(1) 
    plt.stem(R) 

def question_2():
    R = GenSig(20,5,'S') 
    plt.figure(2) 
    plt.stem(R) 

def question_3():
    R = GenSig(20,5,'R') 
    plt.figure(3) 
    plt.stem(R) 

def question_4():
    R = GenSig(20,6,'S') + GenSig(20,7,'R') - 6*GenSig(20,7,'S')
    R += -GenSig(20,11,'R') + 9*GenSig(20,11,'S') - GenSig(20,12,'R') 
    R += 11*GenSig(20,12,'S') + GenSig(20,16,'R') - 15*GenSig(20,16,'S')
    plt.figure(4) 
    plt.stem(R)

def genSin(N,f,fs):
    t = np.linspace(0,(N-1)/fs,N)
    R = np.sin(2 * np.pi * f * t)
    plt.figure(5) 
    plt.stem(R)
    
def genCos(N,f,fs):
    R = np.zeros(N)
    for i in range(f,N):
        R[i] = np.cos(2 * np.pi * f * (i/fs))
    plt.figure(6) 
    plt.plot(R)

def question_5_2(N,f,fs):
    t = np.linspace(0,(N-1)/fs,N)
    R = np.sign(np.sin(2 * np.pi * f * t))
    plt.figure(7) 
    plt.plot(R)

def question_5_2_alt(N,f,fs):
    R = np.zeros(fs*(f+1))
    T = np.zeros(N)
    for i in range(0,f+1):
        nb = i * fs
        for j in range(1,fs+1):
            if((j <= (fs/2)+1) and (nb+j-1 != nb)):
                R[nb+j-1] = 1
            else : 
                R[nb+j-1] = -1
    R[0] = 0
    for i in range(0,N):
        T[i] = R[i]
    plt.figure(8) 
    plt.plot(T)
    plt.stem(T)
    
def question_5_3(f,fs):
    return f*fs

import cmath as cm
def expsig(n,a):
    isreal = np.isreal(a)
    result = np.zeros(n)
    if(isreal == True):
        for i in range(n):
            result[i] = np.power(i,a)
    else :
        theta = cm.phase(a)
        for i in range(n):
            result[i] = (a.real**i) * (np.cos(theta * i) + np.sin(theta * i)) 
    print(result)
    plt.figure(9)
    plt.plot(result)
    plt.stem(result)
        
a = 0.95 ** cm.exp(complex(0.0,(np.pi/10)))
expsig(50,a)