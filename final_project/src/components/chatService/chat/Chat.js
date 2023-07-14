import React, { useEffect, useState } from 'react';
import queryString from 'query-string';


import './chat.scss';

import InfoBar from "../info/InfoBar";
import Input from "../Input";
import Messages from "../../components/Messages";
import TextContainer from "../../components/TextContainer";
import io from 'socket.io-client';

// const ENDPOINT = 'http://localhost:5001';

const Chat = () => {

    const [name, setName] = useState('');
    const [room, setRoom] = useState('');
    const [users, setUsers] = useState('');
    const [message, setMessage] = useState('');
    const [receiveMessages, setReceiveMessages] = useState([]);

    const socket = io('http://localhost:5001', {
        cors: {
			origin: "*",
		}
    });

    useEffect(() => {
        // 무슨 의미인지 확인!
        // Join.js의 Link에서 to의 get 방식 경로에 ? 다음을 query-string으로 불러옴
        // socket.emit('join')이 실행되기 전에 setRoom과 setName이 실행되도록 하기 위함!!
        const {name, room} = queryString.parse(window.location.search);

        console.log(name, room);

        setName(name);
        setRoom(room);

        socket.emit('join', {name, room}, (error) => {
            if(error){
                alert(error);
            }
        });

        return () => {
            socket.emit('disconnect');
            socket.off();
        }
    }, [window.location.search]);

    useEffect(() => {
        //소켓 서버로부터 받은 메세지 처리
        socket.on('message', (message) => {
            //의미 확인!!
            setReceiveMessages([...receiveMessages, message]);
        });
        
        socket.on('roomData', ({users}) => {
            setUsers(users);
        });
    }, [])


    //Input 컴포넌트에서 작성한 메세지 전달
    const sendMessage = e => {
        e.preventDefault();
        if(message){
            socket.emit('sendMessage', message, () => {
                setMessage('');
            })
        }
    }

    return (
    <div className='chat-outerContainer'>

        <div className='chat-inner-container'>
            <InfoBar room={room} />

            <Messages messages={receiveMessages} name={name} />

            <Input
            message={message}
            setMessage={setMessage}
            sendMessage={sendMessage}
            />
        </div>

        {/* <TextContainer users={users} /> */}
    </div>
    );
};

export default Chat;