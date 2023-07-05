import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, List } from 'reactstrap';
import './rideDetail.scss';
import TabMenu from '../../components/TabMenu';

const RideDetail = () => {
    const { rides_id } = useParams();
    const [rideDetail, setRideDetail] = useState();

    useEffect(() => {
        axios.get(`http://localhost:8080/amusement/rideDetail/${rides_id}`)
            .then(response => setRideDetail(response.data))
    },[rides_id]);
    
    //console.log(rideDetail?.r_video);

    return (
    <div>
        <div className='ride-detail-wrapper'>
            <img src={rideDetail?.r_img} className='ride-tot-img col-md-7' alt='totImg'/>
            <List type='unstyled' className='ride-list-wrapper'>
                <li>
                    <img src={rideDetail?.r_img} className='ride-main-img' alt='mainImg'/>   
                </li>
                <div className='ride-second-wrapper'>
                    <div style={{fontSize:'2.5rem', color:'black', fontWeight:'bold'}}>
                        {rideDetail?.r_name}
                    </div>
                    
                </div>
            </List>
        </div>
        <Container>
            <br/>
            <TabMenu/>
        </Container>
    </div>
    );
};

export default RideDetail;