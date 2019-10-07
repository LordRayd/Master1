import numpy as np
import matplotlib.pyplot as plt
from numpy import random
from numpy.linalg import matrix_power

def question_1_2_1():
    x = np.array([0,1,2,3])
    v = np.array([0,1,2,3])
    print(v)
    print(type(v))
    fig = plt.figure()
    plt.plot(x,v,'rv--', label = 'v(x)')
    plt.legend(loc='lower right')
    plt.xlabel('x')
    plt.ylabel('v')
    plt.title('Mon titre')
    plt.xlim([-1,4])
    plt.ylim([0,5])
    plt.show()
    fig.savefig('toto.png')

def quetion_1_2_2():
    vector = np.array([0,1,2,3])
    plt.plot(vector, 'rv--', label='vecteur')
    plt.show()
    matrix = np.array([[1,12],[1, 2]])
    plt.plot(matrix, 'rv--', label='matrice')
    plt.show()

def question_1_2_3():
    vector = np.array([0,1,2,3])
    matrix = np.array([[1,12],[1, 2]])
    a=np.array([1,2,3], dtype = complex)
    a[0] = 3.2
    print(a)
    a.dtype
    
    print(vector.dtype)
    print(matrix.dtype)

def question_1_2_4():
    vector = np.array([0,1,2,3])
    matrix = np.array([[1,12],[1, 2]])
    print(np.shape(vector))
    print(np.shape(matrix))

def question_1_2_5():
    tab =  np.arange(0, 10, 1)
    print(tab)
    tab2 = np.linspace(0,9,10)
    print(tab2)
    tab3 = np.logspace(0,1,10,dtype=int)
    print(tab3)

def question_1_2_6():
    M = random.randn(3,3)
    print(M)
    a = random.randn(10000)
    hist = plt.hist(a,40)

def question_1_2_7():
    vector = np.zeros(10,dtype=int)
    print(vector)
    matrix = np.ones((3,3),dtype=int)
    print(matrix)
    
def question_1_2_8():
    points = [1,2,3]
    diagonale = np.diag(points)
    print(diagonale)
    plt.plot(diagonale, 'rv--', label='matrice')
    plt.show()
    
def question_1_2_9():
    M = [[np.nan,np.nan,np.nan],
         [np.nan,np.nan,np.nan],
         [np.nan,np.nan,np.nan],
         [np.nan,np.nan,np.nan],
         [np.nan,np.nan,np.nan]]
    print(M)
    
def initfunction(i,j):
    return 100 + 10*i + j

def question_1_2_10():
    nb = np.fromfunction(lambda i, j: initfunction(i,j), (5,3), dtype=int)
    print(nb)
    
def question_1_2_11():
    matrix = np.ones((3,5,7),dtype=int)
    np.save('file.npy',matrix)
    b = np.load('file.npy')
    print(b)
    
def question_1_3_1():
    A =  np.ones((4,2),dtype=int)
    B =  np.ones((2,3),dtype=int)
    print(A[0,0])
    print(B[0][2])
    print(A.shape)
    print(B.shape)
    print(A.ndim)
    print(B.ndim)
    
def question_1_3_2():
    M = random.randn(3,3) 
    print("Matrice de depart : ")
    print(str(M))
    # Array to be added as column 
    column_to_be_added = np.array([1, 2, 3]) 
    # Adding column to numpy array 
    result = np.hstack((M, np.atleast_2d(column_to_be_added).T)) 
    # printing result 
    print ("Matrice finale")
    print(str(result)) 
    
def question_1_3_3():
    M = random.randn(3,3)
    print(M)
    print("Tranche")
    print(M[0:2:1])
    
def question_1_4_1():
    A =  np.ones((3,2),dtype=int)
    B =  np.ones((3,2),dtype=int)
    AB = np.add(A,B)
    print(AB)

def question_1_4_2():
    A =  np.ones((3,2),dtype=int)
    B =  np.ones((3,2),dtype=int)
    C = np.multiply(A,3)
    print(B)
    D = np.divide(B,4)
    print(D)
    
def question_1_4_3():
    u = np.ones(10,dtype=int)
    print(u)
    v = np.ones(10,dtype=int)
    print(v)
    w = np.ones(10,dtype=int)
    print(w)
    z = 2*v -3*w + u/3
    print(z)
    
def multiplyMatrice(A,B):
    return np.dot(A,B)

def question_1_4_4():
    A =  np.ones((3,2),dtype=int)
    B = [3,3]
    result = multiplyMatrice(A,B)
    print(result)

def question_1_4_5():
    A = [[2,5,6],[4,3,6]]
    B = [3,3]
    C = [3,2]
    print(np.dot(A,np.dot(B,C)))

def matrice_puissance(M,P):
    R = M
    for i in range(0, P):
        R = np.multiply(R,M)
    return R
    
def question_1_4_6():
    M = np.array([[2,2],
         [2,2]])
    print(matrice_puissance(M,2))
    print(matrix_power(M,2))
    
def question_1_4_7():
    A = [[2,4],[4,6]]
    B = [3,3]
    result = multiplyMatrice(A,B)
    print(result)
    result = multiplyMatrice(B,A)
    print(result)
    
def question_1_4_8():
    A = [[2,5],[4,3]]
    B = [[2,3],[5,7]]
    C = [[7,3],[4,3]]
    print(np.dot(A,np.dot(B,C)))
    print(np.dot(np.dot(A,B),C))

def question_1_4_9():
    A = [[2,5],[4,3]]
    I = [[1,0],[0,1]]
    print(A)
    print(np.dot(A,I))

def question_1_4_10():
    A = [[0, 1], [2, 3]]
    T = np.transpose(A)
    print(A)
    print(T)
    
def question_1_4_11():
    A = np.array([[1, 2], [3, 4]])
    print(np.linalg.det(A))
    print(np.linalg.matrix_rank(A))
    print(np.trace(A))
    print(np.linalg.inv(A))
    
def question_1_4_12():
    A = np.array([[1,2], [3,4]])
    b = np.array([1,5])
    print(np.linalg.solve(A,b))

def scalaire(v1,v2):
    return np.vdot(v1,v2)
    
def question_1_4_13():
    A = np.ones((10,1),dtype=int)
    B = np.ones((1,10),dtype=int)
    C = np.zeros((1,10),dtype=int)
    print(scalaire(A,B))
    print(scalaire(C,A))

def produitVector(v1,v2):
    return np.cross(v1,v2)

def question_1_4_14():
    x = [1, 2, 3]
    y = [4, 5, 6]
    print(produitVector(x,y))

def question_1_4_15():
    x = [1, 2, 3]
    y = [4, 5, 6]
    print(np.linalg.norm(x))
    print(np.linalg.norm(y))
    
question_1_4_15()