import axios from 'axios';
import React, { useEffect, useLayoutEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';
import './reviewList.scss';
import { Button, CloseButton, Container, Input, Table } from 'reactstrap';
import StarRating from '../../components/StarRating';

//글 작성
function Write(props){
    const [memberId, setMemberId] = useState("");
    const [content, setContent] = useState("");
    const [ratingNum, setRatingNum] = useState();

    const starRatingFunc = rating => {
        //console.log("rating", rating);
        setRatingNum(rating);
    }
    
    // useEffect(()=>{
    //     console.log("ratingNum", ratingNum);
    // },[ratingNum])

    return <Container className="px-4 px-lg-5 mt-5">
        <form onSubmit={e => {
        e.preventDefault();
        const memberId = e.target.memberId.value;
        const content = e.target.content.value;
        const amuse_id = Number(props.amuse_id);

        // post로 서버에 데이터 전달
        // 등록
        axios.post("http://localhost:8080/test/write", {
            amuse_id: amuse_id,
            member_id: memberId,
            r_content: content,
            r_grade: ratingNum
        },[]).then(result => props.onWrite());
    }}>
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

//답글 처리
function Answer(props){
    const [content, setContent] = useState("");

    return <form 
        onSubmit={e => {
        e.preventDefault();
        const memberId = "hong1";
        const content = e.target.content.value;
        const amuse_id = Number(props.amuse_id);
        const review_id = Number(props.review_id);

        // console.log("content = ", content, " amuse_id = ", amuse_id);
        // console.log("review_id = ", props.review_id);

        axios.post("http://localhost:8080/test/answer", {
            amuse_id: amuse_id,
            member_id: memberId,
            r_content: " ㄴ " + content,
            review_id: review_id
        },[]).then(result => props.onAnswer());

        //props.onAnswer();
    }}>
        <br/>
        Answer: <p><textarea 
                    type="text"
                    name="content"
                    value={content}
                    onChange={e => {
                        setContent(e.target.value);
                    }}
                    /></p>
        <input type='submit' value="Answer"/>
        <input type='button' value="Close" onClick={e => {
            props.setMode("");
        }}/>
    </form>
}

function reviewDelete(review_id, {setMode}){
    if(window.confirm("삭제합니까?")){
        axios.get(`http://localhost:8080/test/delete/${review_id}`,[])
        setMode("");
    }
}

const ReviewList = () => {

    const {amuse_id} = useParams();
    const [review, setReview] = useState();
    const [mode, setMode] = useState("");
    const [review_id, setReview_id] = useState();

    //글, 답글 작성 및 삭제할 때에도 다시 렌더링 되도록 하는 방법???
    useEffect(() => {
        axios.get(`http://localhost:8080/test/reviewList/${amuse_id}`)
            .then(response => setReview(response.data))
    },[review_id, mode, amuse_id]);

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
    else if(mode === "ANSWER"){
        content = <Answer setMode={setMode} amuse_id={amuse_id} review_id={review_id} 
                    onAnswer={() => {
                        setMode("");
                    }}>
        </Answer>
    }

    const handleAnswerClick = review_id => {
        console.log("button으로 받아온 review_id = ", review_id);
        setReview_id(review_id);
        setMode("ANSWER");
    }

    return (
        <Container className="mt-5">
            <header className='header-title'>후기</header>
            <Table className='table-borderless text-center'>
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
                        <Button type='button' className='mt-auto'
                            onClick={e => {
                                e.preventDefault();
                                alert(review.review_id);
                                reviewDelete(review.review_id, {setMode});
                        }}>Delete</Button>&nbsp;
                        <Button type='button' className='mt-auto'
                            onClick={e => {
                                handleAnswerClick(review.review_id);

                                //첫번째 버튼과 두번째 버튼 비교
                                //왜 첫번째 버튼에서는 이렇게 하면 review_id 받을 수 있고
                                //왜 두번째 버튼에서는 이렇게 하면 못 받는지!!
                                //alert(review.review_id);
                                //setMode("ANSWER");
                        }}>Answer</Button>
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