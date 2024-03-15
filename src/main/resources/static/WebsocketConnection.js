
// Runs when the DOM is loaded
document.addEventListener("DOMContentLoaded", function () {
    updateOnLaunch();
    connectWebSocket();
});


// Connects to the websocket for concurrent price updates
var stompClient = null;
function connectWebSocket() {
    var socket = new SockJS('/wss');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        updateCurrentPrice();
    });
}

// Subscribes to the topic containing the price data & updates the gui
function updateCurrentPrice() {
    stompClient.subscribe('/topic/latest', function (message) {
        var data = JSON.parse(message.body);
        insertData(data);
    });
}

// Fetches the latest reading from a Java Spring endpoint for quick loading in gui
function updateOnLaunch() {
    fetch("http://localhost:8080/api/v1/reading/latest")
        .then(response => response.json())
        .then(data => {
            insertData(data);
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