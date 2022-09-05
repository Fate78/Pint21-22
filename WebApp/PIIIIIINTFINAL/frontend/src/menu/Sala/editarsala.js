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
        reservas: [],
        idSala: "",
        idCentro: "",
        nSala: "",
        lotacaoMax: "",
        tempoMinLimp: "",
        limpo: "",
        ativo: "",

    })

    const [submit, setSubmit] = useState({
        lotacaoMax: "",
        tempoMinLimp: "",
        limpo: "",
        ativo: "",

    })

    function handleChange(event) {
        const { name, value } = event.target
        setSubmit(prevFormData => ({
            ...prevFormData,
            [name]: value
        }))
    }

    const idSala = useLocation();
    useEffect(() => {

        axios.get(baseUrl + "/salas/" + idSala.pathname.split("/")[3])
            .then(data => {
                console.log(data)
                setState({
                    idSala: data.data.idSala,
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
    }, [idSala])
    console.log(idSala)


    function useUpdate() {
        // update entity - PUT
        axios.put(baseUrl + "/salas/" + idSala.pathname.split("/")[3],
            {
                idSala: state.idSala,
                idCentro: state.idCentro,
                nSala: state.nSala,
                lotacaoMax: submit.lotacaoMax === "" ? state.lotacaoMax : submit.lotacaoMax,
                tempoMinLimp: submit.tempoMinLimp === "" ? state.tempoMinLimp : submit.tempoMinLimp,
                limpo: state.limpo === "" ? state.limpo : submit.limpo,
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
                        <p>Tem uma lotação máxima de <input name="lotacaoMax" onChange={handleChange} value={submit.lotacaoMax} placeholder={state.lotacaoMax} /></p>
                        <p>O tempo mínimo de limpeza é <input name="tempoMinLimp" onChange={handleChange} value={submit.tempoMinLimp} placeholder={state.tempoMinLimp} /></p>
                        <p>Ativo? <input name='ativo' value="true" onChange={handleChange} type="radio" /> Ativo <input name='ativo' onChange={handleChange} value="false" type="radio" /> Inativo</p>
                        <hr/>
                        <hr/>
                        <hr/>
                        <p>Limpo? <input name='limpo' value="true" onChange={handleChange} type="radio" /> Limpo <input name='limpo' onChange={handleChange} value="false" type="radio" /> Por Limpar</p>
                        <p>
                            <Link to={`/sala/${idSala.pathname.split("/")[3]}`} className="">
                                <button className='btn btn-primary' onClick={useUpdate}>Guardar</button>
                            </Link></p>
                    </form>
                    <p>
                        <Link to={`/sala/${idSala.pathname.split("/")[3]}`}>
                            <button className='btn btn-primary'>Cancelar</button>
                        </Link>
                    </p>

                </div>
            </div>

        </div>


    );


}




