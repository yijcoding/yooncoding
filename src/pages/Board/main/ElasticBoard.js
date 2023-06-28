import React, { useEffect, useRef, useState } from 'react';
//import './ElasticBoard.css'
import './board.css'
import $ from 'jquery'
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from 'react-bootstrap';

function ElasticBoard() {

    const [pageSize, setPageSize] = useState(undefined);
    const [currentPage, setCurrentPage] = useState(1);
    const [pagevalue, setPagevalue] = useState(100);
    const [elasticResults, setElasticResults] = useState([]);
    const [searchValue, setSearchValue] = useState("");



    // 페이지 사이즈 업데이트 함수
    useEffect(() => {
        elastick(currentPage);
        //post_elastick(currentPage)
    }, [currentPage, pageSize]);

    const updatePageSize = (pagingEvent) => {
        console.log("너 없냐" + pagingEvent)
        setPageSize(pagingEvent.target.value);
        console.log('pagingEvent=======' + pagingEvent)
        elastick(currentPage);
    }

    //post_elastick(currentPage)

    // function handlePageChange(newPage) {
    //     setCurrentPage(newPage);
    //     elastick(newPage);
    // }

    const elastick = async (currentPage) => {
        let searchValue = document.getElementById("text").value;
        let selectField = document.querySelector('select[name=selectField]').value;


        console.log("selectField: " + selectField);
        console.log("searchValue: " + searchValue);
        console.log("currentPage: " + currentPage)
        console.log("pageSize: " + pageSize);



        if (pageSize === undefined) {
            setPageSize(10);
            console.log("111111111111111111111");
            const query = {
                "from": 0,
                "size": 10,
                "query": {
                    "match_all": {}
                }
            };
            getElasticBoardList(query);
        } else {
            console.log("22222222222222222222");
            const query = {
                "from": ((currentPage - 1) * pageSize), // buttonvalue*pageSize 이렇게 해라
                "size": pageSize,
                "sort": [
                    {
                        "board_id": {
                            "order": "desc"
                        }
                    }
                ],
                "query": {
                    "wildcard": {
                        [selectField]: {
                            "value": `*${searchValue}*`
                        }
                    }
                }
            };
            getElasticBoardList(query);
        }
        // const query1 = {
        //     "query": {
        //         "wildcard": {
        //             [selectField]: {
        //                 "value": `*${'${searchValue}'}*`
        //             }
        //         }
        //     }
        // };
    };


    const getElasticBoardList = async (query) => {

        const sel = [query];

        const url = 'http://localhost:9200/board_index/_search';



        const headers = { 'Content-Type': 'application/json' };
        const data = JSON.stringify(sel[0]);
        const requestOption = {
            method: 'POST',
            url: url,
            headers: headers,
            data: data
        };


        await axios(requestOption).then(response => {
            let pagevalue = response.data.hits.total.value;
            let countpages = Math.ceil(pagevalue / pageSize);
            console.log('------------------------------------')
            console.log(pageSize);
            console.log(pagevalue);
            console.log('------------------------------------')


            setPagevalue(pagevalue);
            console.log(response);
            console.log("pagevalue===" + pagevalue);
            console.log("countpages===" + countpages);
            const data = response.data.hits.hits;
            console.log(data)
            setElasticResults(data);
        }).catch(error => {
            console.log(error);
        });
    }







    // const post_elastick = async (currentPage) => {
    //     console.log('post')

    //     const query = {
    //         "from": 0,
    //         "size": 10,
    //         "query": {
    //             "match_all": {}
    //         }
    //     };
    //     const sel = [query];

    //     const url = 'http://localhost:9200/board_index/_search';

    //     const headers = { 'Content-Type': 'application/json' };
    //     const data = JSON.stringify(sel[0]);
    //     const requestOption = {
    //         method: 'POST',
    //         url: url,
    //         headers: headers,
    //         data: data
    //     };

    //     await axios(requestOption).then(response => {
    //         let pagevalue = response.data.hits.total.value;
    //         let countpages = Math.ceil(pagevalue / pageSize);
    //         setPagevalue(pagevalue);
    //         console.log(response);
    //         console.log("pagevalue===" + pagevalue);
    //         console.log("countpages===" + countpages);
    //         const data = response.data.hits.hits;
    //         console.log(data)
    //         setElasticResults(data);
    //     }).catch(error => {
    //         console.log(error);
    //     });


    // $.ajax({

    //     success: function (data) {
    //         let pagevalue = response.data.hits.total.value; // 게시물 갯수

    //         let countpages = Math.ceil(pagevalue / pageSize); // 개시물수를 페이지사이즈만큼 나눈다음 올림


    //         setPagevalue(pagevalue); //게시물 갯수


    //         console.log(data);
    //         console.log("pagevalue===" + pagevalue);
    //         console.log("countpages===" + countpages);

    //         let tableHTML = "";

    //         for (const hit of data.hits.hits) {
    //             const source = hit._source;
    //             const member_id = source.member_id;
    //             const postdate = source.postdate;
    //             const ymd = postdate.substring(0, 10);
    //             const ymd2 = ymd.replaceAll("-", ".");

    //             tableHTML += "<tr>";
    //             tableHTML += "<td>" + hit._id + "</td>";
    //             tableHTML += "<td>" + hit._source.b_type + "</td>";
    //             tableHTML += "<td style={{textAlign:'center',padding:'0 0 0 10px'><a href='/board/view?board_id=" + hit._id + "'>" + hit._source.b_title + "</a></td>";
    //             tableHTML += "<td>" + member_id + "</td>";
    //             tableHTML += "<td>" + ymd2 + "</td>";
    //             tableHTML += "<td>" + hit._source.favorite + "</td>";
    //             tableHTML += "<td>" + hit._source.visitcount + "</td>";
    //             tableHTML += "</tr>";
    //         }

    //         // 여기에서 table 요소에 테이블 HTML을 추가합니다.
    //         $('#elasti').html(tableHTML);
    //         paging(pagevalue, currentPage, pageSize);
    //     }
    // });
    //};


    function paging(pagevalue, currentPage, pageSize) {
        const buttons = [];
        $('.page_test').html("");
        let lowpage = 1;
        let maxpage = 10;

        lowpage = Math.ceil(pagevalue / pageSize);

        const pagegroup = Math.ceil(currentPage / maxpage);

        let last = pagegroup * pageSize;
        if (last > lowpage) {
            last = lowpage;
        }
        let first = last - (pageSize - 1); // 화면에 보여질 첫번째 페이지 번호
        const next = last + 1;
        const prev = first - 1;

        if (lowpage < 1) {
            first = last;
        }


        if (first > 10) {
            buttons.push(
                <button className='pagination-button' value={prev} key={prev}>Prev</button>
            );
        }
        for (let j = first; j <= last; j++) {
            if (currentPage === (j)) {
                buttons.push(
                    <button className='pagination-button' value={j} key={j}>{j}</button>
                );
            } else if (j > 0) {
                buttons.push(
                    <button className='pagination-button' value={j} key={j}>{j}</button>
                );
            }
        }
        if (next > 10 && next < lowpage) {
            buttons.push(
                <button className='pagination-button' value={next} key={next}>next</button>
            );
        }
        console.log(buttons)
        return buttons;
    }


    const tabEvent = [
        { name: "전체", id: "li-0", link: "0" },
        { name: "국내", id: "li-1", link: "1" },
        { name: "해외", id: "li-2", link: "2" },
        { name: "질문", id: "li-3", link: "3" },
        { name: "자유", id: "li-4", link: "4" }
    ]


    const pagecount = [

        { name: "10", id: "select-1", value: "10" },
        { name: "30", id: "select-2", value: "30" },
        { name: "50", id: "select-3", value: "50" },
    ]

    const handleSearchChange = (event) => {
        setSearchValue(event.target.value);
    }

    return (
        <>
            <section className="notice">
                {/* board list area */}
                <div id="board-list" style={{ clear: "both" }}>
                    <div className="page-title" style={{ textAlign: 'center' }}>
                        <h1>게시판</h1>
                    </div>


                    <div className='container'>
                        <div className="write_from_wrap">

                            {/* tabs menu */}
                            <div>
                                <ul className="tabs">
                                    <li className="tab-link current" id="li-0"><a
                                        href="/board?b_type=0">전체</a></li>
                                    <li className="tab-link" id="li-1"><a
                                        href="/board?b_type=1">국내</a></li>
                                    <li className="tab-link" id="li-2"><a
                                        href="/board?b_type=2">해외</a></li>
                                    <li className="tab-link" id="li-3"><a
                                        href="/board?b_type=3">질문</a></li>
                                    <li className="tab-link" id="li-4"><a
                                        href="/board?b_type=4">잡담</a></li>
                                </ul>
                            </div>

                            {/* 글작성 버튼 */}
                            <div className="write_form">
                                <button type="submit" id="write-top" className="btn-board-top" >
                                    <img
                                        src="https://cdn-icons-png.flaticon.com/512/5218/5218705.png"
                                        alt="write-top"
                                        style={{ width: '15px', top: '5px' }} />
                                    글쓰기
                                </button>
                            </div>
                        </div>

                        {/* 글 출력수량 설정 */}
                        <div className="menu_select">
                            <div className='text'>
                                <select className="form-select form-select-sm" id="view-select" aria-label="form-select-sm example" onChange={updatePageSize} >
                                    {pagecount.map((sl) => {
                                        return (<option key={sl.id} id={sl.id} value={sl.value}>
                                            {sl.name}
                                        </option>
                                        )
                                    })}
                                </select>
                            </div>
                        </div>

                        <table className="board-table">
                            <thead>
                                <tr>
                                    <th scope="col" className="th-num" style={{ width: '5%' }} > 번호</th>
                                    <th scope="col" className="th-num" style={{ width: '10%' }}>분류</th>
                                    <th scope="col" className="th-title" style={{ width: '30%' }}>제목</th>
                                    <th scope="col" className="th-member" style={{ width: '10%' }}>작성자</th>
                                    <th scope="col" className="th-date" style={{ width: '30%' }}>등록일</th>
                                    <th scope="col" className="th-date" style={{ width: '5%' }}>추천수</th>
                                    <th scope="col" className="th-date" style={{ width: '5%' }}>조회수</th>
                                </tr>
                            </thead>
                            <tbody id="elasti">
                                {elasticResults.map((hit) => {

                                    let source = hit._source;
                                    let member_id = source.member_id;
                                    let postdate = source.postdate;

                                    let ymd = postdate ? postdate.substring(0, 10) + ' ' + postdate.substring(14, 19) : "";
                                    let ymd2 = ymd.replaceAll("-", ".");

                                    return (
                                        <tr key={hit._id}>
                                            <td>{hit._id}</td>
                                            <td>{hit._source.b_type}</td>
                                            <td style={{ textAlign: "center", padding: "0 0 0 10px" }}>
                                                <a href={"/detail?board_id=" + hit._id}>{hit._source.b_title}</a>
                                            </td>
                                            <td>{member_id}</td>
                                            <td>{ymd2}</td>
                                            <td>{hit._source.favorite}</td>
                                            <td>{hit._source.visitcount}</td>
                                        </tr>
                                    );

                                })}
                            </tbody>
                        </table>

                        { /* board paging start */}

                        { /* board paging end */}

                    </div>
                </div>
                <div style={{ clear: 'both' }}></div>

                { /* board search area */}
                <div id="board-search">
                    <div className='container'>
                        <div className='search-window'>
                            <div className='search-wrap'>
                                <select
                                    name="selectField">
                                    <option value="b_title">제목</option>
                                    <option value="b_content">내용</option>
                                    <option value="member_id">작성자</option>
                                </select>
                                <label htmlFor="search" className="blind">검색</label>
                                <input
                                    id="text"
                                    type="search"
                                    name="search"
                                    value={searchValue}
                                    onChange={handleSearchChange} />
                                <button
                                    type="button"
                                    id="searchButton"
                                    className="btn btn-search"
                                    onClick={() => elastick(currentPage)}>
                                    검색
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
            </section >
        </>
    );
}


export default ElasticBoard;