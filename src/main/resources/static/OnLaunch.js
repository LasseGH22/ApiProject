document.addEventListener("DOMContentLoaded", function () {
    const selector = document.getElementById("selector");
    updatePriceOnLaunch();
    updateGraphOnLaunch(selector.value);
    connectWebSocket();
})

function updatePriceOnLaunch() {
    fetch("http://localhost:8080/api/v1/reading/latest")
        .then(response => response.json())
        .then(data => {
            insertData(data);
        })
}

async function updateGraphOnLaunch(currency) {
    await fetch("http://localhost:8080/api/v1/reading/top40")
        .then(response => response.json())
        .then(data => {
            drawGraph(data,currency)
        });
}