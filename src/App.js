import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import AmuseList from './pages/amuse-list/AmuseList';
import AmuseDetail from './pages/amuse-detail/AmuseDetail';
import RideDetail from './pages/ride-detail/RideDetail';
import PreviousPage from './components/PreviousPage';

const App = () => {
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