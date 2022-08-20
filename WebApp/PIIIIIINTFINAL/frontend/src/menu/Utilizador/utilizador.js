import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css'
const baseUrl = "https://roombookerapi.azurewebsites.net/api";




export default function Pagina() {
    const [state, setState] = useState({
        idTipo: "",
        nomeUtilizador: "",
        nomeCompleto: "",
        email: "",
        dataNascimento: "",
        verificado: "",

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
                    idTipo: response.idTipo,
                    nomeUtilizador: response.nomeUtilizador,
                    nomeCompleto: response.nomeCompleto,
                    email: response.email,
                    dataNascimento: response.dataNascimento,
                    verificado: response.verificado
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
                <h3 className="mb-4 text-gray-800">Informações da sala</h3>
                <div className='budy'>
                    <p>{state.nomeUtilizador}</p>

                    <p>Esta sala encontra-se localizada no centro {state.nomeUtilizador}</p>
                </div>
            </div>

        </div>


    );


}




