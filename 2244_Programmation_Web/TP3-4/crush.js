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
        this.x2 = 0
        this.y2 = 0
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
        this.x2 = x1_
        this.y2 = y1_
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
        if (this.typeBonbon() == 1) {
            context.drawImage(document.getElementById("blue"), this.x1, this.y1);
        } else if (this.typeBonbon() == 2) {
            context.drawImage(document.getElementById("green"), this.x1, this.y1);
        } else if (this.typeBonbon() == 3) {
            context.drawImage(document.getElementById("orange"), this.x1, this.y1);
        } else if (this.typeBonbon() == 4) {
            context.drawImage(document.getElementById("red"), this.x1, this.y1);
        } else if (this.typeBonbon() == 5) {
            context.drawImage(document.getElementById("yellow"), this.x1, this.y1);
        } else if (this.typeBonbon() == 0) {
            console.log("erreur bonbon");
        }
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
        return !((this.x1 == this.x2) && (this.y1 == this.y2))
    }

    /**
     * met à jour les coordonnées du sprite si besoin
     */
    metAJour() {
        if (this.estEnMouvement()) {
            if (this.x1 < this.x2) {
                this.x1 = this.x1 + 5
            }
            if (this.y1 < this.y2) {
                this.y1 = this.y1 + 5
            }
            if (this.x1 > this.x2) {
                this.x1 = this.x1 - 5
            }
            if (this.y1 > this.y2) {
                this.y1 = this.y1 - 5
            }
        }

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
        this.controleur = monControleur_;
        this.modele = modele_;
        this.tailleLutin = tailleLutin_;
        this.listLutin = this.metAJourAPartirDuModele();
    }

    /**
     * la vue va aller chercher son état dans son propre modèle
     */
    metAJourAPartirDuModele() {
        this.listLutin = []
        for (let i = 0; i < this.modele.tableau.length; i++) {
            for (let j = 0; j < this.modele.tableau.length; j++) {
                if (this.modele.tableau[i][j] != 0) {
                    this.nouveauLutin(i, j, this.modele.tableau[i][j]);
                }
            }
        }
    }

    /**
     * 
     * @param {*} x 
     * @param {*} y 
     * @param {*} quel 
     */
    nouveauLutin(x, y, quel) {
        let lutin = new LutinBonbon(quel, this.tailleLutin, this.tailleLutin)
        lutin.positionXY(x * this.tailleLutin, y * this.tailleLutin)
        this.listLutin.push(lutin)
    }

    nouveauLutinPourApparition(x, y, quel, compteur) {
        let lutin = new LutinBonbon(quel, this.tailleLutin, this.tailleLutin)
        lutin.positionXY(x * this.tailleLutin, -compteur * this.tailleLutin);
        lutin.deplacementVers(x * this.tailleLutin, y * this.tailleLutin);
        this.listLutin.push(lutin);
    }

    echangePossible(x1, y1, x2, y2) {
            var ret = false;
            this.modele.echange2cases(x1, y1, x2, y2);
            let listExplosion = this.modele.explosePossible();
            for (let i = 0; i < listExplosion.length && ret != true; i++) {
                for (let j = 0; j < listExplosion.length && ret != true; j++) {
                    if (listExplosion[i][j] == 1) {
                        ret = true
                    }
                }
            }
            if (ret == false) {
                this.modele.echange2cases(x1, y1, x2, y2);
            }
            return ret
        }
        /**
         * intervertit deux cases dans la vue
         * @param {*} x1 
         * @param {*} y1 
         * @param {*} x2 
         * @param {*} y2 
         */
    echange2lutins(x1, y1, x2, y2) {
        if (((x1 == x2) && (y1 == y2 - 1 || y1 == y2 + 1)) || ((y1 == y2) && (x1 == x2 + 1 || x1 == x2 - 1))) {

            if (this.echangePossible(x1, y1, x2, y2)) {
                this.listLutin.forEach(sprite => {
                    if (sprite.x1 == (x1 * this.tailleLutin) && sprite.y1 == (y1 * this.tailleLutin)) {
                        sprite.deplacementVers(x2 * this.tailleLutin, y2 * this.tailleLutin);
                    }
                    if (sprite.x1 == (x2 * this.tailleLutin) && sprite.y1 == (y2 * this.tailleLutin)) {
                        sprite.deplacementVers(x1 * this.tailleLutin, y1 * this.tailleLutin);
                    }
                });
                this.animeVue(context)
            }

        }
    }


    effaceEcran(contexte) {
        contexte.clearRect(0, 0, contexte.width, contexte.height);
        contexte.fillStyle = "grey";
        contexte.fillRect(0, 0, contexte.width, contexte.height);
        contexte.fillStyle = "white";
        contexte.fillText(this.modele.score, 100, 800);
    }

    /**
     * dessine la vue sans animation
     * @param {*} contexte 
     */
    afficheVue(contexte) {
        this.effaceEcran(contexte)
        this.listLutin.forEach(sprite => {
            sprite.dessin(contexte);
        });
    }

    metAJour() {
        this.listLutin.forEach(sprite => {
            sprite.metAJour();
        });
    }

    estEnMouvement() {
            var ret = false;
            this.listLutin.forEach(sprite => {
                if (sprite.estEnMouvement()) {
                    ret = true;
                }
            });
            return ret;
        }
        /**
         * anime les bonbons et quand c'est terminé, appelle le contrôleur
         * @param {*} contexte 
         */
    animeVue(contexte) {
        this.metAJour()
            // itere sur la mise à vue de chaque Sprite
        this.afficheVue(contexte)
        var that = this
        if (this.estEnMouvement()) {
            setTimeout(() => { that.animeVue(contexte) }, 10)
        } else {
            this.controleur.finAnimation(contexte);
        }
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
        for (let i = 0; i < this.tableau.length; i++) {
            for (let j = 0; j < this.tableau.length; j++) {
                this.tableau[i][j] = Math.floor(Math.random() * Math.floor(5) + 1)
            }
        }
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
        for (let i = 0; i < listExplosion.length; i++) {
            for (let j = 0; j < listExplosion.length; j++) {
                if (listExplosion[i][j] == 1) {
                    this.score = this.score + 1;
                    this.tableau[i][j] = 0;
                }
            }
        }
    }

    /**
     * est-ce qu'il y a des explosions potentielles dans le modèle ?
     */
    explosePossible() {
        var list = Array.from({ length: this.taille }, e => Array(this.taille).fill(0));
        for (let i = 0; i < list.length; i++) {
            for (let j = 0; j < list.length; j++) {
                if (list[i][j] == 0) {
                    if (this.correspondanceElement(i, j, i + 1, j)) {
                        if (this.correspondanceElement(i, j, i + 2, j)) {
                            list[i][j] = 1;
                            list[i + 1][j] = 1;
                            list[i + 2][j] = 1;
                        }
                    }
                    if (this.correspondanceElement(i, j, i - 1, j)) {
                        if (this.correspondanceElement(i, j, i - 2, j)) {
                            list[i][j] = 1;
                            list[i - 1][j] = 1;
                            list[i - 2][j] = 1;
                        }
                    }
                    if (this.correspondanceElement(i, j, i, j - 1)) {
                        if (this.correspondanceElement(i, j, i, j - 2)) {
                            list[i][j] = 1;
                            list[i][j - 1] = 1;
                            list[i][j - 2] = 1;
                        }
                    }
                    if (this.correspondanceElement(i, j, i, j + 1)) {
                        if (this.correspondanceElement(i, j, i, j + 2)) {
                            list[i][j] = 1;
                            list[i][j + 1] = 1;
                            list[i][j + 2] = 1;
                        }
                    }
                }
            }
        }
        return list
    }

    /**
     * 
     */
    combbinaisonExistante() {
        let ret = false;
        this.explosePossible().forEach(ligne => ligne.forEach(colonne => {
            if (colonne == 1) {
                ret = true;
            }
        }))
        return ret;
    }

    correspondanceElement(x1, y1, x2, y2) {
        if (this.verifieIndice(x1) && this.verifieIndice(y1) && this.verifieIndice(x2) && this.verifieIndice(y2)) {
            if (this.tableau[x1][y1] != 0 && this.tableau[x2][y2] != 0) {
                return this.tableau[x1][y1] == this.tableau[x2][y2];
            }
        }
    }

    verifieIndice(position_) {
        return (position_ >= 0 && position_ < this.taille)
    }
}

