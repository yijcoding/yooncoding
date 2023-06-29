import React, { useEffect, useLayoutEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
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

    return (
        <Container className='d-flex tot-wrapper'>
            <div className='main-wrapper col-md-8'> 
                <Container id='top'>
                    <section className="py-3">
                        <header className='header-title' style={{fontSize: '3rem'}}>{amuseDetail?.a_name}</header>
                        <figure style={{fontWeight: 'bold', fontSize: '1.3rem'}}>운영시간: {amuseDetail?.a_time}</figure>
                        <article>
                            {/* main image */}
                            <div className='main-image-wrapper'>
                                <img src={imgUrl === "" ? amuseDetail?.a_img : imgUrl} className='main-image rounded' alt='main'/>
                            </div>
                            {/* ===여기는 왜 col-8 영역 적용이 안되는걸까요!?!?=== */}
                            {/* width: 100%를 하면 두번째 사진만 크기가 달라짐.. */}
                            <div className='side-image-wrapper d-flex'>
                                {/* side image */}
                                {amuseImage?.map(img => (
                                    <div key={img.aimg_id}>
                                        <img className='side-image rounded' alt='side' src={img.url} onMouseMove={e => {
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
                                <li><a href='#top'>Top</a></li>
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