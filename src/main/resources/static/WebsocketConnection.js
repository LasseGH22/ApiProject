
document.addEventListener("DOMContentLoaded", function () {
    connectWebSocket();

    var selector = document.getElementById("selector");

    selector.addEventListener("change", function () {
        console.log(selector.value);
        getGraph(selector.value);
    })
});


var stompClient = null;
function connectWebSocket() {
    var socket = new SockJS('/wss');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        updateCurrentPrice();
    });
}


function updateCurrentPrice() {
    stompClient.subscribe('/topic/latest', function (message) {
        var data = JSON.parse(message.body);
        console.log(data);
        document.getElementById("symbol").textContent = data.name;
        document.getElementById("usd").innerText = data.priceUSD + "$";
        document.getElementById("gbp").innerText = data.priceGBP + "£";
        document.getElementById("eur").innerText = data.priceEUR + "€";
        document.getElementById("date").innerText = data.dateTime;
    });
}

/*
var bitcoinChart = null;
async function getGraph(currency) {
    await fetch("http://localhost:8080/api/v1/reading/readings")
        .then(response => response.json())
        .then(data => {

            const labels = data.map(item => {
                const dateString = `${item.dateMonth} ${item.dateDay}, ${item.dateYear} ${item.dateTime}`;
                const date = new Date(dateString);
                return `${date.toLocaleString('default', {month: 'short'})} ${date.getDate()} ${date.getFullYear()} ${date.getHours()}:${date.getMinutes() < 10 ? '0' : ''}${date.getMinutes()}:${date.getSeconds() < 10 ? '0' : ''}${date.getSeconds()}`;
            });


            const colorMapping = {
                'USD': { backgroundColor: 'rgba(255, 99, 132, 0.2)', borderColor: 'rgba(255, 99, 132, 1)' },
                'EUR': { backgroundColor: 'rgba(54, 162, 235, 0.2)', borderColor: 'rgba(54, 162, 235, 1)' },
                'GBP': { backgroundColor: 'rgba(255, 206, 86, 0.2)', borderColor: 'rgba(255, 206, 86, 1)' },
            };
            const defaultColor = { backgroundColor: 'rgba(201, 203, 207, 0.2)', borderColor: 'rgba(201, 203, 207, 1)' };
            const { backgroundColor, borderColor } = colorMapping[currency] || defaultColor;


            const prices = data.map(item => item["price" + currency]);


            drawGraph(currency,labels,prices,backgroundColor,borderColor)
        });
}

function drawGraph(currency,labels,prices,backgroundColor,borderColor) {
    const config = {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Bitcoin price in ' + currency,
                backgroundColor,
                borderColor,
                data: prices
            }]
        },
        options: {
            scales: {
                x: {
                    type: 'time',
                    time: {
                        parser: 'MMM DD, YYYY HH:mm:ss',
                        unit: 'day',
                        tooltipFormat: 'MMM D, YYYY h:mm:ss a',
                        displayFormats: {
                            day: 'MMM D',
                            hour: 'hA',
                            minute: 'h:mm a'
                        }
                    },
                    title: {
                        display: true,
                        text: 'Date and Time'
                    }
                },
                y: {
                    beginAtZero: false,
                    title: {
                        display: true,
                        text: currency
                    }
                }
            }
        }
    };
    const graphBox = document.getElementById('graph').getContext('2d');

    if (bitcoinChart) {
        bitcoinChart.destroy();
    }
    bitcoinChart = new Chart(graphBox, config);
}

 */

