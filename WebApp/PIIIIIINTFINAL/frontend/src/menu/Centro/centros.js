import React, { useState, useEffect } from 'react';
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
import { Link } from "react-router-dom";
import axios from 'axios';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {
    const [centros, setCentros] = useState([]);
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    useEffect(() => {
        axios.get(baseUrl + "/centros")
            .then(data => {

                setCentros(
                    data.data
                )

            })
            .catch(err => {
                console.log(err);
            });
    });

    function loadcentro() {

        return centros.map((data, index) => {


            return (

                <div key={index} className='col'>
                    <Link to={`/centro/${data.idCentro}`} style={{ textDecoration: "none", color: "black" }}>
                        <div className='card'>
                            <div className='card-body'>
                                <div className='card-title'>
                                    <p>Centro de {data.nomeCentro}</p>
                                </div>
                                <hr />
                                <p>Este centro está {data.ativo ? "ativo" : "inativo"}</p>
                            </div>
                        </div>
                    </Link>
                </div>
            )


        })

    }



    return (
        <div id="content-wrapper" class="d-flex flex-column">

            <div className="container">
                <h1 class="h3 mb-4 text-gray-800">Centros</h1>

                <p> <Link to={'/criarcentro'}>
                    <button className="btn btn-primary mt-3">Criar Centro</button>
                </Link>
                </p>
                <div className='row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4'>


                    {loadcentro()}

                </div>
            </div>

        </div>


    );



}
