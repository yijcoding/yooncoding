import React, { useEffect, useLayoutEffect, useState } from 'react';
import './modal.scss';

import io from 'socket.io-client';

function ShowChatList(props){
    return (
    // <>
    // {props.receivedMessages.map((msg, index) => {
    //     if(props.user === props.member_id){
    //         <div style={{float:'right'}}>
    //             <li key={index} style={{textAlign:'right'}}>
    //                 {props.member_id} : {msg}
    //             </li>
    //         </div>
    //     }
    //     else{
    //         <div style={{float:'left'}}>
    //             <li key={index} style={{textAlign:'left'}}>
    //                 {props.user} : {msg}
    //             </li>
    //         </div>
    //     }
    // })}
    // </>
    
    // 내가 보낸 메세지
    // props.user === props.member_id
    // ?
    // <>
    // {props.receivedMessages?.map((msg, index) => (
    //     <li key={index} style={{textAlign:'right'}}>
    //         {props.member_id} : {msg}
    //     </li>
    // ))}
    // </>
    // :
    // //상대방이 보낸 메세지
    // <>
    // {props.receivedMessages.map((msg, index) => (
    //     <li key={index} style={{textAlign:'left'}}>
    //         {props.user} : {msg}
    //     </li>
    // ))}
    // </>
    <>
    {props.receivedMessages?.map((msg, index) => {
        if(props.user === props.member_id){
            <li key={index} style={{textAlign:'right'}}>
                {props.member_id} : {msg}
            </li>
        }
        else{
            <li key={index} style={{textAlign:'left'}}>
                {props.user} : {msg}
            </li>
        }
    })}
    </>
    );
}

const ModalChat = (props) => {
    const { open, close, header, member_id, roomId } = props;

    const [message, setMessage] = useState('');
    const [receivedMessages, setReceivedMessages] = useState([]);

    const [user, setUser] = useState();

    const socket = io('http://localhost:5000', {
		cors: {
			origin: "*",
		}
	});
    
    useEffect(() => {
        // 메시지 이벤트 리스너를 등록합니다.
        socket.on('chat message', data => {
            setReceivedMessages((prevMessages) => [...prevMessages, data.message]);
            setUser(data.user);
            console.log(user);

            for(let i = 0; i < receivedMessages.length; i++){
                console.log(receivedMessages[i]);
            }
        });

        // 컴포넌트 언마운트 시, 이벤트 리스너를 정리합니다.
        return () => {
            socket.off('chat message');
        };
    },[message, user]);

    const sendMessage = (e) => {
        console.log("message = ", message);
        e.preventDefault();
        
        // 서버로 메시지 이벤트를 보냅니다.
        socket.emit('chat message', { roomId, message, member_id });
        setMessage('');
    };

    return (
        <div className={open ? 'openModal modal' : 'modal'}>    
            {open ? 
            (
            <section style={{position:'relative'}}>
                <header>
                    <h4>{header}({roomId})</h4>
                    <button className="close" onClick={close}>
                        X
                    </button>
                </header>
                <main style={{position:'relative', height:'300px', width:'100%'}}>
                    <ul style={{maxHeight: '100%', overflowY: 'auto'}}>
                    {/* <ShowChatList 
                        receivedMessages={receivedMessages} 
                        member_id={member_id}
                        user={user} 
                    /> */}
                    {receivedMessages?.map((msg, index) => {
                        if(user === member_id){
                            <li key={index} style={{textAlign:'right'}}>
                                {member_id} : {msg}
                            </li>
                        }
                        else{
                            <li key={index} style={{textAlign:'left'}}>
                                {user} : {msg}
                            </li>
                        }
                    })}
                    </ul>
                    {/* <ul>
                    {receivedMessages && receivedMessages.map((msg, index) => (
                        <li key={index} style={{textAlign:'right'}}>
                            {member_id} : {msg}
                        </li>
                    ))}
                    </ul> */}
                </main>
                <footer>
                    {/* <form onSubmit={sendMessage}>
                        <textarea
                            type='text'
                            cols='35'
                            value={message}
                            onChange={e => {setMessage(e.target.value)}}
                        />&nbsp;
                        <button type='submit'>전송</button>
                    </form> */}
                    <input
                            
                    />
                </footer>
            </section>
            )
            : 
            null}
        </div>
    );
};

export default ModalChat;