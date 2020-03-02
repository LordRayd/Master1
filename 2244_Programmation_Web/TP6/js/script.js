$(document).ready(function() {
    $(".central").click(function() {
        $(".volet").animate({ width: '100px' });
        $(this).animate({ width: '100px' });
    });

    $(".volet").click(function() {
        $(".central").animate({ width: '100px' });
        $(".central").addClass("imgG");
        $(".central").addClass("volet");
        $(".central").removeClass('central')
        $(this).addClass("central")
        $(this).removeClass("imgG")
        $(this).removeClass("volet")

        if ($(this).width() == 100) {
            $(this).animate({ width: '470px' });
        } else {
            $(this).animate({ width: '100px' });
        }
    });
});