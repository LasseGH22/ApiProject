
document.addEventListener("DOMContentLoaded", function () {
    connectWebSocket();
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




document.addEventListener("DOMContentLoaded", function () {
    function fetchLatest() {
        fetch("http://localhost:8080/api/v1/reading/readings")
            .then(response => response.json())
            .then(data => {
                const labels = data.map(item => item.dateTime);
                const prices = data.map(item => item.priceUSD);

                const config = {
                    type: 'line',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Bitcoin Price (USD)',
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            data: prices
                        }]
                    },
                    options: {
                        scales: {
                            x: {
                                type: 'time',
                                time: {
                                    unit: 'minute',
                                    tooltipFormat: 'MMM D, YYYY h:mm:ss a',
                                    displayFormats: {
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
                                    text: 'Price in USD'
                                }
                            }
                        }
                    }
                };
                const ctx = document.getElementById('graph').getContext('2d');
                const bitcoinChart = new Chart(ctx, config);
            });
    }

    fetchLatest();
});


