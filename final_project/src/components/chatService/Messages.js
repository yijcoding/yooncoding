import React, { useEffect } from 'react';

import BasicScrollToBottom from 'react-scroll-to-bottom';
import Message from './Message';

import './messages.scss';

const Messages = ({ messages, name }) => {

    useEffect(() => {
        console.log(messages);
    }, [messages]);


    return (
    //메세지 입력할 때 스크롤 아래로 내려가도록 설정
    <BasicScrollToBottom className="messages">
      {messages.map((message, i) => {
        return <div key={i}><Message message={message} name={name} /></div>
      })}
    </BasicScrollToBottom>
    );
};

export default Messages;