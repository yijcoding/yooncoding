import { useEffect, useState } from "react";
import React from 'react';

import './announcement.css'
import AnnouncementList from "./component/announcementList";
import CustomerMove from "../../customerMove";

function Announcement() {




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
                <div className="board-list">
                    <div className="page-title">
                        <h1>공지사항</h1>
                    </div>
                    <br></br>
                    <div className="container">



                        <AnnouncementList></AnnouncementList>

                    </div>
                </div>

            </section>
        </div>
    );
}


export default Announcement;