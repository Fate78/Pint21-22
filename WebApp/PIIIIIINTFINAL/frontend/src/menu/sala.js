import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import '../CSS/stylesdashboard1.css';
import '../CSS/style.css'
const baseUrl = "https://roombookerapi.azurewebsites.net/api";




export default function Pagina() {
    const [state, setState] = useState({
        reservas: [],
        idCentro: "",
        nSala: "",
        lotacaoMax: "",
        tempoMinLimp: "",
        limpo: "",
        ativo: "",

    })



    const idSala = useLocation();
    useEffect(() => {

        fetch(baseUrl + "/salas/" + idSala.pathname.split("/")[2], {
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
                    idCentro: response.idCentro,
                    nSala: response.nSala,
                    lotacaoMax: response.lotacaoMax,
                    tempoMinLimp: response.tempoMinLimp,
                    limpo: response.limpo,
                    ativo: response.ativo
                })

            })
            .catch(err => {
                console.log(err);
            })
    }, [idSala])
    console.log(idSala)



    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="main">
                <h3 className="mb-4 text-gray-800">Informações da sala</h3>
                <div className='budy'>
                    <div className='tituloSala'><p><h1><b>Sala {state.nSala}</b></h1></p></div>

                    <p>Esta sala encontra-se localizada no centro {state.idCentro}</p>
                </div>
            </div>

        </div>


    );


}




