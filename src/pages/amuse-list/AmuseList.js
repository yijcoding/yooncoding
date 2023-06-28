import axios from 'axios';
import React, { useEffect, useState } from 'react';
import {Card} from "../../components/Card";

import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import Slider from 'react-slick';
import { Container } from 'reactstrap';

const AmuseList = () => {

    const [amuseList, setAmuseList] = useState([]);

    useEffect(() => {
    axios.get('http://localhost:8080/test/amuseList')
      .then(response => setAmuseList(response.data))
    },[]);

    const settings = {
      infinite : true,      // 무한 반복 옵션     
      slidesToShow : 4,     // 한 화면에 보여질 컨텐츠 개수
      slidesToScroll : 1,   // 스크롤 한번에 움직일 컨텐츠 개수
      speed : 1000,         // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
      dots : true,          // 스크롤바 아래 점으로 페이지네이션 여부
      autoplay : true,      // 자동 스크롤 사용 여부
      autoplaySpeed : 2000, // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
      pauseOnHover : true,  // 슬라이드 이동 시 마우스 호버하면 슬라이더 멈추게 설정
      draggable : false     //드래그 가능 여부
    }

    return (
    <Container className='amuseList-wrapper'>
        <div className='amuseList-header'>
            Amusement List
        </div>
        <div className='amuseList-body'>
        <Slider {...settings}>
            {amuseList.map(amuse => (
                <Card key={amuse.amuse_id} id={amuse.amuse_id} path="amusement"
                name={amuse.a_name} a_country={amuse.a_country} img={amuse.a_img}/>
            ))}
        </Slider>
        </div>
    </Container>
    );
};

export default AmuseList;