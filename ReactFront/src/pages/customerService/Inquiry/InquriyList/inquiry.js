import { useEffect, useState } from "react";
import React from 'react';

import './inquiry.css'
import CustomerMove from "../../customerMove";
import InquiryList from "./component/inquiryList";


function Inquiry() {




    useEffect(() => {
        const requestOptions = {
            method: "GET",
            headers: { "Content-Type": "application.json" },
        };


    }, []);




    return (
        // <div>
        //     너 왜 아무것도 안뜸
        // </div>
        <div>
            <CustomerMove></CustomerMove>
            <section className="notice">
                <div id="board-list">
                    <div className="page-title">
                        <h1>문의내역</h1>
                    </div>
                    <br></br>
                    <div className="container">


<InquiryList></InquiryList>
                       

                    </div>
                </div>

            </section>
        </div>
    );
}


export default Inquiry;