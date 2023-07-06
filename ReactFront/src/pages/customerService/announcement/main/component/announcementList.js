import { useEffect, useRef, useState } from "react";
import $ from 'jquery';
import axios from "axios";
import { Link, useLocation } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import PagingSearch from "./pagingSearch";




function AnnouncementList() {
  const [data, setData] = useState([]);
  const [pageNumber, setPageNumer] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const searchData = useRef("");
  const location = useLocation();





  useEffect(() => {
    const addressParams = new URLSearchParams(location.search);
    let PageNum = addressParams.get("pageNum")
    if (PageNum === null || PageNum === undefined) {
      PageNum = 1;
    }
    setPageNumer(PageNum)
    getBoardList(PageNum);

  }, []);


  const getBoardList = (PageNum) => {


    console.log(location.search);
    console.log(PageNum)
    const pageCount = 10;

    if (PageNum === null || PageNum === undefined) {
      PageNum = 1;
      console.log("11111111111111")
    }


    const fetchData2 = async () => {

      await axios.get('http://localhost:8080/customer/announcement', {
        params: {
          pageNum: PageNum,
        }
      }).then((response) => {
        const data = response.data;
        console.log(response)
        console.log(data)
        setTotalPages(data.totalPages)
        setData(data.content);
      }).catch(error => {
        console.log("http error");
        console.log(error);
      });

    };
    fetchData2();
  }

  const conversionPostdate = (item) => {
    let postdate = item.postdate;
    let formattedPostDate = postdate.replace("T", " ").substring(0, 16).replace(/-/g, ".");

    return (
      <td>{formattedPostDate}</td>
    );
  }

  return (
    <div>
      <table className="board-table">
        <thead>
          <tr>
            <th scope="col" className="th-num" style={{ width: '5%' }}>번호</th>
            <th scope="col" className="th-num" style={{ width: '15%' }}>분류</th>
            <th scope="col" className="th-title" style={{ width: '25%' }}>제목</th>
            <th scope="col" className="th-date" style={{ width: '30%' }}>등록일</th>
          </tr>
        </thead>

        <tbody id="boardList">
          {/* 합쳐진 데이터를 렌더링하는 부분 */}
          {/* 각 필드에 대한 데이터를 출력 */}
          {
            data.map((item, index) => (
              item.announcement_num && (
                <tr key={index}>
                  <td>{item.announcement_num}</td>
                  <td>{item.c_type}</td>
                  <td><Link to={`/announcementDetail?announcement_num=${item.announcement_num}`}>{item.c_title}</Link></td>
                  {conversionPostdate(item)}
                </tr>
              )
            ))
          }
        </tbody>
      </table>

      <PagingSearch
        data={data}
        pageNumber={pageNumber}
        totalPages={totalPages}
        searchData={searchData}
      />

    </div>
  );
}


export default AnnouncementList;