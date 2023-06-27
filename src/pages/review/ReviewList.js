import axios from 'axios';
import React, { useEffect, useLayoutEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';
import './reviewList.scss';
import { Container, Input, Table } from 'reactstrap';
import StarRating from '../../components/StarRating';

function Write(props){
    const [memberId, setMemberId] = useState("");
    const [content, setContent] = useState("");
    const [ratingNum, setRatingNum] = useState();

    const starRatingFunc = rating => {
        //console.log("rating", rating);
        setRatingNum(rating);
    }
    
    useEffect(()=>{
        console.log("ratingNum", ratingNum);
    },[ratingNum])

    return <Container className="px-4 px-lg-5 mt-5">
        <form onSubmit={e => {
        e.preventDefault();
        const memberId = e.target.memberId.value;
        const content = e.target.content.value;
        const amuse_id = Number(e.target.amuse_id.value);

        //console.log("ratingNum", ratingNum);
        //console.log(memberId, content, typeof(amuse_id));
        
        // post로 서버에 데이터 전달
        // 등록
        axios.post("http://localhost:8080/test/write", {
            amuse_id: amuse_id,
            member_id: memberId,
            r_content: content,
            r_grade: ratingNum
        },[]).then(result => props.onWrite());

        // props.onWrite();
    }}>
        <Input type="hidden" name="amuse_id" value={props.amuse_id}/>
        {/* star rating component */}
        <StarRating starRatingFunc={starRatingFunc}/>
        <br/>
        ID: <p><input 
                type="text"
                name="memberId"
                value={memberId}
                onChange={e => {
                    setMemberId(e.target.value);
                }}
                /></p>
        Review: <p><textarea 
                    type="text"
                    name="content"
                    value={content}
                    onChange={e => {
                        setContent(e.target.value);
                    }}
                    /></p>
        <input type='submit' value="Write"/>
        <input type='button' value="Close" onClick={e => {
            props.setMode("");
        }}/>
    </form></Container>
}

function reviewDelete(review_id){
    if(window.confirm("삭제합니까?")){
        axios.get(`http://localhost:8080/test/delete/${review_id}`,[])
    }
}

const ReviewList = () => {

    const {amuse_id} = useParams();
    const [review, setReview] = useState();
    const [mode, setMode] = useState("");

    useEffect(() => {
        axios.get(`http://localhost:8080/test/reviewList/${amuse_id}`)
            .then(response => setReview(response.data))
    },[]);

    let content = null;

    if(mode === ""){
        content = <button className="btn btn-outline-dark mt-auto" onClick={e => {
            e.preventDefault();
            setMode("WRITE");
        }}>Write</button>
    }
    else if(mode === "WRITE"){
        content = <Write setMode={setMode} amuse_id={amuse_id} onWrite={() => {
            setMode("")
        }}></Write>
    }

    return (
        <Container className="px-4 px-lg-5 mt-5">
            <header className='header-title'>후기</header>
            <Table className='table table-borderless text-center'>
                <thead className='border-bottom'>
                    <tr>
                        <th>ID</th>
                        <th>내용</th>
                        <th>등록일</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody className='border-bottom'>
                {review?.map(review => (
                    <tr key={review.review_id}>
                        <td>{review.member_id}</td>
                        <td>{review.r_content}</td>
                        <td>{review.r_regidate}</td>
                        <td>
                        <button type='button' className='btn btn-outline-dark mt-auto'
                            onClick={e => {
                                e.preventDefault();
                                reviewDelete(review.review_id);
                        }}>Delete</button>
                        <button type='button' className='btn btn-outline-dark mt-auto'
                            onClick={e => {
                                e.preventDefault();
                        }}>Answer</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
            {content}
        </Container>
    );
};

export default ReviewList;