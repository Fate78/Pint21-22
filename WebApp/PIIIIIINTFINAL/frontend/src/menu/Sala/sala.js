import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import axios from 'axios'
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
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

    

    const IDSala = useLocation();
    useEffect(() => {

        axios.get(baseUrl + "/salas/" + IDSala.pathname.split("/")[2])
            .then(data => {
                console.log(data)
                setState({
                    idSala:data.data.idSala,
                    idCentro: data.data.idCentro,
                    nSala: data.data.nSala,
                    lotacaoMax: data.data.lotacaoMax,
                    tempoMinLimp: data.data.tempoMinLimp,
                    limpo: data.data.limpo,
                    ativo: data.data.ativo
                })

            })
            .catch(err => {
                console.log(err);
            })
    }, [IDSala])
    console.log(IDSala)



    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="container">
                <h3 className="mb-4 text-gray-800">Informações da sala</h3>
                <div className='budy'>
                    <div className='tituloSala'><p><h1><b>Sala {state.nSala}</b></h1></p></div>

                    <p>Esta sala encontra-se localizada no centro {state.idCentro}.</p>
                    <p>Tem uma lotação máxima de {state.lotacaoMax}.</p>
                    <p>O tempo mínimo de limpeza é de {state.tempoMinLimp} minutos.</p>
                    <Link to={`/sala/editar/${state.idSala}`}>
                    <button>Editar</button>
                    </Link>
                </div>
            </div>

        </div>


    );


}




