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
        idCentro: "",
        nomeCentro: "",
        ativo: "",
        salas: [],
        utilizadorCentros: [],

    })

    const [submit, setSubmit] = useState({
        nomeCentro: "",
        ativo: "",
    })

    function handleChange(event) {
        const { name, value } = event.target
        setSubmit(prevFormData => ({
            ...prevFormData,
            [name]: value
        }))
    }

    const iDCentro = useLocation();
    useEffect(() => {

        axios.get(baseUrl + "/centros/" + iDCentro.pathname.split("/")[3])
            .then(data => {
                console.log(data)
                setState({
                    idCentro: data.data.idCentro,
                    nomeCentro: data.data.nomeCentro,
                    ativo: data.data.ativo,
                    salas: data.data.salas,
                    utilizadorCentros: data.data.utilizadorCentros
                })

            })
            .catch(err => {
                console.log(err);
            })
    }, [iDCentro])
    console.log(iDCentro)


    function useUpdate() {
        // update entity - PUT
        useEffect(() => {

            axios.put(baseUrl + "/centros/" + iDCentro.pathname.split("/")[3],
                { 
                    idCentro: state.idCentro,
                    nomeCentro: state.nomeCentro,
                    ativo: state.ativo,
                    salas: state.salas,
                    utilizadorCentros: state.utilizadorCentros,
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
                        <p>Tem uma lotação máxima de <input name="lotacaoMax" onChange={handleChange} value={submit.lotacaoMax} placeholder={state.lotacaoMax} /></p>
                        <p>O tempo mínimo de limpeza é <input name="tempoMinLimp" onChange={handleChange} value={submit.tempoMinLimp} placeholder={state.tempoMinLimp} /></p>
                        <p>Ativo? <input name='ativo' value="true" onChange={handleChange} type="radio" /> Ativo <input name='ativo' onChange={handleChange} value="false" type="radio" /> Inativo</p>
                        <Link to={`/centro/${iDCentro.pathname.split("/")[3]}`}>
                            <button onClick={useUpdate}>Guardar</button>
                        </Link>
                    </form>
                    <Link to={`/centro/${iDCentro.pathname.split("/")[3]}`}>
                        <button>Cancelar</button>
                    </Link>

                </div>
            </div>

        </div>


    );


}




