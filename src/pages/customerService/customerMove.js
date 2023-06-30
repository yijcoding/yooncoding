import React from 'react';

function CustomerMove() {
    const Move = () => {
        let list = document.querySelector('.menu_select .option-list');

        //이거 상태관리로 값에 따라 보이고 안보이게 하셈
        if (list.style.overflow === 'hidden' || list.style.overflow === '') {
            list.style.overflow = 'visible';
        } else {
            list.style.overflow = 'hidden';
        }
    };

    const handleLinkClick = (link) => {
        window.location.href = link;
    };

    return (
        <div className="menu_select" onClick={Move}>
            <span onClick={() => handleLinkClick('/')} style={{ cursor: 'pointer' }}>
                <img src="https://cdn-icons-png.flaticon.com/512/20/20176.png" alt="1" />|
            </span>
            <div className="text">
                <span>
                    <b>공지사항</b>
                </span>
                <img src="https://cdn-icons-png.flaticon.com/512/3519/3519316.png" alt="1" />
                <ul className="option-list" onClick={Move}>
                    <li>
                        <a href="#" onClick={() => handleLinkClick('/announcement')}>
                            공지사항
                        </a>
                    </li>
                    <li>
                        <a href="#" onClick={() => handleLinkClick('/customer/faq?f_type=')}>
                            FAQ
                        </a>
                    </li>
                    <li>
                        <a href="#" onClick={() => handleLinkClick('/customer/announcementInquiry')}>
                            문의하기
                        </a>
                    </li>
                    <li>
                        <a href="#" onClick={() => handleLinkClick('/customer/consultationDetails')}>
                            상담내역
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default CustomerMove;