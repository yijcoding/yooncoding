import { useState, useEffect } from 'react';
import axios from 'axios';

function Favorite(props) {
    const { viewData } = props;
    const [favoriteData, setFavoriteData] = useState(null);


    useEffect(() => {
        const fetchData = async () => {
            await fetchFavoriteData();
        };
        fetchData();
    }, []);

    const fetchFavoriteData = async () => {

        await axios.get('/board/favoriteBoard', {
            params: {
                board_id: viewData.board_id
            }
        }).then(response => {
            setFavoriteData(response.data);
        }).catch(error => {
            console.log(error);
        });
    };

    if (favoriteData === null) {
        return <div>Loading...</div>;
    }


    const handleFavoriteEvent = rs => {
        // if (member_id === null) {
        //     alert('로그인해주세요!');
        // } else {
        axios
            .post('/board/favoriteBoard', {

                board_id: viewData.board_id,
                data: rs,
                member_id: "hong",
                favorite: viewData.favorite,
                hate: viewData.hate

            })
            .then(response => {
                fetchFavoriteData();
            })
            .catch(error => {
                console.log(error);
            });
        // }
    };

    return (
        <div className="favorite">
            <span>{favoriteData.favorite}</span>
            <span style={{ margin: '0 10px' }}></span>
            <span onClick={() => handleFavoriteEvent(1)}>
                <img src="https://cdn-icons-png.flaticon.com/512/4411/4411029.png" style={{ width: '75px' }} />
            </span>
            <span onClick={() => handleFavoriteEvent(2)}>
                <img src="https://cdn-icons-png.flaticon.com/512/4510/4510629.png" style={{ width: '75px' }} />
            </span>
            <span style={{ margin: '0 10px' }}></span>
            <span>{favoriteData.hate}</span>
        </div>
    );
}

export default Favorite;