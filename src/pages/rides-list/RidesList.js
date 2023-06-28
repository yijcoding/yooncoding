import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { Card2 } from '../../components/Card2';
import './ridesList.scss';

import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Slider from 'react-slick';
import { useParams } from 'react-router-dom';
import { Container } from 'reactstrap';

const RidesList = (props) => {
    const {amuse_id} = useParams();

    const [ridesList, setRidesList] = useState();
    
    useEffect(() => {
        axios.get(`http://localhost:8080/test/ridesList/${amuse_id}`)
            .then(response => setRidesList(response.data))
    },[]);

    const settings = {
        infinite : true,      // 무한 반복 옵션     
        slidesToShow : 4,     // 한 화면에 보여질 컨텐츠 개수
        slidesToScroll : 1,   // 스크롤 한번에 움직일 컨텐츠 개수
        speed : 1000,         // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
        dots : true,          // 스크롤바 아래 점으로 페이지네이션 여부
        autoplay : true,      // 자동 스크롤 사용 여부
        draggable : false       //드래그 가능 여부
      }

    return (
        <Container className="rides-wrapper mt-5">
            <section className="py-5">
                <header className='header-title'>놀이기구</header>
                <Slider {...settings}>
                    {ridesList?.map(rList => (
                        <Card2 key={rList.rides_id} id={rList.rides_id} path="rideDetail"
                        name={rList.r_name} img={rList.r_img} location={rList.r_location}/>
                    ))}
                </Slider>
            </section>
        </Container>
    );
};

export default RidesList;