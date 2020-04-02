$(document).ready(function() {
    $.get("http://localhost:3000/news", function(data, status) {
        $("#news").html(data);
    });
    $.getJSON("http://localhost:3000/pression", function(data, status) {
        var pression = {
            name: "Pression",
            x: data.heure,
            y: data.pression,
            type: "line"
        };
        $.getJSON("http://localhost:3000/temp", function(data, status) {
            var temperature = {
                name: "Temperature",
                x: data.heure,
                y: data.temp,
                type: "line"
            };
            var data = [temperature, pression];
            console.log(data);
            Plotly.newPlot('plotly-chart', data);
        });
    });
    setInterval(function() {
        $.get("http://localhost:3000/news", function(data, status) {
            $("#news").html(data);
        });
    }, 10000);
    setInterval(function() {
        $.getJSON("http://localhost:3000/pression", function(data, status) {
            var pression = {
                name: "Pression",
                x: data.heure,
                y: data.pression,
                type: "line"
            };
            $.getJSON("http://localhost:3000/temp", function(data, status) {
                var temperature = {
                    name: "Temperature",
                    x: data.heure,
                    y: data.temp,
                    type: "line"
                };
                var data = [temperature, pression];
                console.log(data);
                Plotly.newPlot('plotly-chart', data);
            });
        });
    }, 4000);

    $("#plotly-chart")
});