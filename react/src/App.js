import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import AmuseList from './pages/amuse-list/AmuseList';
import AmuseDetail from './pages/amuse-detail/AmuseDetail';
import RideDetail from './pages/ride-detail/RideDetail';
import ReviewListPaging from './pages/review/ReviewListPaging';

import SearchPw from './login/views/Searchpw';
import NewPw from './login/views/NewPw';
import PwComplete from './login/views/PwComplete';

import { FullWriteList } from './login/views/FullWriteList';
import { FullEditMember } from './login/views/FullEditMember';
import { KakaoLogin } from './login/service/kakaoLogin';
import { KakaoInter } from './login/views/KaKaoInter.js';
import Login from './login/views/login';
import SignUp from './login/views/SignUp';
import Menu from './login/components/Menu';

const App = () => {

  return (
    <Router>
      <Menu/>
      <Routes>
        <Route path="/board" element={<ReviewListPaging/>}/>
        <Route path="/amuseList" element={<AmuseList/>}/>
        <Route path="/amusement/:amuse_id" element={<AmuseDetail/>}/>
        <Route path="/rideDetail/:rides_id/:amuse_id" element={<RideDetail/>}/>

        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/writeList/:id" element={<FullWriteList />} />
        <Route path="/searchPw" element={<SearchPw />} />
        <Route path="/newPw" element={<NewPw />} />
        <Route path="/pwComplete" element={<PwComplete />} />
        <Route path="/editMember" element={<FullEditMember />} />
        <Route path="/kakaoLogin" element={<KakaoLogin />} />
        <Route path="/kakaoInter" element={<KakaoInter />} />

      </Routes>
    </Router>
  );
}

export default App;