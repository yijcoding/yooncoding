import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, List } from 'reactstrap';
import './rideDetail.scss';

const RideDetail = () => {
    const { rides_id } = useParams();
    const [rideDetail, setRideDetail] = useState();
    
    useEffect(() => {
        axios.get(`http://localhost:8080/test/rideDetail/${rides_id}`)
            .then(response => setRideDetail(response.data))
    },[rides_id]);
    
    console.log(rideDetail?.r_video);

    return (
    <div>
        <div className='ride-detail-wrapper'>
            <img src={rideDetail?.r_img} className='ride-tot-img col-md-7' alt='totImg'/>
            <List type='unstyled' className='ride-list-wrapper'>
                <li>
                    <img src={rideDetail?.r_img} className='ride-main-img' alt='mainImg'/>   
                </li>
                <div className='ride-second-wrapper'>
                    <li className='header-title' style={{
                        textAlign:'center', fontSize:'4rem', marginBottom:'20px', padding:'50px'}}>
                        <h1>{rideDetail?.r_name}</h1>
                    </li>
                    <li style={{textAlign:'center', marginBottom:'20px'}}>
                        <h5>{rideDetail?.r_info}</h5>
                    </li>
                    <li style={{textAlign:'center', marginBottom:'20px', padding: '20px'}}>
                        <h5>위치: {rideDetail?.r_location}</h5>
                    </li>
                </div>
            </List>
            <List type='unstyled' className='ride-list-wrapper'>
                <li>
                    <img src={rideDetail?.r_img} className='ride-main-img' alt='rideMainImg'/>   
                </li>
                <div className='ride-second-wrapper'>
                    <li className='header-title' style={{
                        textAlign:'center', fontSize:'4rem', marginBottom:'20px', padding:'50px'}}>
                        <h1>{rideDetail?.r_name}</h1>
                    </li>
                    <li style={{textAlign:'center', marginBottom:'20px'}}>
                        <h5>{rideDetail?.r_info}</h5>
                    </li>
                    <li style={{textAlign:'center', marginBottom:'20px', padding: '20px'}}>
                        <h5>위치: {rideDetail?.r_location}</h5>
                    </li>
                </div>
            </List>
        </div>
        <Container style={{marginTop:'100px'}}>
            <iframe id="inlineFrameExample"
                title="Inline Frame Example"
                width="100%"
                height="600px"
                allow="autoplay"
                src={rideDetail?.r_video}>
            </iframe>

            {/* 확장자가 달라서 안되는 듯 */}
            {/* <video
                autoPlay
                loop
                muted
                playsInline
                width="100%"
                height="600px"
            >
                <source src={rideDetail?.r_video} type='video/mp4'/>
            </video> */}

            {/* react-youtube 있다고 함 */}
        </Container>
    </div>
    );
};

export default RideDetail;