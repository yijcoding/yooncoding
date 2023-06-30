import React from 'react';
import { Button } from 'reactstrap';

const PreviousPage = () => {
    const handleScroll = () => {
        //현재의 스크롤 양을 localStorage에 저장
        window.localStorage.setItem("scrollPosition", window.scrollY);
        console.log("현재 scrollY = ", window.scrollY);
    }
    return (
        <div>
            <Button onClick={() => {
                window.history.back()
            }}></Button>
        </div>
    );
};

export default PreviousPage;