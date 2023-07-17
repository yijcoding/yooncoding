import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import AmuseDetail from './pages/amuse-detail/AmuseDetail';
import RideDetail from './pages/ride-detail/RideDetail';
import ReviewListPaging from './pages/review/ReviewListPaging';

import SearchPw from './login/views/Searchpw';
import NewPw from './login/views/NewPw';
import PwComplete from './login/views/PwComplete';

import Menu from './login/components/Menu';

import Home from './index/screens/Home';
import List from './index/screens/List';
import SelectedMyPage from './index/screens/SelectedMyPage';
import FAQ from './index/screens/FAQ';
import HomeModal from './index/components/Home/HomeModal';

import { FullWriteList } from './login/views/FullWriteList';
import { FullEditMember } from './login/views/FullEditMember';
import { KakaoLogin } from './login/service/kakaoLogin';
import { KakaoInter } from './login/views/KaKaoInter.js';
import Login from './login/views/login';
import SignUp from './login/views/SignUp';
import { useRef } from 'react';
import AmuseList from "./pages/amuse-list/AmuseList";
import Footer from "./index/components/Footer";

const App = () => {
  const searchNameRef = useRef();
  const onChangeSearchName = (e) => {
    searchNameRef.current = e.target.value;
    // console.log(searchNameRef.current);
  };

  
  return (
    <Router>
      <Menu searchNameRef={searchNameRef} onChangeSearchName={onChangeSearchName}/>
      {/* <Menu/> */}
      <Routes>
        {/* 일준 */}
        {/* <Route path="/board" element={<ReviewListPaging/>}/> */}
        {/* <Route path="/" element={<AmuseList/>}/> */}
        <Route path="/amusement/:amuse_id" element={<AmuseDetail/>}/>
        <Route path="/rideDetail/:rides_id/:amuse_id" element={<RideDetail/>}/>

        {/* 시진형 */}
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/writeList/:id" element={<FullWriteList />} />
        <Route path="/searchPw" element={<SearchPw />} />
        <Route path="/newPw" element={<NewPw />} />
        <Route path="/pwComplete" element={<PwComplete />} />
        <Route path="/editMember" element={<FullEditMember />} />
        <Route path="/kakaoLogin" element={<KakaoLogin />} />
        <Route path="/kakaoInter" element={<KakaoInter />} />

        {/* 동욱 */}
        <Route path = "/" element= { <Home />}></Route>
        <Route path = "/amuseList" element= { <List />}></Route>
        <Route path = "/mypage/selectedmypage" element= { <SelectedMyPage />}></Route>
        <Route path = "/customer/faq" element= { <FAQ />}></Route>
        <Route path = "/modal" element= { <HomeModal />}></Route>
      </Routes>
      <Footer/>
    </Router>
  );
}

export default App;