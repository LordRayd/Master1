$(document).ready(function() {

    $("input").click(function() {

        eval($("#montexte").val());

    });

    $(function() {
        $("#lettrejaune").draggable();
        $("#lettrerouge").draggable();
        $("#lettrebleu").draggable();
    });
});