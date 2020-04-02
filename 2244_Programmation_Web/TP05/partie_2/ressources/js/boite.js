$(document).ready(function() {

    $("input").click(function() {

        eval($("#montexte").val());

    });

    $(function() {
        $("#lettrejaune").draggable();
        $("#lettrerouge").draggable();
        $("#lettrebleu").draggable();
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
    });
});