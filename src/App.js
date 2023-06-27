import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

import { BrowserRouter as Router, Route, Routes, useLocation } from "react-router-dom";

import AmuseList from './pages/amuse-list/AmuseList';
import AmuseDetail from './pages/amuse-detail/AmuseDetail';
import RideDetail from './pages/ride-detail/RideDetail';
import { useLayoutEffect, useRef } from 'react';

// // 뒤로 가기 눌렀을 때 이전 페이지에서의 스크롤 위치로 이동
// const useScrollRestoration = () => {
//   const location = useLocation();
//   const scrollPositions = useRef({});

//   // useLayoutEffect는 렌더링 전에 특정 행동을 수행
//   // (참고) useEffect는 렌더링이 끝나고 특정 행동을 수행
//   useLayoutEffect(() => {
    
//     //현재 스크롤 위치를 저장
//     scrollPositions.current[location.key] = window.scrollY;

//     //이전 페이지로 돌아갈 때(뒤로 가기 누를 때) 저장된 스크롤 위치로 복원
//     const restoreScrollPosition = () => {
//       if(scrollPositions.current[location.key] !== undefined){
//         window.scrollTo(0, scrollPositions.current[location.key])
//       }
//     };

//     restoreScrollPosition();
//   }, [location]);
// };

const App = () => {

  // useScrollRestoration();

  return (
    <Router>
      <Routes>
        <Route path="/" element={<AmuseList/>}/>
        <Route path="/amusement/:amuse_id" element={<AmuseDetail/>}/>
        <Route path="/rideDetail/:rides_id" element={<RideDetail/>}/>
      </Routes>
    </Router>
  );
}

export default App;