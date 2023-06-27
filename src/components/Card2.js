import 'bootstrap/dist/css/bootstrap.min.css';
import './card2.scss';  
import { useNavigate } from 'react-router-dom';
import { Card } from 'reactstrap';

export const Card2 = ({path, name, country, img, id, location, detail, backPage, info}) => {
  const navigate = useNavigate();

  return (
    path !== undefined
    ?
    <div className="col mb-5 card-wrapper" onClick={e => {
      e.preventDefault();
      navigate(`/${path}/${id}`);
    }}>
      <div className="card h-100 card-wrapper-2">
        <img className="card-img-top" src={img} alt="fac_img" />
        <div class="card-body p-4">
            <div class="text-center">
                <h5 class="fw-bolder">{name}</h5>
            </div>
        </div>
        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
            <div class="text-center">
              <h6 class="fw-bolder">{location}</h6>
              <p class="fw-bolder">{info}</p>
            </div>
        </div>
      </div>
    </div>
    : (
      detail !== undefined
      ? 
      <div className="col mb-5">
        <div class="card h-100">
          <img class="card-img-top" src={img} alt="fac_img" />
          <div class="card-body p-4">
              <div class="text-center">
                  <h5 class="fw-bolder">{name}</h5>
                  <h6 class="fw-bolder">{location}</h6>
              </div>
          </div>
          <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
              <div class="text-center">
                <button type='button' class="btn btn-outline-dark mt-auto" onClick={e => {
                  e.preventDefault();
                  detail(id);
                }}>Detail</button>
              </div>
          </div>
        </div>
      </div>
      :
      <div className="card-detail-wrapper col mb-5">
        <Card className='card-detail-wrapper'>
          <img className="card-img-top" src={img} alt="fac_img" />
          <div class="card-body p-4">
              <div class="text-center">
                  <h3 class="fw-bolder">{name}</h3>
              </div>
          </div>
          <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
              <div class="text-center">
                <p>{location}</p>
                <p>{info}</p>
              </div>
              <div class="text-center">
                <button type='button' class="btn btn-outline-dark mt-auto" onClick={e => {
                  e.preventDefault();
                  backPage(id)
                }}>Back</button>
              </div>
          </div>
        </Card>
      </div>
    )
  );
};