class Controleur {

    /**
     * 
     * @param {*} tailleJeu 
     * @param {*} tailleLutin 
     */
    constructor(tailleJeu, tailleLutin) {
        this.tailleJeu = tailleJeu;
        this.modele = new Modele(tailleJeu);
        this.vue = new Vue(tailleJeu, this, this.modele, tailleLutin);
        this.vue.metAJourAPartirDuModele()
        while (this.modele.combbinaisonExistante()) {
            this.modele.faitExplosion();
            this.repackGrille(context);
        }
        this.modele.score = 0;
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
        while (this.modele.combbinaisonExistante()) {
            this.modele.faitExplosion();
            this.repackGrille(contexte);
            this.vue.animeVue(contexte)
        }
        this.vue.metAJourAPartirDuModele();
        this.vue.afficheVue(contexte);
    }

    /**
     * on vient de cliquer à la position x,y dans l'écran
     * @param {*} x 
     * @param {*} y 
     */
    click(x, y) {
        if (this.indice_x1 == undefined && this.indice_y1 == undefined) {
            this.indice_x1 = Math.floor(x / this.vue.tailleLutin);
            this.indice_y1 = Math.floor(y / this.vue.tailleLutin);
        } else if (this.indice_x2 == undefined && this.indice_y2 == undefined) {
            this.indice_x2 = Math.floor(x / this.vue.tailleLutin);
            this.indice_y2 = Math.floor(y / this.vue.tailleLutin);
            this.vue.echange2lutins(this.indice_x1, this.indice_y1, this.indice_x2, this.indice_y2);

            /*this.modele.echange2cases(this.indice_x1, this.indice_y1, this.indice_x2, this.indice_y2);
            this.modele.faitExplosion();
            this.vue.metAJourAPartirDuModele();*/
            this.effacePointClicker();
        }
        this.vue.afficheVue(context);
    }

