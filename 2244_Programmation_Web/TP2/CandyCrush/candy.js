function Controller() {
    this.modele = 0
    this.matriceVue = []
    this.rightToClick = 1;

    /**
     * Empeche l'utilisateur de clicker au cours du traitement
     */
    this.stopClicking = function() {

    }

    /**
     * Réautorisse le fait de clicker pour l'utilisateur
     */
    this.restartClicking = function() {

    }

    /**
     * Crée une matrice temporaire vérifiant si la finalisation du mouvement permet de créé une combianaison.
     * Pour ce faire regarde si les deux indices contiennent ou non une combinaison
     */
    this.movePossible = function(x1, y1, x2, y2) {

    }

    /**
     * Regarde si les éléments qui sont autours de la position permettennt une combianaison avec cette dernière
     * Pour ce faire regarde en haut, bas , droite, gauche
     */
    this.combinaison = function(x, y) {

    }

    /**
     * Parcours tous les éléments du tableau et lance la méthode combinaison
     */
    this.checkAllElements = function() {

    }

}

function Vue() {
    this.listImage = []
    this.controller = 0;
    /**
     * Appel movePossible du controller qui verifie si le mouvement créer une combinaison
     */
    this.animation = function(x1, y1, x2, y2) {

    }

    //Regarde si il existe encore des espaces
    this.existSpace = function() {

    }

    /**
     * Fait descendre les elements lorsqu'un element vide est contenu dans son milieu
     * Si un element plus haut que les autres et aucun element au dessus alors créer de nouveaux elements
     */
    this.descenteElement = function() {

    }

    /**
     * Crée les élements manquant dans le tableau
     */
    this.creationElement = function() {

    }

    this.lastElementOfColumn = function(x, y) {

    }
}

function Modele() {
    this.matrice = []
    this.score = 0
    this.update = function(matrice) {

    }
    this.addToScore(x) {

    }
}