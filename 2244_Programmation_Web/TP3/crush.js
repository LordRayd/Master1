class LutinBonbon {

    /**
     * 
     * @param {*} type_  type de l'objet (1, 2, 3, 4, 5) - en gros un tag sur le bonbon
     * @param {*} largeur_  zoom à l'affichage
     * @param {*} hauteur_ zoom à l'affichage
     */
    constructor(type_, largeur_, hauteur_) {
        this.largeur = largeur_
        this.hauteur = hauteur_
        this.type = type_
        this.x1 = 0
        this.y1 = 0
        this.selectionne = false
    }

    /**
     * 
     * @param {*} x1_ 
     * @param {*} y1_ 
     */
    positionXY(x1_, y1_) {
        this.x1 = x1_
        this.y1 = y1_
    }

    /**
     * Renvoie le type du sprite
     */
    typeBonbon() {
        return this.type
    }

    /**
     * true ou false : indique si l'objet est selectionné ou pas
     * @param {*} v 
     */
    marque(v) {
        this.selectionne = v
    }

    /**
     * dessine le sprite avec sa position courante
     * @param {*} context 
     */
    dessin(context) {
        var img = new Image(); // Crée un nouvel élément Image
        if (this.typeBonbon() == 1) {
            img.src = 'images/Blue.png';
        } else if (this.typeBonbon() == 2) {
            img.src = 'images/Green.png';
        } else if (this.typeBonbon() == 3) {
            img.src = 'images/Orange.png';
        } else if (this.typeBonbon() == 4) {
            img.src = 'images/Red.png';
        } else if (this.typeBonbon() == 5) {
            img.src = 'images/Yellow.png';
        }
        context.drawImage(img, this.x1, this.y1);
    }

    /**
     * Prevoit un déplacement vers x2,y2
     * @param {*} x2_ 
     * @param {*} y2_ 
     */
    deplacementVers(x2_, y2_) {
        this.x2 = x2_
        this.y2 = y2_
    }

    /**
     * 
     */
    estEnMouvement() {
        return ((this.x1 !== this.x2) || (this.y1 !== this.y2))
    }

    /**
     * met à jour les coordonnées du sprite si besoin
     */
    metAJour() {
        if (!this.estEnMouvement()) return
        if (this.x1 < this.x2) this.x1 = this.x1 + 5
        if (this.y1 < this.y2) this.y1 = this.y1 + 5
        if (this.x1 > this.x2) this.x1 = this.x1 - 5
        if (this.y1 > this.y2) this.y1 = this.y1 - 5
    }
} // class LutinBonbon

class Vue {
    // contient le modèle de la vue

    /**
     * on passe la taille et un objet contrôleur en argument
     * @param {*} txy 
     * @param {*} monControleur 
     * @param {*} modele 
     * @param {*} tailleLutin 
     */
    constructor(txy, monControleur_, modele_, tailleLutin_) {
        this.taille = txy;
        this.controleur = monControleur_
        this.modele = modele_
        this.tailleLutin = tailleLutin_
    }

    /**
     * la vue va aller chercher son état dans son propre modèle
     */
    metAJourAPartirDuModele() {

    }

    /**
     * 
     * @param {*} x 
     * @param {*} y 
     * @param {*} quel 
     */
    nouveauLutin(x, y, quel) {

    }

    /**
     * intervertit deux cases dans la vue
     * @param {*} x1 
     * @param {*} y1 
     * @param {*} x2 
     * @param {*} y2 
     */
    echange2lutins(x1, y1, x2, y2) {

    }

    /**
     * dessine la vue sans animation
     * @param {*} contexte 
     */
    afficheVue(contexte) {

    }

    /**
     * anime les bonbons et quand c'est terminé, appelle le contrôleur
     * @param {*} contexte 
     */
    animeVue(contexte) {

    }
}

class Modele {

    /**
     * taille est le coté du carré en nombre de cases
     * @param {*} taille 
     */
    constructor(taille_) {
        this.score = 0
        this.taille = taille_
        this.tableau = Array.from({ length: taille_ }, e => Array(taille_).fill(0));
    }

