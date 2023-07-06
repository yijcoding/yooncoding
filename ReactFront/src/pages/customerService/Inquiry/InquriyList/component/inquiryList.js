import { useEffect, useState } from "react";
import axios from "axios";
import { Link, useLocation } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import PagingSearch from "./pagingSearch";




function InquiryList() {
  const [data, setData] = useState([]);
  const location = useLocation();




  useEffect(() => {
    const addressParams = new URLSearchParams(location.search);
    const PageNum = addressParams.get("pageNum")
    getBoardList(PageNum);

  }, [location]);


  const getBoardList = (PageNum) => {


    console.log(location.search);
    // const searchParams = new URLSearchParams(location.search);

    // //탭으로 분류
    // const b_typeVaule = searchParams.get("b_type");
    // let pageCount = searchParams.get("viewCnt");
    // console.log(pageCount)

    // if (pageCount == null) {
    //   pageCount = 10;
    //   console.log(pageCount)
    // }
    // console.log(pageCount)
    console.log(PageNum)
    const pageCount = 10;
    if(PageNum ===null){
      PageNum=1
    }
      console.log(PageNum)
    const requestOptions = {
      method: 'GET',
      url: 'http://localhost:8080/customer/consultationDetails',
      headers: {
        'Content-Type': 'application/json'
      },

      params: {
        viewCnt: pageCount,
        pageNum: PageNum,
      }

    };

    const fetchData2 = async () => {
      try {
        const response = await axios(requestOptions);
        const data = response.data;
        console.log(data)
        setData(data);
      } catch (error) {
        console.log("http error");
        console.log(error);
      }

    };
    fetchData2();
  }



  return (
    <div>
      <table className="board-table">
        <thead>
          <tr>
            <th scope="col" className="th-num" style={{ width: '5%' }}>번호</th>
            <th scope="col" className="th-num" style={{ width: '10%' }}>분류</th>
            <th scope="col" className="th-title" style={{ width: '35%' }}>제목</th>
            <th scope="col" className="th-title" style={{ width: '20%' }}>제목</th>
            <th scope="col" className="th-date" style={{ width: '30%' }}>등록일</th>
          </tr>
        </thead>

        <tbody id="boardList">
          {/* 합쳐진 데이터를 렌더링하는 부분 */}
          {/* 각 필드에 대한 데이터를 출력 */}
          {
            data.map((item, index) => (
              item.inquiry_num && (
                <tr key={index}>
                  <td>{item.inquiry_num}</td>
                  <td>{item.b_type}</td>
                  <td><Link to={`/inquiryDetail?inquiry_num=${item.inquiry_num}`}>{item.b_title}</Link></td>
                  <td>{item.member_id}</td>
                  <td>{item.postdate}</td>
                </tr>
              )
            ))
          }
        </tbody>
      </table>

      <PagingSearch
        data={data}
        setData={setData}
      />

    </div>
  );
}


export default InquiryList;