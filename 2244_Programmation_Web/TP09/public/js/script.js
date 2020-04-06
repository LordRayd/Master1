$(document).ready(function() {

    $('#btn').on('click', function () {
        $.getJSON(`https://tp9progwebsamuel.herokuapp.com/${$('#user').val()}/distance?A=${$('#A').val()}&B=${$('#B').val()}`, function(data, status) {
            if (data.erreur !== undefined) {
                alert(data.erreur);
            } else {
                document.location.reload(true);
            }
        });
    });

    $('#clearbtn').on('click', function () {
        $.get('https://tp9progwebsamuel.herokuapp.com/clearList', function(data, status) {
            document.location.reload(true);
        });
    });
});