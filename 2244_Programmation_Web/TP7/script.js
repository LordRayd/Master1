$(document).ready(function() {
    $("#pression").click(function() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var plotly_vars = JSON.parse(this.responseText);
                console.log(plotly_vars['heure'])
            }
        };
        xmlhttp.open("GET", "http://localhost:3000/pression", true);
        xmlhttp.send();
    });
    $("#temp").click(function() {
        var pression = {};
        var temperature = {};


        var xmlhttpPression = new XMLHttpRequest();
        xmlhttpPression.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                let plotly_vars = JSON.parse(this.responseText);
                console.log(plotly_vars['heure']);
                pression = {
                    name: "Air pressure",
                    x: plotly_vars['heure'],
                    y: [81.32, 72.1, 121.41, 71.31, 40.98, 30.96, 51.2, 71.92],
                    text: ['81.32 Pa', '72.1 Pa', '121.41 Pa', '71.31 Pa', '40.98 Pa', '30.96 Pa', '51.2 Pa', '71.92 Pa'],
                    hoverinfo: 'x+text+name',
                    type: "line",
                };
            }
        };
        xmlhttpPression.open("GET", "http://localhost:3000/pression", true);
        xmlhttpPression.send();


        var xmlhttpTemp = new XMLHttpRequest();
        xmlhttpTemp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                let plotly_vars = JSON.parse(this.responseText);
                temperature = {
                    name: "Energy consumption",
                    x: plotly_vars['heure'],
                    y: [20, 50, 92, 82, 14, 20, 50, 62],
                    text: ['20 kw/H', '50 kw/H', '92 kw/H', '82 kw/H', '14 kw/H', '20 kw/H', '50 kw/H', '62 kw/H'],
                    hoverinfo: 'x+text+name',
                    type: "bar",
                    marker: {
                        color: '#270657'
                    }
                };
            }
        };
        xmlhttpTemp.open("GET", "http://localhost:3000/temp", true);
        xmlhttpTemp.send();

        var data = [temperature, pression];

        layout = {
            showlegend: false,
            yaxis2: {
                type: "category",
                side: "right",
                overlaying: "y"
            }
        };
        Plotly.newPlot('plotly-chart', data, layout);

    });
    $("#newsButton").click(function() {

    });

    setInterval(function() {
        $.get("http://localhost:3000/news", function(data, status) {
            $("#news").html(data);
        });
    }, 4000);
});