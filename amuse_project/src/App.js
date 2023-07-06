import './App.css';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Home from './screens/Home';
import Menu from './components/Menu';
import List from './screens/List';
import Footer from './components/Footer';
import { useRef } from 'react';
import SelectedMyPage from './screens/SelectedMyPage';
import FAQ from './screens/FAQ';
import HomeModal from './components/Home/HomeModal';

function App() {
  const searchNameRef = useRef();
  const onChangeSearchName = (e) => {
    searchNameRef.current = e.target.value;
    // console.log(searchNameRef.current);
  };
  return (
    <Router>
      <Menu searchNameRef={searchNameRef} onChangeSearchName={onChangeSearchName}/>
      <Routes>
        <Route path = "/" element= { <Home />}></Route>
        <Route path = "/list" element= { <List />}></Route>
        <Route path = "/mypage/selectedmypage" element= { <SelectedMyPage />}></Route>
        <Route path = "/customer/faq" element= { <FAQ />}></Route>
        <Route path = "/modal" element= { <HomeModal />}></Route>
      </Routes>
      {/* <Footer /> */}
    </Router>
  );
}

export default App;
