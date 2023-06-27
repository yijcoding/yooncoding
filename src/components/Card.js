import "./card.scss";
import { useNavigate } from "react-router-dom";

export const Card = ({path, name, a_country, img, id, location}) => {
  const navigate = useNavigate();
  return (
    path !== undefined
    ? <div className="card-wrapper" onClick={e => {
      e.preventDefault();
      navigate(`/${path}/${id}`)
    }}>
      <div className="card-body-img">
        <img src={img} alt="img"/>
      </div>
      <div className="card-body-text">
        <div className="card-body-text-title">{name}</div>
      </div>
      <div className="card-footer">
        <div className="card-body-text-content row">
          {a_country}
          {location}
        </div>
      </div>
    </div>
    : <div className="card-wrapper" onClick={e => {
        e.preventDefault();
        alert("")
    }}>
      <div className="card-body-img">
        <img src={img} alt="img"/>
      </div>
      <div className="card-body-text">
        <div className="card-body-text-title">{name}</div>
      </div>
      <div className="card-footer">
        <div className="card-body-text-content">
          {a_country}
          {location}
        </div>
      </div>
    </div>
  );
};