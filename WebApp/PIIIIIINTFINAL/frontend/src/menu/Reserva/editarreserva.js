import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
import axios from 'axios';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";





export default function Pagina() {
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`
    const [state, setState] = useState({
        idReserva: "",
        idSala: "",
        idCentro: "",
        idUtilizador: "",
        //nomeUtilizador: "",
        horaInicio: "",
        horaFim: "",
        dataReserva: "",
        numPessoas: "",
        ativo: "",
    })

    const [submit, setSubmit] = useState({
        idSala: "",
        //nomeUtilizador: "",
        horaInicio: "",
        horaFim: "",
        dataReserva: "",
        numPessoas: "",
        ativo: "",

    })

    function handleChange(event) {
        const { name, value } = event.target
        setSubmit(prevFormData => ({
            ...prevFormData,
            [name]: value
        }))
    }

    const IDReserva = useLocation();
    useEffect(() => {

        axios.get(baseUrl + "/reservas/" + IDReserva.pathname.split("/")[3])
            .then(data => {
                console.log(data)
                setState({
                    idReserva: data.data.idReserva,
                    idSala: data.data.idSala,
                    idCentro: data.data.idCentro,
                    idUtilizador: data.data.idUtilizador,
                   // nomeUtilizador: data.data.nomeUtilizador,
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


    function useUpdate() {
        // update entity - PUT
        axios.put(baseUrl + "/reservas/" + IDReserva.pathname.split("/")[3],
            {

                idReserva: state.idReserva,
                idSala: submit.idSala === "" ? state.idSala : submit.idSala,
                idCentro: state.idCentro,
                idUtilizador: state.idUtilizador,
                //nomeUtilizador: submit.nomeUtilizador === "" ? state.nomeUtilizador : submit.nomeUtilizador,
                horaInicio: submit.horaInicio === "" ? state.horaInicio : submit.horaInicio,
                horaFim: submit.horaFim === "" ? state.horaFim : submit.horaFim,
                dataReserva: submit.dataReserva === "" ? state.dataReserva : submit.dataReserva,
                numPessoas: submit.numPessoas === "" ? state.numPessoas : submit.numPessoas,
                ativo: submit.ativo === "" ? state.ativo : submit.ativo,

            }
        )
            .then(data => {
                console.log(data)
                setState(anterior => ({
                    ...anterior,
                    ...submit
                }))
            })
            .catch(err => {
                console.log(err);
            })
    }

    return (

        <div id="content-wrapper" class="d-flex flex-column">
            <div className="container">
                <h3 className="mb-4 text-gray-800">Informações da sala</h3>
                <div className='budy'>
                    <div className='tituloSala'><p><h1><b>Sala {state.nSala}</b></h1></p></div>

                    <p>Esta sala encontra-se localizada no centro {state.idCentro}</p>
                    <form>
                        <p>Tem uma lotação máxima de <input name="idSala" onChange={handleChange} value={submit.idSala} placeholder={state.idSala} /></p>
                        
                        <p>Tem uma lotação máxima de <input name="horaInicio" onChange={handleChange} value={submit.horaInicio} placeholder={state.horaInicio} /></p>
                        <p>O tempo mínimo de limpeza é <input name="horaFim" onChange={handleChange} value={submit.horaFim} placeholder={state.horaFim} /></p>
                        <p>Tem uma lotação máxima de <input name="dataReserva" onChange={handleChange} value={submit.dataReserva} placeholder={state.dataReserva} /></p>
                        <p>O tempo mínimo de limpeza é <input name="numPessoas" onChange={handleChange} value={submit.numPessoas} placeholder={state.numPessoas} /></p>
                        <p>Ativo? <input name='ativo' value="true" onChange={handleChange} type="radio" /> Ativo <input name='ativo' onChange={handleChange} value="false" type="radio" /> Inativo</p>
                        <p>
                            <Link to={`/reserva/${IDReserva.pathname.split("/")[3]}`} className="">
                                <button className='btn btn-primary' onClick={useUpdate}>Guardar</button>
                            </Link></p>
                    </form>
                    <p>
                        <Link to={`/reserva/${IDReserva.pathname.split("/")[3]}`}>
                            <button className='btn btn-primary'>Cancelar</button>
                        </Link>
                    </p>

                </div>
            </div>

        </div>


    );


}




