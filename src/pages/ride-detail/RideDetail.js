import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, List, Nav } from 'reactstrap';
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
                    <div className='header-title' 
                        style={{fontSize:'3.5rem', color:'grey'}}>
                        {rideDetail?.r_name}
                    </div>
                    
                </div>
            </List>
        </div>
        <Container>
        </Container>
    </div>


    // <Container style={{marginTop:'100px', display:'flex'}}>
    //     <div style={{width:'100%'}}>
    //         <iframe 
    //             id="inlineFrameExample"
    //             title="Inline Frame Example"
    //             width="1%"
    //             height="600px"
    //             allow="autoplay"
    //             style={{borderRadius:'20px'}}
    //             src={rideDetail?.r_video}>
    //         </iframe>

    //         {/* 확장자가 달라서 안되는 듯 */}
    //         {/* <video
    //             autoPlay
    //             loop
    //             muted
    //             playsInline
    //             width="100%"
    //             height="600px"
    //         >
    //             <source src={rideDetail?.r_video} type='video/mp4'/>
    //         </video> */}

    //         {/* react-youtube 있다고 함 */}
    //     </div>
    //     <div className='col-md-6'>
    //         <List style={{listStyle:'none'}}>
    //             <li style={{textAlign:'center', marginBottom:'20px'}}>
    //                 <h5>{rideDetail?.r_info}</h5>
    //             </li>
    //             <li style={{textAlign:'center', marginBottom:'20px', padding: '20px'}}>
    //                 <h5>위치: {rideDetail?.r_location}</h5>
    //             </li>
    //         </List>
    //     </div>
    // </Container>
    
    );
};

export default RideDetail;