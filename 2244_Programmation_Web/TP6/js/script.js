$(function() {

    $(".volet").on("click", function() {
        if ($(this).hasClass('central')) {
            if ($(this).width() == 470) {
                $('.volet').each(function() {
                    $(this).animate({ width: '100px' });
                    $(this).find(".panneauTxt").hide();
                });
                $(this).addClass("imgG");
                $(this).removeClass("central");
            }
        } else {
            $('.central').each(function() {
                $(this).find(".panneauTxt").hide();
                $(this).animate({ width: '100px' });
                $(this).addClass("imgG");
                $(this).removeClass("central");
            });
            $(this).find('.panneauTxt').css({
                'top': $(this).position().top + 117,
                'left': $(this).position().left + 117
            });
            $(this).find(".panneauTxt").show();
            $(this).animate({ width: '470px' });
            $(this).addClass("central");
            $(this).removeClass("imgG");
        }
    });
});