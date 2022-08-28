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

  useEffect(() => {
    axios.get(baseUrl + "/utilizadores")
      .then((nUtil) => {
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
      <div className="col" style={{ textDecoration: "none", color: "black" }}>
        <div className="card">
          <div className="card-body">
            <div className="card-title">
              Utilizadores Registados
            </div>


            <p>{numUtil}</p>


          </div>
        </div>
      </div>
    </div>
  );
}