import { useEffect, useState } from "react";
import CustomerMove from "../../customerMove";
import { useLocation } from "react-router-dom";
import axios from "axios";

function InquiryDetail() {
  const [viewData, setViewData] = useState([]);
  const [imageData, setImageData] = useState();
  const [isLoading, setIsLoading] = useState(true);
  const location = useLocation();
  const addressParams = new URLSearchParams(location.search);

  useEffect(() => {
     getData();
  }, []);

  const getData = async() => {
    const inquiry_num = addressParams.get("inquiry_num");

    await axios
      .get("http://localhost:8080/customer/consultationView", {
        params: {
          inquiry_num: inquiry_num,
        },
      })
      .then((response) => {
        console.log(response.data[0]);
        setViewData(response.data)
        setIsLoading(false); // 데이터 로드 완료
      });
  };

  const backToThe = () => {
    window.location.href = "/inquiry";
  };

  return (
    // <>
    // <div>1111111111</div></>
    <div className="wrap">
      <div id="view-wrap">
      {isLoading ? (
        <div>데이터를 불러오는 중...</div>
      ) :(
        <>
        <div className="customerMove">
          <CustomerMove></CustomerMove>
        </div>
        {/* 상단 */}
        <div className="header">
          <div className="header-inner">
            <p style={{ fontSize: "20px", float: "left" }}>
              <b>{viewData[0] && viewData[0].b_title}</b>
            </p>
            <div className="heder-inner-inner" style={{ clear: "both" }}>
              <br />
              <span
                style={{
                  marginBottom: "10px",
                  fontSize: "15px",
                }}
              >
                <a href={viewData[0] ? `/inquiry?member_id=${viewData[0].member_id}` : "#"}>
  {viewData[0] && viewData[0].member_id}
</a>
              </span>
              <span style={{ float: "right" }}>{viewData[0] &&viewData[0].postdate}</span>
            </div>
          </div>
        </div>
        <hr />
        <br />
        <div className="main-content">
          <div className="image-wrap">
          {viewData[0] &&viewData[1].map((img, index) => (
              <img key={index} src={img} alt="1"></img>
            ))}
          </div>
          <div className="content">
            <div className="content-inner">
              <div
                style={{
                  height: "100%",
                  minHeight: "400px",
                  background: "#eeeeee",
                  padding: "10px",
                }}
              >
                {viewData[0] &&viewData[0].b_content}
              </div>
            </div>
          </div>

          {/* 답변일 없을시 */}
          {viewData[0] && viewData[0].cnt === 1 && (
            <div>
              
              
                <p>관리자 답변</p>
                <textarea
                  rows="10"
                  name="b_content"
                  id="adminAnswerContent"
                  style={{ width: "100%" }}
                ></textarea>
                <input type="button" value="관리자 답변" />
                <input type="hidden" name="inquiry_num" value={viewData[0] &&viewData[0].inquiry_num} />
                <input type="hidden" name="b_title" value={viewData[0] && viewData[0].b_title} />
              
            </div>
          )}

          {/* 글 삭제 (관리자) */}
          <div className="view-btn">
            <input
              type="button"
              className="btn btn-blue"
              id="board_delete"
              value="삭제"
            />
          </div>

          <div className="list-wrap">
            <input
              type="button"
              className=""
              id="listback"
              onClick={backToThe}
              value="목록"
            />
          </div>
          <hr style={{ border: "0" }} />
          <br />
        </div>
        </>
      )}
      </div>
    </div>
  );
}

export default InquiryDetail;