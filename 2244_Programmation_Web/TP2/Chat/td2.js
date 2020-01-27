var cahier = document.getElementById("dessin").getContext("2d");
cahier.largeur = document.getElementById("dessin").width;
cahier.hauteur = document.getElementById("dessin").height
var nbPoints = 0
var positionX = []
var positionY = []
var positionChat = 0
var intervalId

var imgChatMarche = document.getElementById("chatmarche")
imgChatMarche.largeur = 110
imgChatMarche.hauteur = 98

var imgChatDort = document.getElementById("chatdort")
imgChatDort.largeur = 110
imgChatDort.hauteur = 102

function dessineEcran() {
    cahier.clearRect(0, 0, cahier.largeur, cahier.hauteur);

    //BackGround
    cahier.fillStyle = "gold";
    cahier.fillRect(0, 0, cahier.largeur, cahier.hauteur);

    //Bouton Effacer
    cahier.fillStyle = "red"
    cahier.fillRect(0, 0, 120, 40)
    cahier.fillStyle = "black"
    cahier.fillText("Effacer", 50, 20);


    //Bouton Ligne
    cahier.fillStyle = "red"
    cahier.fillRect(140, 0, 120, 40)
    cahier.fillStyle = "black"
    cahier.fillText("Ligne", 190, 20);

    //Bouton Animation
    cahier.fillStyle = "red"
    cahier.fillRect(280, 0, 120, 40)
    cahier.fillStyle = "black"
    cahier.fillText("Animation", 320, 20);

}
dessineEcran()

function relieLigne() {
    cahier.beginPath()
    cahier.fillStyle = "green";
    for (let i = 1; i < nbPoints; i++) {
        cahier.moveTo(positionX[i], positionY[i]);
        cahier.lineTo(positionX[i - 1], positionY[i - 1]);
    }
    cahier.stroke();
}


function dessineChat(bool, lar, hau) {
    if (bool == 1) {
        cahier.drawImage(imgChatDort, lar - imgChatDort.largeur / 2, hau - imgChatDort.hauteur / 2)
    } else {
        cahier.drawImage(imgChatMarche, lar - (imgChatMarche.largeur / 2), hau - (imgChatMarche.hauteur / 2))
    }
}

function dessineChatAuPoint(n) {
    dessineEcran()
    relieLigne();
    if (n < nbPoints && n >= 0) {
        if (n == 0) {
            var audio = new Audio('miaou.mp3');
            audio.play();
        }
        if (n == nbPoints - 1) {
            dessineChat(1, positionX[n], positionY[n])
        } else {
            dessineChat(0, positionX[n], positionY[n])
        }
    }
}

function dessineChatAnimation() {
    if (positionChat < nbPoints) {
        dessineChatAuPoint(positionChat)
        positionChat = positionChat + 1
    } else {
        clearInterval(intervalId)
        positionChat = 0
    }
}

function captureClick(event) {
    if (positionChat == 0) {
        var x = event.pageX - event.target.offsetLeft
        var y = event.pageY - event.target.offsetTop
        if (x <= 120 && y <= 40) {
            dessineEcran()
            positionX = []
            positionY = []
            nbPoints = 0
            positionChat = 0
        } else if (x >= 140 && x <= 260 && y <= 40) {
            relieLigne()
        } else if (x >= 280 && x <= 400 && y <= 40) {
            intervalId = setInterval(dessineChatAnimation, 500)
        } else {
            cahier.fillStyle = "blue";
            cahier.fillRect(x, y, 5, 5)
            positionX[nbPoints] = x
            positionY[nbPoints] = y
            nbPoints = nbPoints + 1
        }
    }
}
document.addEventListener("click", captureClick)