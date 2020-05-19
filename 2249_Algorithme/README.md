# Algorithme des données
## Enseignant
    Charlotte Pelletier
## Plan
* Analyse en composantes principales
* Algorithme des K-moyennes
* Régression linéaire descente de gradient
* Régression logistique descente de gradient
* Régularisation
* Algorithme de classification
* Algorithme des K Plus Proche Voisin
* Sélection de modèles
## Controle
    1 sur table non noté (CC)
    1 Quizz de 1h sur moodle
    Les TPs rendu
## Ouverture TP
Les TPs sont soit: <br> 
* Visibles directement sur Github
* Soit ouvrables et exécutables sur JupyterLab, Notebook ou VsCode 
## Rappel Mathématique
Matrice A : 
| a | b |
|---|---|
| c | d |

Matrice B : 
| a | b | c |
|---|---|---|
| d | e | f |
| g | h | i |

### Déterminant
detA = 1 / ad - bc <br>
detB = 1 / aei + bfg + cdh - ceg - fha -ibd 
### Matrice Inverse
A<sup>-1</sup> = 1 / detA *
| d | -b |
|---|---|
| -c | a |

B<sup>-1</sup> = 1 / detB * 
| ei - fh | - ch - bi | bf - ce |
|---|---|---|
| - fg - di | ai - cg | - cd - af |
| dh - eg | - bg - ah | ae - bd |

### Valeurs propres
Pour A resoudre :
* (a - lambda)x + by = 0
* cx + (d - lambda)y = 0

Pour B resoudre : 
* (a - lambda)x + by + cz = 0
* dx + (e - lambda)y + fz = 0
* gx + hy + (i - lambda)z = 0

