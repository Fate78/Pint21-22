import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import '../CSS/stylesdashboard1.css';
import '../CSS/style.css'
const baseUrl = "https://roombookerapi.azurewebsites.net/api";




export default function Pagina() {
    const [state, setState] = useState({
        reservas: [],
        tickets: [],
        utilizadorCentros: [],
        idUtilizador: "",
        idTipo: "",
        nomeUtilizador: "",
        nomeCompleto: "",
        email: "",
        dataNascimento: "",
        verificado: "",
        ativo: "",

    })



    const idUtilizador = useLocation();
    useEffect(() => {

        fetch(baseUrl + "/utilizadores/" + idUtilizador.pathname.split("/")[2], {
            "method": "GET",
            "headers": {
                "Accept": "application/json",
                "Access-Control-Allow-Origin": "true"
            }
        })
            .then(response => response.json())
            .then(response => {
                console.log(response)
                setState({
                    idUtilizador: response.idUtilizador,
                    idTipo: response.idTipo,
                    nomeUtilizador: response.nomeUtilizador,
                    nomeCompleto: response.nomeCompleto,
                    email: response.email,
                    dataNascimento: response.dataNascimento,
                    verificado: response.verificado,
                    ativo: response.ativo

                })

            })
            .catch(err => {
                console.log(err);
            })
    }, [idUtilizador])
    console.log(idUtilizador)



    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="main">
                <h3 className="mb-4 text-gray-800">Informações do Utilizador</h3>
                <div className='budy'>
                    <div className='tituloSala'><p><h1><b>{state.nomeUtilizador}</b></h1></p></div>

                    <p>ID Utilizador: {state.idUtilizador}</p>
                    <p>Nome Completo: {state.nomeCompleto}</p>
                    <p>Email: {state.email}</p>
                    <p>Data de Nascimento: {state.dataNascimento}</p>
                    <p>Verificado: {state.verificado}</p>
                    <p>Ativo: {state.ativo}</p>
                    <p>Reservas: {state.reservas}</p>
                    <p>Utilizador de Centros: {state.utilizadorCentros}</p>
                    <p>Tickets: {state.tickets}</p>
                </div>
            </div>

        </div>


    );


}
