import React, { useEffect, useLayoutEffect, useRef, useState } from 'react';
import './modal.scss';
// import queryString from 'query-string';

import io from 'socket.io-client';
// import { useLocation } from 'react-router-dom';

let socket;
const ENDPOINT = 'http://localhost:3001';

function Messages(props) {
    const {member_id, receivedMessages} = props;
    console.log("member_id = ", member_id);
    console.log("receivedMessages = ", receivedMessages);

    return (
        // 문제점 1) 다른 사람의 채팅 메세지 받을 때 작성자가 메세지 입력하면 다른 사람 아이디가 작성자 아이디로 변경
        // <>
        // {receivedMessages.map((msg, index) => (
        //     <li key={index} style={member_id === user ? { justifyContent: 'flex-end' } : { justifyContent: 'flex-start' }}>
        //         {/* {member_id === user ? `${member_id} : ${msg}` : `${member_id} : ${msg}`} */}
        //         {member_id} : {msg}
        //     </li>
        // ))}
        // </>

        //2) 어떻게 하면 작성자의 채팅창에서 상대방의 메세지를 볼 때 상대방의 아이디로 메세지가 나오게 할 수 있을까?

        // <>
        // {member_id === user 
        // ? 
        // (
        //     receivedMessages.map((msg, index) => (
        //         <li key={index}>{member_id} : {msg}</li>
        //     ))
        // )
        // : 
        // (
        //     otherMsg.map((msg, index ) => (
        //         <li key={index}>{user} : {msg}</li>
        //     ))
        // )}
        // </>
        <ul>
        {receivedMessages.map((data, index) => {
            return member_id === data.member_id 
            ?
            // <li key={index} style={{ display: 'flex', justifyContent: 'flex-end' }}>
            <li key={index} style={{float:'right', clear:'both'}}>
                {/* <div style={{maxWidth:'60%',
                            width:'100px', 
                            borderRadius:'15px', 
                            textAlign:'center',
                            fontSize:'1.1rem',
                            fontWeight:'bold'}}>{member_id}</div> */}
                <div style={{
                            fontWeight:'bold',
                            marginBottom:'10px',
                            width:'150px',
                            height:'50px',
                            lineHeight:'50px', 
                            backgroundColor:'lightskyblue', 
                            borderRadius:'15px',
                            textAlign:'center'}}>{data.message}</div>
            </li>
            :
            <div>
                <li key={index} style={{float:'left', clear:'both'}}>
                    <div style={{maxWidth:'60%',       
                                width:'100px', 
                                borderRadius:'15px', 
                                textAlign:'center',
                                fontSize:'1.1rem',
                                fontWeight:'bold'}}>{data.member_id}</div>
                    <div style={{
                                width:'150px',
                                height:'50px',
                                lineHeight:'50px', 
                                backgroundColor:'lightskyblue', 
                                borderRadius:'15px',
                                textAlign:'center'}}>{data.message}</div>
                </li>
            </div>    
        })}
        </ul>
    );
}

function UserList(props){
    const {users} = props;

    return (
    <>
    <h5>[ User List ]</h5>
    <ul>
        {users.map((user, index) => (
            <li key={index}>{user}</li>
        ))}
    </ul>
    </>
    );
}

