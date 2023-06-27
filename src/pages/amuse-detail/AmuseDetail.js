import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import FacList from '../fac-list/FacList';
import ReviewList from '../review/ReviewList';
import 'bootstrap/dist/css/bootstrap.min.css';
import './amuseDetail.scss';
import RidesList from '../rides-list/RidesList';
import { Container } from 'reactstrap';

const AmuseDetail = (props) => {

    const {amuse_id} = useParams();
    const [amuseDetail, setAmuseDatail] = useState();
    const [amuseImage, setAmuseImage] = useState();

    useEffect(() => {
        axios.get(`http://localhost:8080/test/amuseDetail/${amuse_id}`)
            .then(response => setAmuseDatail(response.data))
    },[]);

    useEffect(() => {
        axios.get(`http://localhost:8080/test/amuseImage/${amuse_id}`)
            .then(response => setAmuseImage(response.data))
    },[]);

    return (
        <Container className='d-flex'>
            <div className='main-wrapper col-8'> 
                <Container className="px-4 px-lg-5 mt-5">
                    <header className='header-title'>{amuseDetail?.a_name}</header>
                    <figure>운영시간: {amuseDetail?.a_time}</figure>
                    <article>
                        {/* main image */}
                        <div className='main-image-wrapper'>
                            <img src={amuseDetail?.a_img} className='main-image rounded' alt='main-image'/>
                        </div>
                        {/* ===여기는 왜 col-8 영역 적용이 안되는걸까요!?!?=== */}
                        {/* width: 100%를 하면 두번째 사진만 크기가 달라짐.. */}
                        <div className='side-image-wrapper d-flex'>
                            {/* side image */}
                            {amuseImage?.map(img => (
                                <div key={img.aimg_id}>
                                <img className='side-image rounded' src={img.url} alt='side-image'/>
                                </div>
                            ))}
                        </div>
                    </article>
                    <article>
                        <br/><br/>
                        {amuseDetail?.a_info}
                    </article>
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
            </div>
            <div className='aside-wrapper col-4'>
                <Container className="px-4 px-lg-5 mt-5">
                    <header className='header-title'>Category</header>
                    <article></article>
                </Container>
            </div>
        </Container>
    );
};

export default AmuseDetail;