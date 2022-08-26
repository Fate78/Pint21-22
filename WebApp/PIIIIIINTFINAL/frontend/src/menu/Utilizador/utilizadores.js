import React, { useEffect, useState } from 'react';
import '../../CSS/stylesdashboard1.css';
import { Link } from "react-router-dom";
import axios from 'axios';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {

    const [utilizadores, setUtilizadores] = useState();
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    useEffect(() => {
        axios.get(baseUrl + "/utilizadores")
            .then(data => {

                setUtilizadores(
                    data.data
                )

            })
            .catch(err => {
                console.log(err);
            });
    })



    function loadFillData() {

        return utilizadores?.map((data, index) => {

            return (

                <div key={index} className='col'>
                    <Link to={`/utilizador/${data.idUtilizador}`} style={{ textDecoration: "none", color: "black" }}>
                    <div className='card'>
                        <div className='card-body'>
                            <div className='card-title'>
                                <p>{data.nomeUtilizador}</p>
                            </div>
                            <hr />
                            <p>{data.nomeCompleto}</p>
                            <p>{data.email}</p>
                            <p>Nasceu a {data.dataNascimento}</p>
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
                <h1 class="h3 mb-4 text-gray-800">Utilizadores</h1>



                <div className='row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4'>


                    {loadFillData()}

                </div>
            </div>

        </div>


    );




}
