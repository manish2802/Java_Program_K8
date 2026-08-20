function callApi() {
    fetch("/Java8SecondHighestSalaryDemo")
        .then(response => response.text())
        .then(data => {
            document.getElementById("result").innerText = data;
        })
        .catch(error => {
            console.error("API Error:", error);
        });
        
          fetch("/Java8-second-highest-salary-number")
        .then(response => response.text())
        .then(data => {
            document.getElementById("2").innerText = data;
        })
        .catch(error => {
            console.error("API Error:", error);
        });
}