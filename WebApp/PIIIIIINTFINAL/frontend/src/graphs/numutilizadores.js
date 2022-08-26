import axios from "axios";
import { useEffect, useState } from "react";
import {
    Card,
    Container,
    Row,
    Col,
    Image,
  } from "react-bootstrap";

const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function NumUtilizadores() {
    
    const [numUtil, setNumUtil] = useState();

    useEffect(() =>{
        axios.get( baseUrl + "/utilizadores")
        .then((nUtil) =>{
            setNumUtil(
                nUtil.data.length
            )
        })
        .catch((err) => {
            console.log(err)
        })
    }, [])

    return (
        <div>
            <Card.Body>
                <Row>
                  <Col xs="5">
                    <div className="icon-big text-center icon-warning">
                      <Image src="../img/user.png" rounded />
                    </div>
                  </Col>
                  <Col xs="7">
                    <div className="numbers">
                      <p className="card-category">Utilizadores registados</p>
                      <Card.Title as="h4">{numUtil}</Card.Title>
                    </div>
                  </Col>
                </Row>
              </Card.Body>
        </div>
    );
}