const ModalChat = (props) => {
    //modal 관련
    const { open, close, header, member_id } = props;
    
    //쿼리스트링 정보 알기 위함 => Join에서 Link의 url 정보 가져옴
    // const location = useLocation();

    //사용자 목록
    const [users, setUsers] = useState([]);

    //입장 메세지 표시
    const [isOpen, setIsOpen] = useState(false);
    const [enterMsg, setEnterMsg] = useState([]);

    //채팅방에서 사용할 익명 이름과 방 이름
    // const [name, setName] = useState();
    // const [room, setRoom] = useState();

    //보낸 메세지
    const [message, setMessage] = useState('');
    //받은 메세지
    const [receivedMessages, setReceivedMessages] = useState([]);

    // const [otherMsg, setOtherMsg] = useState([]);

    //렌더링 된 후 실행
    useEffect(() => {
        //소켓 서버 연결 요청
        socket = io(ENDPOINT, {
            cors: {
                origin: "*",
            }
        });

        // //Join에서 Link url의 쿼리스트링에서 name과 room을 가져옴
        // const {room, name} = queryString.parse(window.location.search);
        // //받은 name, room 저장
        // setName(name);
        // setRoom(room);

        // socket.emit('join', {name, room}, (error) => {
        //     if(error){
        //         alert('join emit error!');
        //     }
        // });
    }, [ENDPOINT]);
    

    useEffect(() => {
        // 소켓 서버로부터의 메세지를 저장
        socket.on('message', data => {
            // setUsers((prevUser) => [prevUser, data.member_id]);

            setUsers((prevUsers) => {
                // 새로운 data.member_id가 이미 users 배열에 존재하는지 확인
                if (!prevUsers.includes(data.member_id)) {
                  // 중복이 없는 경우에만 data.member_id를 추가
                  return [...prevUsers, data.member_id];
                }
                // 중복이 있는 경우에는 이전 users 배열을 그대로 반환
                return prevUsers;
              });

            setReceivedMessages((prevMessages) => [...prevMessages, data]);
        });
        socket.on('enter', data => {
            if(data.member_id === member_id){
                setEnterMsg((prev) => {
                    if(!prev.includes(data.enterMsg)){
                        return [...prev, data.enterMsg];
                    }
                    else{
                        return prev;
                    }
                }) 
            }
            else{
                setEnterMsg((prev) => {
                    if(!prev.includes(data.enterMsg)){
                        return [...prev, `${data.member_id} 님이 입장했습니다`];
                    }
                    else{
                        return prev;
                    }
                }) 
            }
        })
    },[]);

    //메세지 전송
    const sendMessage = (e) => {
        if(message.length !== 0 && message !== ""){
            console.log("message = ", message);
            e.preventDefault();
            
            // 서버로 메시지 이벤트를 보냅니다.
            socket.emit('sendMessage', { message, member_id });
            setMessage('');
        }
        else{
            alert("글을 입력하세요");
        }
    };

    

    //메세지 입력하거나 받을 때 스크롤 아래로 내리기
    // const scrollRef = useRef(null);

    // const moveScroll = () => {
    //     if (scrollRef.current) {
    //         scrollRef.current?.scrollIntoView({ behavior: "smooth" });
    //     }
    // };

    // useEffect(() => {
    //     moveScroll();
    // },[message]);


    // if(open){
    //     socket.emit('enter', 
    //         {msg : `${member_id} 님이 입장했습니다`}
    //     )

    //     setReceivedMessages([]);
    // }


    useLayoutEffect(() => {
        if (open) {
            // 채팅창이 열리면 상태 초기화
            setIsOpen(true);
            setReceivedMessages([]);
            setEnterMsg([]);

            socket.emit("enter", {
                user : member_id,
                enterMsg : `${member_id}님이 입장했습니다`
            });
        }
    },[open]);

    return (
        <div className={open ? 'openModal modal' : 'modal'}>    
            {open ? 
            (
            <section style={{position:'relative'}}>
                <header>
                    <h4 style={{textAlign:'center'}}>{header}</h4>
                    <button className="close" onClick={close}>
                        X
                    </button>
                </header>
                <div style={{display:'flex', marginTop:'10px'}}>
                    <main style={{position:'relative', height:'400px', width:'80%', overflow:'auto'}}>
                        <ul>
                            {enterMsg && enterMsg.map((msg, index) => (
                                <li key={index} style={{listStyle:'none', textAlign:'center'}}>{msg}</li>
                            ))}
                        </ul>
                        <Messages member_id={member_id} receivedMessages={receivedMessages}/>
                        {/* <div ref={scrollRef}></div> */}
                    </main>
                    <div style={{width:'20%', height:'400px', overflow:'hidden', textAlign:'center', borderLeft:'1px solid lightgray', marginLeft:'10px'}}>
                        <UserList users={users}/>
                    </div>
                </div>
                <footer>
                    <form onSubmit={sendMessage}>
                        <textarea
                            type='text'
                            cols='70'
                            value={message}
                            onChange={e => {setMessage(e.target.value)}}
                        />&nbsp;
                        <button type='submit'>전송</button>
                    </form>
                </footer>
            </section>
            )
            : 
            null}
        </div>
    );
};

export default ModalChat;