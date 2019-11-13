# -*- coding: utf-8 -*-
"""
Éditeur de Spyder

Ceci est un script temporaire.
"""

import pandas as pd   #pour l’exploration de données
import numpy as np    #pour les opérations numériques
import matplotlib.pyplot as plt

#Users
unames = ['user_id', 'gender', 'age', 'occupation', 'zip']
users = pd.read_csv('ml-1m/users.dat', sep='::', header=None, names=unames, engine='python')
users.head()

#Ratings
uratings = ['user_id', 'movie_id', 'rating', 'timestamp']
ratings = pd.read_csv('ml-1m/ratings.dat', sep='::', header=None, names=uratings, engine='python')
ratings.head(10)

#Movies
umovies = ['movie_id', 'title', 'genre']
movies = pd.read_csv('ml-1m/movies.dat', sep='::', header=None, names=umovies, engine='python')
movies.head(10)

#merge all files
data = pd.merge(pd.merge(users, ratings), movies)
data.head()

#%% Question 1 Il y a plus d'hommes que de femmes
np.sum(data.rating > 4.5)
np.sum(data.rating[data.gender == 'F'] > 4.5)
np.sum(data.rating[data.gender == 'M'] > 4.5)

#%% Question 2 suelement 6%
sommePlusDe = np.sum(data.rating[data.gender == 'F'] > 4.5)
sommeFemme = np.sum(data.rating[data.gender == 'F'])
print(sommePlusDe / sommeFemme)

#%% Question 3
np.sum(data[(data.gender == 'M') & (data.age >= 30)].groupby('movie_id', axis=0)['rating'].median() >= 4.5)
np.sum(data[(data.gender == 'F') & (data.age >= 30)].groupby('movie_id', axis=0)['rating'].median() >= 4.5)


#%% Question 4 
data.groupby('movie_id', axis=0)['rating'].mean().nlargest(15)
data[data.movie_id == 787]

#%% Question 5 
data.groupby('movie_id', axis=0)['rating'].count().head()
data2 = pd.concat([data.groupby('movie_id', axis=0)['rating'].mean(),data.groupby('movie_id', axis=0)['rating'].count()], axis=1)
data2.columns = ['mean_rating', 'n_rating']
data2.head()

#%% Question 6
data.groupby('movie_id', axis=0)['rating'].count().head()
data6 = pd.concat([data.groupby('movie_id', axis=0)['rating'].mean(),data.groupby('movie_id', axis=0)['rating'].count()], axis=1)
data6.columns = ['mean_rating', 'n_rating']
data6.head()


#%% Question 1
data.rating.hist(bins=5, align='left', range=[1, 6])

#%% Question 2
data.groupby('movie_id', axis=0)['rating'].count().hist(bins=10)

#%% Question 3 
data.groupby('movie_id', axis=0)['rating'].mean().hist(bins=10, alpha = 0.7)
data[(data.gender == 'M')].groupby('movie_id', axis=0)['rating'].mean().hist(bins=10, alpha=0.5)
data[(data.gender == 'F')].groupby('movie_id', axis=0)['rating'].mean().hist(bins=10, alpha=0.5)


#%% Question 4
seuil_pop = 30
map_id_to_count = data.groupby('movie_id')['rating'].count().to_dict()
data['movie_count'] = data['movie_id'].map(map_id_to_count)
# rajoute une variable comptant le nombre de votes par film
# changer kde par hist pour retrouver un histogramme et non une estimation de la densité
data[data.movie_count >= seuil_pop].groupby('movie_id', axis=0)['rating'].mean().plot(kind='kde', color='b')
data[data.movie_count <= seuil_pop].groupby('movie_id', axis=0)['rating'].mean().plot(kind='kde', color='g')