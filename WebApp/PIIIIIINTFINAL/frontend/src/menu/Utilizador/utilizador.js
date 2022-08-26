import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css'
import axios from 'axios';
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



    const iDUtilizador = useLocation();
    useEffect(() => {

        axios.get(baseUrl + "/utilizadores/" + iDUtilizador.pathname.split("/")[2])
            .then(response => {
                console.log(response)
                setState({
                    idTipo: response.data.idTipo,
                    nomeUtilizador: response.data.nomeUtilizador,
                    nomeCompleto: response.data.nomeCompleto,
                    email: response.data.email,
                    dataNascimento: response.data.dataNascimento,
                    verificado: response.data.verificado
                })

            })
            .catch(err => {
                console.log(err);
            })
    }, [iDUtilizador])

    
    
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




