const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 8080;

app.use(bodyParser.json());
app.use(cors());

app.post('/sendMessage', (req, res) => {
    const message = req.body.message;
    console.log(`Received message from client: ${message}`);

    let response;
    if (message === 'Hello') {
        response = {message: 'Hi, how can I help you?'};
    } else if (message === 'Bye') {
        response = {message: 'Goodbye, see you later!'};
    } else {
        response = {message: "I don't understand what you mean."};
    }

    res.json(response);
});

app.listen(port, () => {
    console.log(`Server started on port ${port}`);
});
