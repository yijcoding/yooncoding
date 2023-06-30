import './uploadAnnouncement.css'
import { Container } from 'react-bootstrap';
import { useEffect, useRef, useState } from 'react';
import axios from 'axios';

import { useLocation } from 'react-router-dom';
import ImageUpload from './component/imageUpload';
import Ckeditor from '../../../../components/ckeditor';
import SelectType from './component/selectType';
import CustomerMove from '../../customerMove';


function UploadAnnouncement() {
    const ckeditorData = useRef("");
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [originBoardImg, setOriginBoardImg] = useState([]);
    const [useAnnouncement_num, setUseAnnouncement_num] = useState("");
    const [ImageCheck, setImageCheck] = useState("");
    //주소에서 값가져오기
    const location = useLocation();
    const urlSearch = new URLSearchParams(location.search)


    useEffect(() => {
        async function ready() {
            const detailData = await getDetailInfo();

            console.log(detailData)
            setTitle(detailData.c_title);
            setContent(detailData.c_content);
            setOriginBoardImg(detailData.boardImg)
        }

        ready();
    }, []);

    useEffect(() => {

        async function ready() {
            const detailData = await getDetailInfo();
            setOriginBoardImg(detailData.boardImg)
        }

        ready();
    }, [ImageCheck]);



    //기존 데이터
    const getDetailInfo = async () => {

        const announcement_num = urlSearch.get
            ("announcement_num")
        setUseAnnouncement_num(announcement_num);

        const response = await axios.get('http://localhost:8080/customer/updateAnnouncement', {
            params: {
                announcement_num: announcement_num
            }
        });

        console.log(response.data)
        const data = response.data
        return data

    }

    const fetchData = async () => {
        const announcement_num = useAnnouncement_num
        console.log('---------------------------------')
        console.log(announcement_num);
        console.log('---------------------------------')
        console.log(title.current);
        console.log(ckeditorData.current);
        console.log(selectedFiles);


        console.log(title)
        console.log(ckeditorData.current)

        if (title.length <= 3)
            window.alert("제목을 3글자 이상 입력해주세요!");
        else if (ckeditorData.current.length <= 5)
            window.alert("본문은 5글자 이상 입력해주세요!")
        else {

            try {
                // 텍스트 데이터 전송
                const response = await axios.post("http://localhost:8080/customer/updateAnnouncement", {
                    c_title: title,
                    c_content: ckeditorData.current,
                    announcement_num: announcement_num
                });

                const data = response.data;
                console.log(response.data);
                const boardId = data;  // 서버에서 생성된 게시물 ID를 기반으로 함. 적절한 Key로 대체해야 함.

                if (selectedFiles.length > 0) {
                    // 이미지 데이터 전송
                    const formData = new FormData();

                    // 이미지 파일 추가
                    selectedFiles.forEach((file) => {
                        formData.append("file", file);
                    });

                    // boardId와 함께 이미지 업로드 API에 전송
                    const imageResponse = await axios.post(`http://localhost:8080/customer/imageUpload/${announcement_num}`, formData, {
                        headers: {
                            "Content-Type": "multipart/form-data",
                        },
                    });

                    console.log(imageResponse);
                }

                window.location.href = `/announcementDetail?announcement_num=${announcement_num}`;
            } catch (error) {
                console.log("HTTP error");
                console.log(error);
            }
        }
    };


    //이전 게시물상세페이지로 돌아가기
    function cancle() {
        const announcement_num = useAnnouncement_num
        if (window.confirm("글 작성을 취소하시겠습니까?")) {
            window.location.href = `/announcementDetail?announcement_num=${announcement_num}`;
        }
    }

    return (
        <div className='create-board'>
            <Container>
                <CustomerMove />
                <SelectType
                    b_title={title}
                    setTitleValue={setTitle}
                />
                <hr />
                <Ckeditor
                    ckeditorData={ckeditorData}
                    //onImageUpload={handleImageUpload}
                    setContent={setContent}
                    content={content}
                />

                <ImageUpload
                    selectedFiles={selectedFiles}
                    setSelectedFiles={setSelectedFiles}
                    originBoardImg={originBoardImg}
                    setOriginBoardImg={setOriginBoardImg}
                    announcement_num={useAnnouncement_num}
                    setImageCheck={setImageCheck}
                />


                <br />


                <div id="insert-btn-wrap">
                    <input type="button" id="cancle-btn" className="btn btn-blue" onClick={cancle} value="취소" />
                    <input type="submit" className="btn btn-blue" id="submit" onClick={fetchData} value="확인" />
                </div>
            </Container>
        </div>
    );
}

export default UploadAnnouncement;