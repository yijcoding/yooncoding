import React, { useState } from 'react';
import { Link } from "react-router-dom";
import { Button } from 'reactstrap';

import './join.scss';

//채팅방 & 채팅 사용자 이름 생성
const Join = () => {

    //사용자 이름 입력
    const [name, setName] = useState("");
    //채팅방 이름 입력
    const [room, setRoom] = useState("");

    return (
    <div className='joinOuterContainer'>
      <div className='joinInnerContainer'>

        <h1 className='heading'></h1>

        <div>
          <input
            placeholder='사용자 닉네임 입력'
            className='joinInput'
            type='text'
            onChange={e => setName(e.target.value)}
          />
        </div>
        <div>
          <input
            placeholder='채팅방 이름 입력'
            className='joinInput mt-20'
            type='text'
            onChange={e => setRoom(e.target.value)}
          />
        </div>

        <Link
          onClick={e => (!name || !room ? e.preventDefault() : null)}
          to={`/chat?name=${name}&room=${room}`}
        >
          <Button className={'button mt-20'} type='submit'>
            가입
          </Button>
        </Link>
      </div>
    </div>
    );
};

export default Join;