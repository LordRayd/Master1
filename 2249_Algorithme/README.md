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