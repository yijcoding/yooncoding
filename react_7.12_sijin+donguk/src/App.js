import logo from "./logo.svg";
import "./App.css";
import React, { useEffect, useRef, useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Login from "./login/views/login";
import SignUp from "./login/views/signup";
import SearchPw from "./login/views/searchpw";
import NewPw from "./login/views/newPw";
import PwComplete from "./login/views/pwComplete";
import Menu from "./login/components/Menu";
import { KakaoLogin } from "./login/service/kakaoLogin";
import { Test } from "./login/views/test";
import { KakaoInter } from "./login/views/kakaoInter";
import { FullEditMember } from "./login/views/fullEditMember";
import { FullWriteList } from "./login/views/fullWriteList";
import { GithubLogin } from "./login/views/githubLogin";
import Test2 from "./Test2";
import Home from "./screens/Home";
import { List } from "react-bootstrap-icons";
import SelectedMyPage from "./screens/SelectedMyPage";
import FAQ from "./screens/FAQ";
import HomeModal from "./components/Home/HomeModal";

function App() {
  const searchNameRef = useRef();
  const onChangeSearchName = (e) => {
    searchNameRef.current = e.target.value;
    // console.log(searchNameRef.current);
  };

  return (
    <div>
      <Menu
        searchNameRef={searchNameRef}
        onChangeSearchName={onChangeSearchName}
      />
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/writeList/:id" element={<FullWriteList />} />
        <Route path="/searchPw" element={<SearchPw />} />
        <Route path="/newPw" element={<NewPw />} />
        <Route path="/pwComplete" element={<PwComplete />} />
        <Route path="/editMember" element={<FullEditMember />} />
        <Route path="/kakaoLogin" element={<KakaoLogin />} />
        <Route path="/kakaoInter" element={<KakaoInter />} />
        <Route path="/githubLogin" element={<GithubLogin />} />
        <Route path="/test" element={<Test />} />
        <Route path="/test2" element={<Test2 />} />

        <Route path="/" element={<Home />}></Route>
        <Route path="/list" element={<List />}></Route>
        <Route
          path="/mypage/selectedmypage"
          element={<SelectedMyPage />}
        ></Route>
        <Route path="/customer/faq" element={<FAQ />}></Route>
        <Route path="/modal" element={<HomeModal />}></Route>
      </Routes>
    </div>
  );
}

export default App;
