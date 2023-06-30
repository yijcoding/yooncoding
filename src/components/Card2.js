import 'bootstrap/dist/css/bootstrap.min.css';
import './card2.scss';  
import { useNavigate } from 'react-router-dom';
import { Button, Card, CardBody, CardFooter, Col } from 'reactstrap';

export const Card2 = ({path, name, country, img, id, location, btnDetail, btnBack, info}) => {
  const navigate = useNavigate();

  return (
    path !== undefined
    ?
    <Col className="mb-5 main-wrapper-1" onClick={e => {
      e.preventDefault();
      navigate(`/${path}/${id}`);
    }}>
      <Card className="card-wrapper">
          <img className='card-main-img' src={img} alt="fac_img"/>
        <CardBody className="card-body p-4">
            <div className="text-center">
                <h5 className="fw-bolder">{name}</h5>
            </div><hr/>
        </CardBody>
        <CardFooter className="p-4 pt-0 border-top-0 bg-transparent">
            <div className="text-center">
              <h6 className="fw-bolder">{location}</h6>
              <p className="fw-bolder">{info}</p>
            </div>
        </CardFooter>
      </Card>
    </Col>
    : (
      btnDetail !== undefined
      ? 
      <Col className="main-wrapper-2">
        <Card className="card-wrapper h-100">
          <img className="card-main-img" src={img} alt="fac_img" />
          <CardBody className="card-body p-4">
              <div className="text-center">
                  <h5 className="fw-bolder">{name}</h5>
              </div><hr/>
              <div className="text-center">
                  <h6 className="fw-bolder">{location}</h6>
              </div>
          </CardBody>
          <CardFooter className="p-4 pt-0 border-top-0 bg-transparent">
            <div className="btn-wrapper">
              <Button type='button' className='btn' onClick={e => {
                e.preventDefault();
                btnDetail(id);
              }}>Detail</Button>
            </div>
          </CardFooter>
        </Card>
      </Col>
      :
      <Col className="card-detail-wrapper mb-5">
        <Card className="card-wrapper h-100">
          <img className="card-main-img" src={img} alt="fac_img" />
          <CardBody className="p-4">
            <div className="text-center"><br/>
                <h3 className="fw-bolder">{name}</h3>
            </div><br/>
            <div className="text-center">
              <p>위치: {location}</p>
              <p>{info}</p>
            </div>
          </CardBody>
          <CardFooter className="p-4 pt-0 border-top-0 bg-transparent">
            <div className="btn-wrapper">
              <Button type='button' className="btn" 
                onClick={e => {
                  e.preventDefault();
                  // window.location.replace(`/amusement/${amuse_id}`);
                  btnBack();
              }}>Back</Button>
            </div>
          </CardFooter>
        </Card>
      </Col>
    )
  );
};