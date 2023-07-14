import React from 'react';
import './infoBar.scss';

import onlineIcon from '../../infoBarImage/onlineIcon.png';
import closeIcon from '../../infoBarImage/closeIcon.png';

const InfoBar = () => {
    return (
    <div className='infoBar'>
        <div className='leftInnerContainer'>
            <img className='onlineIcon' src={onlineIcon} alt='online icon' />
            <h3>room</h3>
        </div>
        <div className='rightInnerContainer'>
            <a href='/'>
            <img src={closeIcon} alt='close icon' />
            </a>
        </div>
    </div>
    );
};

export default InfoBar;