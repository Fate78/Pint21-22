import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import axios from 'axios'
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";




export default function Pagina() {
    const accesstoken = localStorage.getItem("token")
    const user = localStorage.getItem("user")
    const password = localStorage.getItem("password")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`
    const [state, setState] = useState({
        idReserva: "",
        idSala: "",
        idCentro: "",
        idUtilizador: "",
        nomeUtilizador: "",
        horaInicio: "",
        horaFim: "",
        dataReserva: "",
        numPessoas: "",
        ativo: "",

    })

    

    const IDReserva = useLocation();
    useEffect(() => {

        axios.get(baseUrl + "/reservas/" + IDReserva.pathname.split("/")[2])
            .then(data => {
                console.log(data)
                setState({
                    idReserva:data.data.idReserva,
                    idSala: data.data.idSala,
                    idCentro: data.data.idCentro,
                    idUtilizador: data.data.idUtilizador,
                    nomeUtilizador: data.data.nomeUtilizador,
                    horaInicio: data.data.horaInicio,
                    horaFim: data.data.horaFim,
                    dataReserva: data.data.dataReserva,
                    numPessoas: data.data.numPessoas,
                    ativo: data.data.ativo
                })
                
            })
            .catch(err => {
                console.log(err);
            })
    }, [IDReserva])
    console.log(IDReserva)



    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="container">
                <h3 className="mb-4 text-gray-800">Informações da sala</h3>
                <div className='budy'>
                    <div className='tituloSala'><p><h1><b>Reserva de {state.nomeUtilizador}</b></h1></p></div>

                    <p>Reserva feita na sala {state.idSala} do centro de {state.idCentro}.</p>
                    <p>A reserva começa às {state.horaInicio} e termina às {state.horaFim} do dia {state.dataReserva}.</p>
                    <p>Irá ser utilizada por {state.numPessoas} pessoas.</p>
                    <Link to={`/reserva/editar/${state.idSala}`}>
                    <button>Editar</button>
                    </Link>
                </div>
            </div>

        </div>


    );


}




