import React from 'react';

const handleKeyDown = e => {
}

const Input = ({ setMessage, sendMessage, message }) => (

  <form className="input-form">
    <input
      className="input"
      type="text"
      placeholder="전송하려는 메시지를 입력하세요."
      value={message}
      sendMessage={sendMessage}
      onChange={({ target: { value } }) => setMessage(value)}
      // onKeyDown={event => event.key === 'Enter' ? sendMessage(event) : null}
      onKeyDown={handleKeyDown}
    />
    <button className="sendButton" onClick={e => sendMessage(e)}>전송</button>
  </form>
  )

export default Input;