import logo from './logo.svg';
import './App.css';
import Board from './pages/Board/main/board.js'
import React, { useEffect, useState } from 'react';
import { call } from './ApiService';
import { Route, Router, Routes } from 'react-router-dom';
import AddBoard from './pages/Board/addBoard/addBoard';
import Detail from './pages/Board/detail/detail';
import UpdateBoard from './pages/Board/updateBoard/updateBoard';
import ElasticBoard from './pages/Board/main/ElasticBoard';


function App() {

  return (


    <Routes>
      <Route path="/board" element={<ElasticBoard />}></Route>
      <Route path="/addboard" element={<AddBoard></AddBoard>}></Route>
      <Route path="/detail" element={<Detail />}></Route>
      <Route path="/updateBoard" element={<UpdateBoard />}></Route >
    </Routes >



  );
}

export default App;
