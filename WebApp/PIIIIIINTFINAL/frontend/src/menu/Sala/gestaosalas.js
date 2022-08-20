import React, { useEffect, useState } from 'react';
import '../../CSS/Main.css';
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
import { Link } from "react-router-dom";
import axios from 'axios';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {
    const [salas, setSalas] = useState([]);
    const [centros, setCentros] = useState([]);
    const [selectedOption, setSelectedOption] = useState(1);
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    useEffect(() => {
        axios.get(baseUrl + "/salas"

        )
            .then(data => {

                setSalas(
                    data.data
                )

            })
            .catch(err => {
                console.log(err);
            });
    }, [])

    useEffect(() => {

        axios.get(baseUrl + "/centros"


        )
            .then(data => {

                setCentros(
                    data.data
                )

            })
            .catch(err => {
                console.log(err);
            });
    }, []);

    function loadcentro() {
        return centros.map((data) => {


            return (
                <label>



                    <input onChange={change} key={data.idCentro} type="radio" value={data.idCentro} checked={selectedOption == data.idCentro} />
                    {data.nomeCentro} &nbsp;





                </label>
            )

        })
    }

    function loadFillData() {

        return salas.map((data, index) => {

            if (data.idCentro == selectedOption) {
                return (

                    <div key={index} className='col'>
                        <Link to={`/sala/${data.idSala}`} style={{ textDecoration: "none", color: "black" }}>

                            <div className='card'>
                                <div className='card-body'>

                                    <div className='card-title'>

                                        <p>Sala {data.nSala}</p>

                                    </div>
                                    <hr />
                                    <p>Lotação máxima: {data.lotacaoMax}</p>
                                    <p>Tempo de Limpeza: {data.tempoMinLimp}</p>
                                    <p>Encontra-se {data.limpo ? "limpo" : "por limpar"}</p>
                                    <p>Está {data.ativo ? "ativo" : "inativo"}</p>
                                </div>
                            </div>

                        </Link>
                    </div>
                )
            }

        })

    }

    function change(changeEvent) {
        setSelectedOption(changeEvent.target.value)
    }



    return (
        <div id="content-wrapper" class="d-flex flex-column">

            <div className="container">
                <h1 class="h3 mb-4 text-gray-800">Gestão de Salas</h1>
                <p>
                    <Link to={`/criarsala`}>
                        <button className="btn btn-primary mt-3">Criar sala</button>
                    </Link>
                </p>
                {loadcentro()}


                <div className='row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4'>

                    {loadFillData()}



                </div>

            </div>

        </div>
    );



}
