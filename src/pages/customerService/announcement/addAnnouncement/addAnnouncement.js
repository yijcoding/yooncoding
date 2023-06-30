import './addAnnouncement.css'
import { Container } from 'react-bootstrap';
import SelectType from './component/selectType';
import { useRef, useState } from 'react';
import axios from 'axios';

import ImageUpload from './component/imageUpload';
import Ckeditor from '../../../../components/ckeditor';
import CustomerMove from '../../customerMove';

function AddAnnouncement() {
    const titleInputRef = useRef("");
    const ckeditorData = useRef("");
    const uploadedImages = useRef([]);
    const [content, setContent] = useState(undefined);
    const [selectedFiles, setSelectedFiles] = useState([]);


    const handleValueChange = (slValue) => {

        console.log(slValue);
    };

    const handleTitleValueChange = (TitleValue) => {
        titleInputRef.current = TitleValue;
    };



    const fetchData = async () => {
        console.log(titleInputRef.current);
        console.log(ckeditorData.current);
        console.log(uploadedImages.current);

        if (titleInputRef.current.length <= 3)
            window.alert("제목을 3글자 이상 입력해주세요!");
        else {


            try {
                // 텍스트 데이터 전송
                const response = await axios.post("http://localhost:8080/customer/insertAnnouncement", {
                    c_title: titleInputRef.current,
                    c_content: ckeditorData.current,
                });

                const data = response.data;
                console.log(response.data);
                const announcement_num = data;  // 서버에서 생성된 게시물 ID를 기반으로 함. 적절한 Key로 대체해야 함.

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

                window.location.href = "/announcement";
            } catch (error) {
                console.log("HTTP error");
                console.log(error);
            }
        }
    };


    function cancle() {
        if (window.confirm("글 작성을 취소하시겠습니까?")) {
            window.location.href = "/announcement";
        }
    }

    return (
        <div className='create-board'>
            <Container>
                <CustomerMove></CustomerMove>
                <SelectType
                    onValueChange={handleValueChange}
                    inputRef={titleInputRef}
                    setTitleValue={handleTitleValueChange}
                />
                <hr />
                <Ckeditor
                    ckeditorData={ckeditorData}
                    //onImageUpload={handleImageUpload}
                    content={content}
                    setContent={setContent}
                />

                <ImageUpload
                    selectedFiles={selectedFiles}
                    setSelectedFiles={setSelectedFiles}
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

export default AddAnnouncement;