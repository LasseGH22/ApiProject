

var stompClient = null;
function connectWebSocket() {
    var socket = new SockJS('/wss');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        updateCurrentPrice();
        updateGraph();
    });
}

// Subscribes to the topic containing the price data & updates the gui
function updateCurrentPrice() {
    stompClient.subscribe('/topic/latest', function (message) {
        var data = JSON.parse(message.body);
        insertData(data);
    });
}

function updateGraph() {
    stompClient.subscribe('/topic/top40Reversed', function (message) {
        var data = JSON.parse(message.body)
        selector = document.getElementById("selector");
        drawGraph(data,selector.value);
    })
}

// Method for inserting data
function insertData(data) {
    document.getElementById("symbol").textContent = data.name;
    document.getElementById("usd").innerText = data.priceUSD + "$";
    document.getElementById("gbp").innerText = data.priceGBP + "£";
    document.getElementById("eur").innerText = data.priceEUR + "€";
    document.getElementById("date").innerText = data.dateTime;
}