    effacePointClicker() {
            this.indice_x1 = undefined;
            this.indice_x2 = undefined;
            this.indice_y1 = undefined;
            this.indice_y2 = undefined;
        }
        /**
         * fait tomber et rebouche les trous en créant de nouveaux bonbons
         * @param {*} contexte 
         */
    repackGrille(contexte) {
        for (let i = 0; i < this.tailleJeu; i++) {
            this.repackColonne(i);
        }
        this.rajouteDesLutins();
    }

    /**
     * 
     */
    rajouteDesLutins() {
        for (let i = 0; i < this.tailleJeu; i++) {
            let compteur = 0;
            for (let j = this.tailleJeu - 1; j >= 0; j--) {
                if (this.modele.tableau[i][j] == 0) {
                    this.modele.tableau[i][j] = Math.floor(Math.random() * Math.floor(5) + 1);
                    compteur = compteur + 1;
                    this.vue.nouveauLutinPourApparition(i, j, this.modele.tableau[i][j], compteur);
                }
            }
        }
    }

    /**
     * repack une colonne: donne faux si pas besoin
     * @param {*} col 
     */
    repackColonne(col) {
        let ret = false;
        if (this.elementVideDansColonne(col)) {
            for (let i = 0; i < this.tailleJeu && ret != true; i++) {
                if (this.modele.tableau[col][i] == 0) {
                    if (this.elementPlusHaut(col, i)) {
                        this.deplaceVersLeBasAPartirDe(col, i);
                        ret = true;
                        this.repackColonne(col)
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 
     * @param {*} col 
     */
    elementVideDansColonne(col) {
        let ret = false
        this.modele.tableau.forEach(e => e.forEach(element => { if (element == 0) ret = true }))
        return ret
    }

    /**
     * 
     * @param {*} x 
     * @param {*} y 
     */
    elementPlusHaut(x, y) {
        let ret = false;
        for (let i = y - 1; i >= 0 && ret == false; i--) {
            if (this.modele.tableau[x][i] != 0) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * 
     * @param {*} x 
     * @param {*} y 
     */
    deplaceVersLeBasAPartirDe(x, y) {
        if (x >= 0 && x < this.tailleJeu && y > 0 && y < this.tailleJeu) {
            this.modele.echange2cases(x, y, x, y - 1);
            this.deplaceVersLeBasAPartirDe(x, y - 1);
        }
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
    var jeu = new Controleur(6, 100);
    jeu.vue.afficheVue(context)
    jeu.vue.animeVue(context)

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