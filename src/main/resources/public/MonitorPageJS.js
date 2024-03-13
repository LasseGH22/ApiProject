
var stompClient = null;
function connectWebSocket() {
    var socket = new SockJS('/wss');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/latest', function (message) {
            var data = JSON.parse(message.body);
            console.log(data);
            document.getElementById("symbol").textContent = data.name;
            document.getElementById("usd").innerText = data.priceUSD + "$";
            document.getElementById("gbp").innerText = data.priceGBP + "£";
            document.getElementById("eur").innerText = data.priceEUR + "€";
            document.getElementById("date").innerText = data.dateTime;
        });
    });
}

document.addEventListener("DOMContentLoaded", function () {
    connectWebSocket();
});





/*
document.addEventListener("DOMContentLoaded",function () {
    function fetchLatest() {
        fetch("http://localhost:8080/api/v1/reading/latest")
            .then(response => response.json())
            .then(data => {
                document.getElementById("symbol").textContent = data.name;
                document.getElementById("usd").innerText = data.priceUSD;
                document.getElementById("gbp").innerText = data.priceGBP;
                document.getElementById("eur").innerText = data.priceEUR;
                document.getElementById("date").innerText = data.dateTime;

                setTimeout(fetchLatest,5000);
            })
            .catch(error => {
                console.error("error fetching data: ", error);
                setTimeout(fetchLatest,5000);
            });
    }

    fetchLatest();
})

 */

