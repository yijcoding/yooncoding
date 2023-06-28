import logo from './logo.svg';
import './App.css';
import Board from './pages/Board/main/board.js'
import React, { useEffect, useState } from 'react';
import { call } from './ApiService';
import { Route, Router, Routes } from 'react-router-dom';
import AddBoard from './pages/Board/addBoard/addBoard';
import Detail from './pages/Board/detail/detail';
import UpdateBoard from './pages/Board/updateBoard/updateBoard';


function App() {

  // const [items, setItems] = useState([]);

  // useEffect(() => {
  //   const requestOptions = {
  //     method: "GET",
  //     headers: { "Content-Type": "application.json" },
  //   };

  //   fetch("http://localhost:8080/board/board", requestOptions)
  //     .then((response) => response.json())
  //     .then((data) => {
  //       setItems(data);
  //       console.log(data);
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }, []);




  // const all = (items) => {
  //   console.log(items);
  // };

  return (


    <Routes>
      <Route path="/board" element={<Board />}></Route>
      <Route path="/addboard" element={<AddBoard></AddBoard>}></Route>
      <Route path="/detail" element={<Detail />}></Route>
      <Route path="/updateBoard" element={<UpdateBoard />}></Route >
    </Routes >



  );
}

export default App;