    /**
     * deux cases dans le modèle
     * @param {*} x1 
     * @param {*} y1 
     * @param {*} x2 
     * @param {*} y2 
     */
    echange2cases(x1, y1, x2, y2) {
        var tmp = this.tableau[x1][y1]
        this.tableau[x1][y1] = this.tableau[x2][y2]
        this.tableau[x2][y2] = tmp
    }

    /**
     * on fait les explosions du modèle
     */
    faitExplosion() {
        var listExplosion = this.explosePossible();
        list.forEach(e => this.tableau[e[0]][e[1]] = 0)
    }

    /**
     * est-ce qu'il y a des explosions potentielles dans le modèle ?
     */
    explosePossible() {
        var list = []
        for (var i = 0; i < this.taille; i++) {
            for (var j = 0; j < this.taille; j++) {
                if (this.correspondanceElement(i, j, i + 1, j))
                    if (this.correspondanceElement(i, j, i + 2, j))
                        list.concat([x, j], [x + 1, j], [x + 2, j]);

                if (this.correspondanceElement(i, j, i - 1, j))
                    if (this.correspondanceElement(i, j, i - 2, j))
                        list.concat([x, j], [x - 1, j], [x - 2, j]);

                if (this.correspondanceElement(i, j, i, j - 1))
                    if (this.correspondanceElement(i, j, i, j - 2))
                        list.concat([x, j], [x, j - 1], [x, j - 2]);

                if (this.correspondanceElement(i, j, i, j + 1))
                    if (this.correspondanceElement(i, j, i, j + 2))
                        list.concat([x, j], [x, j + 1], [x, j + 2]);

            }
        }
        return new Set(list)
    }

    correspondanceElement(x1, y2, x2, y2) {
        if (!(this.verifieIndice(x1) || this.verifieIndice(y2) || this.verifieIndice(x2) || this.verifieIndice(y2))) return
        return this.tableau[x1][y1] == this.tableau[x2][y2]
    }

    verifieIndice(position_) {
        return !(position_ < 0 || position_ > this.taille)
    }
}

class Controleur {

    /**
     * 
     * @param {*} tailleJeu 
     * @param {*} tailleLutin 
     */
    constructor(tailleJeu, tailleLutin) {
        this.modele = new Modele(tailleJeu)
        this.vue = new Vue(tailleJeu, this, this.modele, tailleLutin);
    }

    /**
     * est appelé automatiquement quand la vue s'est mise à jour avec l'animation
     * Quand la vue s'est stabilisée, ca veut dire que l'animation en cours 
     * est terminée et que la vue reflète bien le modèle
     * à ce stade, il faut pour le contrôleur, observer le modèle et le modifier 
     * et éventuellement relancer une animation de la vue
     * @param {*} contexte 
     */
    finAnimation(contexte) {

    }

    /**
     * on vient de cliquer à la position x,y dans l'écran
     * @param {*} x 
     * @param {*} y 
     */
    click(x, y) {

    }

    /**
     * fait tomber et rebouche les trous en créant de nouveaux bonbons
     * @param {*} contexte 
     */
    repackGrille(contexte) {

    }

    /**
     * repack une colonne: donne faux si pas besoin
     * @param {*} col 
     */
    repackColonne(col) {

    }
}


/**
 * démarrage du jeu
 * cree une grille de 10x10 avec des lutins de taille 50x50
 * le controleur cree le modele et la vue
 */
function init() {
    // variable globale
    context = document.getElementById("cvs").getContext("2d");
    context.width = document.getElementById("cvs").width;
    context.height = document.getElementById("cvs").height;
    var jeu = new Controleur(10, 50)
        //jeu.maVue.metAJourAPartirDuModele();
        //jeu.maVue.animeVue(context)

    /**
     * on intercepte le click souris 
     * calcul des coordonnées de la souris dans le canvas
     * @param {*} event 
     */
    function captureClick(event) {
        if (event.target.id == "cvs") {
            var x = event.pageX - event.target.offsetLeft;
            var y = event.pageY - event.target.offsetTop;
            jeu.click(x, y)
        }
    }

    document.addEventListener("click", captureClick);
}