import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { Card2 } from '../../components/Card2';
import { useParams } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from 'reactstrap';

const FacList = () => {
    const { amuse_id } = useParams();
    const [facList, setFacList] = useState();
    const [facId, setFacId] = useState(null);

    const facDetail = id => {
        setFacId(id);
    }

    const facBackPage = id => {
        console.log("back", id);
    }

    useEffect(() => {
        axios.get(`http://localhost:8080/test/facilityList/${amuse_id}`)
            .then(response => setFacList(response.data));
        console.log("facId", facId);
    },[facId]);

    
    
    if(facId === null){
        return (
            <Container class="px-4 px-lg-5 mt-5">
                <section class="py-5">
                    <header class='header-title'>편의시설</header>
                    <div class="container px-4 px-lg-5 mt-5">
                        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 
                                    row-cols-xl-4 justify-content-center">
                            {facList?.map(fac => (
                                <Card2 key={fac.facility_id} id={fac.facility_id} location={fac.f_location} 
                                    name={fac.f_name} img={fac.f_img} detail={facDetail}/>
                            ))}
                        </div>
                    </div>
                </section>
            </Container>
        );
    }
    else{
        return (
            <Container class="px-4 px-lg-5 mt-5">
                <section class="py-5">
                    <header class='header-title'>편의시설</header>
                    <div class="container px-4 px-lg-5 mt-5">
                        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 
                                    row-cols-xl-4 justify-content-center">
                            {facList?.map(fac => {
                                if(facId === fac.facility_id){
                                    return(
                                        <Card2 key={fac.facility_id} info={fac.f_info}
                                            name={fac.f_name} img={fac.f_img} backPage={facBackPage}/>
                                    );
                                }
                            })}
                        </div>
                    </div>
                </section>
            </Container>
        );
    }
};

export default FacList;
