import React, { useState, useEffect } from 'react';
import '../../CSS/stylesdashboard1.css';
import { Link } from "react-router-dom";
import axios from 'axios';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {
    const [reservas, setReservas] = useState([]);
    const [utilizadores, setUtilizadores] = useState([]);
    const [centros, setCentros] = useState([]);
    const [selectedOption, setSelectedOption] = useState(1);
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    useEffect(() => {
        axios.get(baseUrl + "/reservas")
            .then(data => {

                setReservas(
                    data.data
                )

            })
            .catch(err => {
                console.log(err);
            });
    }, []);

    useEffect(() => {
        axios.get(baseUrl + "/utilizadores")
        .then(user => {
            setUtilizadores(
                user.data
            )
        })
        .catch(err => {
            console.log(err);
        })
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

    function loadreserva() {

        let nome 
        return reservas.map((data, index) => {
            
            for( let i = 0; i < utilizadores.length; i++) {
                if(data.idUtilizador === utilizadores[i].idUtilizador) {
                    nome = utilizadores[i].nomeUtilizador
                    break
                }
            }
            console.log(nome)
            if (data.idCentro == selectedOption) {
            return (

                <div key={index} className='col'>
                    <Link to={`/reserva/${data.idReserva}`} style={{ textDecoration: "none", color: "black" }}>
                    <div className='card'>
                        <div className='card-body'>
                        <div className='card-title'>
                            
                                <p>Reserva feita por {nome}</p>
                            
                        </div>
                        <hr />
                        <p>Reserva para {data.dataReserva}</p>
                        <p>Horário: {data.horaInicio} - {data.horaFim}</p>
                        <p>Número de pessoas: {data.numPessoas}</p>
                        </div>
                    </div>
                    </Link>
                </div>
            )
            
            }
        })
        
    }

    function change(changeEvent) {
        setSelectedOption( changeEvent.target.value )
    }

    return (
        <div id="content-wrapper" class="d-flex flex-column">

            <div className="container">
                <h1 class="h3 mb-4 text-gray-800">Reservas</h1>
                {loadcentro()}
                <div className='row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4'>


                    {loadreserva()}

                </div>
            </div>

        </div>


    );



}
