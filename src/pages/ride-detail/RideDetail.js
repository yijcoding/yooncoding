import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, List, Nav, NavItem } from 'reactstrap';
import './rideDetail.scss';

const RideDetail = () => {
    const { rides_id } = useParams();
    const [rideDetail, setRideDetail] = useState();
    
    useEffect(() => {
        axios.get(`http://localhost:8080/test/rideDetail/${rides_id}`)
            .then(response => setRideDetail(response.data))
    },[rides_id]);
    
    return (
        <div className='ride-detail-wrapper'>
            <img src={rideDetail?.r_img} className='ride-tot-img col-md-7'/>
            <List type='unstyled' className='ride-list-wrapper'>
                <li>
                    <img src={rideDetail?.r_img} className='ride-main-img'/>   
                </li>
                <div className='ride-second-wrapper'>
                    <li style={{textAlign:'center', marginBottom:'20px', padding:'50px'}}>
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
    );
};

export default RideDetail;