## Controle Moodle
Resultat : 11.43 moyenne de classe 11.13<br>
J'ai corrigé les erreurs que je connaissais.
### Partie 1 QCM
#### Question 1
Trouver l’ensemble des paramètres (biais et poids) pour une régression linéaire peut s’effectuer uniquement avec l'algorithme de descente de gradient.
* Vrai
#### Question 2
Astride cherche à appliquer l’algorithme des k plus proches voisin sur des données qu’elle sait fortement bruitées. Elle doit utiliser une **grande** valeur de k.
#### Question 3
Un modèle régularisé est plus susceptible de sur-apprendre si le paramètre de régularisation est faible.
* Vrai
#### Question 4
D’après le théorème de décomposition de l’inertie, trouver le minimum de l’inertie intra-classe revient à maximiser l’inertie inter-classe.
* Vrai
#### Question 5
Lors d'une validation-croisée sur k partitions, une valeur de k élevée permet de trouver des valeurs des hyperparamètres plus optimales (qui améliora les performances de classification ou régression).
* Faux
#### Question 6
Est-il probable que le résultat de clustering suivant ait-été obtenu par l'algorithme des k-Moyennes (k=3) ?
* Depend de l'image
#### Question 7
Lors de l'implémentation de l’algorithme de descente de gradient, la mise à jour des paramètres (biais et poids) est identique pour la régression linéaire et la régression logistique.
* Vrai
#### Question 8
La régression ridge<br>
Veuillez choisir au moins une réponse :
* combine les principes de régularisation LASSO et Elasticnet.
* permet de réduire la variance de l’algorithme au détriment d’un biais plus élevé.
* met plusieurs poids à zéro.
* ajoute une pénalité L1 à la fonction de coût. (Pas celle la)
#### Question 9
Pour un nuage de points donné, l’inertie totale dépends des données et des clusters auxquels appartiennent les données.
* Faux
#### Question 10
Est-il probable que le résultat de clustering suivant ait-été obtenu par l'algorithme des k-Moyennes (k=3) ?
* Depend de l'image
#### Question 11
D'après l'exécution du code ci-dessus, complétez le texte suivant :<br>
Il y a un total de *581012* observations représentées par variables *54* explicatives.<br>
Les données *ne sont pas* centrées-réduites. La valeur médiane pour la variable numérotée 1 est d'envirion *127*
#### Question 12
Lors d’une régularisation (ajout d'une pénalité à la fonction coût), le biais est le seul paramètre (coefficient) qui n’est pas régularisé.
* Vrai
#### Question 13
Associez chaque terme de l'équation suivante au type d'inertie correspondant.
* Formule de l'inertie
#### Question 14
Après avoir utilisé une Analyse en Composantes Principales, les données sont représentées dans un espace de plus petite dimension.
* Faux
#### Question 15
D’après le théorème de Bayes, la probabilité a posteriori augmente quand la vraisemblance *Augmente*.
#### Question 16
Dans une régression ridge, les poids sont plus importants quand le paramètre de régularisation est faible.
* Faux
#### Question 17
Chaque figure ci-dessous représente les frontières de décision apprises par un algorithme des k-plus proches voisins.<br>
Associez pour chaque figure la valeur la plus probable de  k : k=1 / k=5 / k=30
* Depend de l'ordre des images.
#### Question 18
Parmi les matrices suivantes, lesquelles sont des matrices de covariances valides ?
* Depend de l'ordre et des valeurs
#### Question 19
La validation-croisée peut permettre de révéler un problème de sur-apprentissage.
* Vrai
#### Question 20
La fonction de coût utilisée pour la régression logistique est identique à celle utilisée pour la régression linéaire ?
* Faux
#### Question 21
Quel est l'algorithme implémenté par ce code ? (1 seule bonne réponse)
* k-Plus Proches Voisins (suivant l'algorithme montré)
#### Question 22
Pour le problème de classification binaire ci-dessous (classe bleue versus classe verte), trois frontières de décision en pointillés ont été apprises. Associez à chaque figure, le type d’apprentissage qui caractérise le mieux ces frontières de décision.
* Depend de l'ordre des images : pour moi sous-apprentissage/ sur-apprentissage / bon-compromis
#### Question 23
Dans une Analyse en Composantes Principales, les axes de projection sont toujours tous orthogonaux les uns avec les autres ?
* Vrai
#### Question 24
Associez à chaque type de régularisation, le type de pénalité ajouté à la fonction coût.
* Elasticnet : *Pénalité L1 + L2*
* LASSO : *Pénalité L1* 
* Ridge : *Pénalité L2*
#### Question 25
Est-il probable que le résultat de clustering suivant ait-été obtenu par l'algorithme des k-Moyennes (k=2) ?
* Faux (dépend de l'image)
### Partie 2 Réponses Courtes
#### Question 26
Jenny a des données représentées par dix variables explicatives. Elle souhaite les représenter dans un espace à deux dimensions. Quel algorithme peut-elle utiliser ?
* Il faut que jenny utilise l'analyse en composantes principales
#### Question 27
Dans l’algorithme de descente de gradient, citez un avantage et un inconvénient d’un pas d’apprentissage trop grand.
* Si le pas est trop grand on se rapproche vite de la solution mais il ne permet pas d'obtenir une solution précise. L'idéale est d'avoir un pas qui évolue, il est d'abord grand puis diminue.
#### Question 28 (1/3 points)
Pour l’algorithme de régression logistique avec régularisation, donnez trois exemples d’hyperparamètres. <br>
Aide : le nombre de clusters (k) est un hyperparamètre pour l'algorithme des k-Moyennes.
* le biais, le pas et le poids de pénalisation 
#### Question 29 (0.5/1 point)
Jérôme a plus d’un million de fichiers audios sur son ordinateur. Il souhaite associer à chaque fichier audio un genre musicale. Il a commencé à annoter un millier de fichiers audios selon leur genre. Quel type de problème d’apprentissage automatique peut-il utiliser pour associer automatiquement un genre à ses autres fichiers audios ?
* Il doit utiliser des algorithmes de classification afin d'attribuer une classe à chacun des morceaux restants ( ex : classique)
#### Question 30 (0/1 point)
Soit m échantillons d’apprentissage différents représentés par d variables explicatives, quelle est l’erreur commise sur les données d’apprentissage de l’algorithme 1-plus proche voisin ?
(Indiquez une valeur numérique)
* J'avais Faut avec 0.95
#### Question 31 (0/2 points)
Après avoir entraîné une régression polynomiale, vous vous rendez compte que l’erreur commise sur les données d’apprentissage est beaucoup plus petite que celle commise sur les données de validation. Que pouvez vous faire ?
* J'avais faux
#### Question 32 (1/2 points)
Citez un point commun et une différence entre l’algorithme des k-Moyennes et l’algorithme des k-plus proches voisins.
* Il se base tous deux sur un principe de distance avec les autres données.
* Il itère tous deux jusqu’à la non modification mais non pas la même condition d'arrêt:
    * le premier s’arrête quand ses centroides ne change plus de position
    * le deuxième s'arrête quand aucun point n'a changé de classe
* Les deux peuvent s'arrêter si le nombre d'itération max est atteint
#### Question 33 (1.5/3 points)
Quels sont les avantages et inconvénients d’un modèle simple par rapport à un modèle complexe ?
* un modèle simple sera plus réceptif à la prédiction de nouvelles données là où un modèle plus complexe à des frontières trop précise, et où un élément qui aurait du prendre une classe ne la prend pas. Le modèle simple en revanche ne permet pas une précision de prédiction importante. Pour le modèle complexe l'introduction d'une seule donnée peut changer drastiquement sont rendus.
#### Question 34 (2/2 points)
Soit X la matrice de données représentée par une numpy array. Donnez la ligne de code à utiliser pour centrer-réduire ces données et sauvegarder le résultat dans une nouvelle variable Xz.
* Xz = (X-X.mean(axis=0)) / X.std(ddof=0,axis=0)
#### Question 35 (0/1 point)
Marc rentre de ses vacances et souhaite trier automatiquement ses photos en sous-catégories. Quel type d’algorithme peut-il utiliser ?
* Pas le KPPV j'ai eu 0 avec
### Partie 3 Mini-Exercices
#### Question 36 (1/1 point)
Quel est le déterminant de la matrice ci-dessous ? M = 
| 1 | 2 | 3 |
|---|---|---|
| 0 | 1 | 4 |
| 5 | 6 | 0 |
* 1
#### Question 37 (1/5 points)
Après avoir appliqué à une Analyse en Composantes Principales sur un jeu de données caractérisant différents arbres par leur hauteur, la taille de la couronne, l’âge de l’arbre, le volume, etc. Les résultats suivants sont obtenus :
* a. Quelles sont les composantes nécessaires pour expliquer (au moins) 85 % de la variabilité (e.g. #1, #2, et #3) ?
* b. Soit x une nouvelle observation (un nouvel arbre) avec le vecteur de variables suivant :
    * (200, 100, 20, 10, 5, 15, 8)
quelle est la coordonnée de cette observation si elle est projetée selon la première composante retournée par l’ACP (avec deux chiffres après la virgule) ?
<br>
<br>
* Question A: Les caractéristiques #1, #3, #5 sont nécessaires
#### Question 38 (3/3 points)
Quel est le taux de bonnes classification d’après la matrice de confusion suivante ? (en pourcentage à une décimale prêt, e.g. 56,3%)
* 77,9%
#### Question 39 (0/4 points)
Soit un modèle de régression logistique avec les paramètres suivants β = (−ln(4),ln(2),−ln(3)).<br>
Une nouvelle observation x a le vecteur de variables suivant (1,1). Quelle est la probabilité que cette observation appartienne à la classe positive (Y=1).<br>
Donnez le résultat sous forme de fraction ou sous forme numérique avec trois chiffres après la virgule, e.g. 5/7 (sans espace) ou 0,123<br>
Rappel : le logarithme d’un produit est la somme de ces facteurs ln(a.b)=ln(a)+ln(b)
* Pas de réponse pas le temps
#### Question 40 
Anna veut prédire si une boisson est un thé ou un café. Elle a recueilli les données suivantes :
* a. En utilisant l’algorithme du 1-plus proche voisin avec une distance euclidienne, quelle est l’étiquette prédite pour une boisson de 125 mL contenant 0,015 g de caféine ? Indiquez également le numéro de l'observation dont cette boisson est la plu proche (e.g., #1).
* b. Cette classiﬁcation ne lui semble pas correcte. Que peut-elle faire pour y remédier ?
<br>
<br>
* Question A : L'étiquette prédite pour le boisson est le café et sont plus proche voisin est l'observation #3
* Question B : Si cette classification n'est pas correct alors il faut augmenter le nombre de plus proche voisin observer. centré et réduire ces données pourrait également être intéressant
