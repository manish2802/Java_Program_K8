function callApi() {
    fetch("/Java8SecondHighestSalaryDemo")
        .then(response => response.text())
        .then(data => {
            document.getElementById("result").innerText = data;
        })
        .catch(error => {
            console.error("API Error:", error);
        });
}