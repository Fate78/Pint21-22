import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import axios from 'axios'
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";


export default function Pagina() {
    const [state, setState] = useState({
        idCentro: "",
        nomeCentro: "",
        ativo: "",
        salas: [],
        utilizadorCentros: []

    })

    const IDCentro = useLocation();
    useEffect(() => {

        axios.get(baseUrl + "/centros/" + IDCentro.pathname.split("/")[2])
            .then(data => {
                console.log(data)
                setState({
                    idCentro: data.data.idCentro,
                    nomeCentro: data.data.nomeCentro,
                    salas: data.data.salas,
                    utilizadorCentros: data.data.utilizadorCentros,
                    ativo: data.data.ativo
                })
                console.log(state)
            })
            .catch(err => {
                console.log(err);
            })
    }, [IDCentro])
    console.log(IDCentro)



    return (
        <div id="content-wrapper" class="d-flex flex-column">

            <div className="main">

                <h3 className="mb-4 text-gray-800">Informações do centro</h3>

                <div className='budy'>

                    <div className='tituloSala'><p><h1><b>Centro {state.nomeCentro}</b></h1></p></div>

                    <p>Este é o centro {state.nomeCentro}.</p>

                    <Link to={`/centro/editar/${state.idCentro}`}>

                        <button>Editar</button>

                    </Link>

                </div>
                
            </div>

        </div>


    );


}




