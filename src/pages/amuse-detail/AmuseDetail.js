import React, { useEffect, useLayoutEffect, useRef, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import axios from 'axios';
import FacList from '../fac-list/FacList';
import ReviewList from '../review/ReviewList';
import 'bootstrap/dist/css/bootstrap.min.css';
import './amuseDetail.scss';
import RidesList from '../rides-list/RidesList';
import { Container, List } from 'reactstrap';
import KakaoMap from '../../components/KakaoMap';

const AmuseDetail = (props) => {

    const {amuse_id} = useParams();
    const [amuseDetail, setAmuseDatail] = useState();
    const [amuseImage, setAmuseImage] = useState();

    const [imgUrl, setImgUrl] = useState("");

    useLayoutEffect(() => {
        axios.get(`http://localhost:8080/test/amuseDetail/${amuse_id}`)
            .then(response => setAmuseDatail(response.data))
    
        axios.get(`http://localhost:8080/test/amuseImage/${amuse_id}`)
            .then(response => setAmuseImage(response.data))
    },[amuse_id]);
    
    const handleMouseImage = (url) => {
        setImgUrl(url);
    }

    //반올림 => Math.round

    //scroll 관련
    //브라우저 현재 높이: window.innerHeight
    //document가 수직으로 얼마나 스크롤 됐는지 픽셀 단위로 반환: window.scrollY(window.pageYOffset)

    const useScrollRestoration = () => {
        const location = useLocation();
        const scrollPositions = useRef({});
        
        useLayoutEffect(() => {
          //현재 스크롤 위치를 저장
          scrollPositions.current[location.key] = window.scrollY;
      
          //이전 페이지로 돌아갈 때(뒤로 가기 누를 때) 저장된 스크롤 위치로 복원
          const restoreScrollPosition = () => {
            if(scrollPositions.current[location.key] !== undefined){
              window.scrollTo(0, scrollPositions.current[location.key])
            }
          };
      
          restoreScrollPosition();
        }, [location]);
      };

    return (
        <Container className='d-flex tot-wrapper'>
            <div className='main-wrapper col-md-8'> 
                <Container id='top'>
                    <section className="py-3">
                        <header className='header-title' style={{fontSize: '3rem'}}>{amuseDetail?.a_name}</header>
                        <div style={{fontWeight: 'bold', fontSize: '1.3rem'}}>운영시간: {amuseDetail?.a_time}</div>
                        <a href='#review' style={{textDecoration:'none', color:'black'}}>{(() => {
                            const array = [];
                            for(let i = 0; i < Math.round(amuseDetail?.avg_grade); i++) {
                                array.push(<span key={i}>⭐</span>);
                            }
                            return array;
                        })()}
                        <span style={{fontWeight:'bold'}}>&nbsp;({amuseDetail?.avg_grade})</span><br/></a>
                        <span style={{fontWeight:'bold'}}>조회수: {amuseDetail?.a_view}</span>
                        <article>
                            <br/>
                            {/* main image */}
                            <div className='main-image-wrapper'>
                                <img src={imgUrl === "" ? amuseDetail?.a_img : imgUrl} 
                                    className='main-image rounded' alt='main'/>
                            </div>
                            {/* ===여기는 왜 col-8 영역 적용이 안되는걸까요!?!?=== */}
                            {/* width: 100%를 하면 두번째 사진만 크기가 달라짐.. */}
                            <div className='side-image-wrapper d-flex'>
                                {/* side image */}
                                {amuseImage?.map(img => (
                                    <div key={img.aimg_id}>
                                        <img className='side-image rounded' src={img.url} alt='side' 
                                            onMouseMove={e => {
                                                e.preventDefault();
                                                handleMouseImage(img.url);
                                        }}/>
                                    </div>
                                ))}
                            </div>
                        </article>
                        <article>
                            <br/><br/>
                            <h5>{amuseDetail?.a_info}</h5>
                        </article>
                    </section>
                </Container>
                <div>
                    <RidesList/>
                </div>
                <div>
                    <FacList/>
                </div>
                <div>
                    <ReviewList/>
                </div>
                <div>
                    <br/>
                    <KakaoMap lat={amuseDetail?.a_lat} lng={amuseDetail?.a_lng}/>
                </div>
            </div>
            <div className='side-wrapper col-md-4'>
                <Container className="side-box">
                    <section className="py-3">
                        <header className='header-title' 
                            style={{fontSize:'2.5rem', textAlign:'center'}}>Category</header>
                        <List style={{textDecoration:'none'}}>
                            <ul className='side-ul'>
                                <li><a href='#top'>Title</a></li>
                                <li><a href='#ride'>Ride</a></li>
                                <li><a href='#facility'>Facilitiy</a></li>
                                <li><a href='#review'>Review</a></li>
                                <li><a href='#location'>Location</a></li>
                            </ul>
                        </List>
                    </section>
                </Container>
            </div>
        </Container>
    );
};

export default AmuseDetail;