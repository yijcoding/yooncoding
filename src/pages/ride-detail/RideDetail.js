import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const RideDetail = () => {

    // console.log("useParams", useParams());
    const { rides_id } = useParams();
    //console.log("rides_id", rides_id);

    const [RideDetail, setRideDetail] = useState();
    
    useEffect(() => {
        axios.get(`http://localhost:8080/test/rideDetail/${rides_id}`)
            .then(response => setRideDetail(response.data))
    },[]);
    
    return (
        <div>
            <h2>Hello my Ride</h2>
            <h3>{RideDetail?.r_name}</h3>
        </div>
    );
};

export default RideDetail;