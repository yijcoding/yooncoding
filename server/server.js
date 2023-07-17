const http = require("http");
const express = require("express");
const app = express();
const io = require('socket.io');

const cors = require('cors')

app.use(cors());

const PORT = 3001;

const httpServer = http.createServer(app).listen(PORT);

const socketServer = io(httpServer, {
	cors: {
		origin: "*",
		methods: ["GET", "POST"]
	}
});

//=======================================
socketServer.on('connection', (socket) => {
    console.log("새로운 사용자가 접속했습니다");

    socket.on('sendMessage', (data) => {
        console.log(data);

        socketServer.emit('message', data);
        
    })

    socket.on('enter', (data) => {
        console.log(data);
        socketServer.emit('enter', data);
        
    })

    socket.on('disconnect', () => {
        console.log('유저가 나갔습니다.');
    })
})
//=======================================