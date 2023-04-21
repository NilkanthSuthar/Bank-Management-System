function sendMessage() {
    var message = document.getElementById("messageInput").value;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            document.getElementById("response").innerHTML = response.message;
        }
    };
    xhr.send(JSON.stringify({message: message}));
}
