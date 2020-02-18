Samuel LE BERRE Master 1 - AIDN
Pour les points importants j'ai suivi votre conception, à savoir
    - Un bonbon est representé comme un LutinBonbon, les points x et y spécifie la position du lutin sur le canvas.
    - La vue connait le modele et peut créer sa grille par rapport à ce dernier
    - Le controleur gère les clicks sourient et l'appel à l'animation et la fin de celle 
        si repack la grille (redescend les bonbons) et en crée de nouveaux.
    - Le modèle comptent les points, regarde les combinaisons faisables et réalise les explosions.

Problèmes rencontrés :
    L'animation de la chute de bonbon n'est pas correcte car le mouvement de bonbon de chute ne se fait que d'une itération.
    Pour regler ce probleme il faudrait n'ont pas regarder s'il existe des éléments au dessus du premier 0 trouvé dans la matrice 
    du modèle et si oui les faire tomber. Mais regarder pour chaque chiffre différent de 0 combien de 0 se trouve en dessous et
    à ce moment déplacer le bonbon (sprite) du nombre de 0 se trouvant en dessous. 