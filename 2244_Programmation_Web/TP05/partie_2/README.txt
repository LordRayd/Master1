Bonjour il semblerait que le module nw-builder de mon nwjs ne fonctionne pas.
J'arrive à lancé l'application en temps réel mais pas à faire un build exécutable.


Question 1:
js : document.getElementById("montexte").value
jquery : $("#montexte").val()

Question 2:
La boite d'alerte est apparut

Question 3:
$(document.body).text("coucou")

Question 4:
$("#ladiv").text("changement de la div")

Question 5:
$("#ladiv").html( "<b>test</b>" );
ou
$("#ladiv").text("changement de la div")
$("#ladiv").css("font-weight", "bold");

Question 6:
$("#ladiv").text("<b>test<b>")

Question 7:
$("#ladiv").hide()
$("#ladiv").hide("slow")
$("#ladiv").show()
$("#ladiv").show("slow")

Question 8:
$("#un").attr("class", "red");


Question 9:
$("#deux").after($("#deux").prev());
$("#trois").after($("#trois").prev());
$("#quatre").after($("#quatre").prev());
$("#trois").after($("#trois").prev());
$("#quatre").after($("#quatre").prev());
$("#quatre").after($("#quatre").prev());


Question 10:
$(function() {
    $("#lettrejaune").draggable();
    $("#lettrerouge").draggable();
    $("#lettrebleu").draggable();
});

Question 11:
$("#boitejaune" ).droppable({
    drop: function( event, ui ) {
        if(ui.draggable.attr("id") =="lettrejaune"){
            console.log("une lettre a ete deposé dans la boite jaune");
            $( this ).attr('src','ressources/images/boite jaune ouverte.jpg');
        }
        }
});
$("#boiterouge" ).droppable({
    drop: function( event, ui ) {
        if(ui.draggable.attr("id") =="lettrerouge"){
            console.log("une lettre a ete deposé dans la boite rouge");
            $( this ).attr('src','ressources/images/boite rouge ouverte.jpg');
        }
        }
});
$("#boitebleu" ).droppable({
    drop: function( event, ui ) {
        if(ui.draggable.attr("id") =="lettrebleu"){
            console.log("une lettre a ete deposé dans la boite bleu");
            $( this ).attr('src','ressources/images/boite bleu ouverte.jpg');
        }
        }
});
