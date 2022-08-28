import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import axios from 'axios'
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";




export default function Pagina() {
    const [idCentro, setIdCentro] = useState();
    const [nSala, setNSala] = useState();
    const [lotacaoMax, setLotacaoMax] = useState();
    const [tempoMinLimp, setTempoMinLimp] = useState("");
    const [limpo, setLimpo] = useState("");
    const [ativo, setAtivo] = useState("");
    const [reservas, setReservas] = useState([]);
    const [loading, setLoading] = useState(false);
    const [isError, setIsError] = useState(false);
    const [data, setData] = useState(null);
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    const handleSubmit = () => {
        setLoading(true);
        setIsError(false);
        const data = {
            idCentro: idCentro,
            nSala: nSala,
            lotacaoMax: lotacaoMax,
            tempoMinLimp: tempoMinLimp,
            limpo: limpo,
            ativo: ativo,
            reservas: reservas,
        }
        axios.post(baseUrl + "/salas", data)
            .then(res => {
                setData(res.data);
                setIdCentro();
                setNSala();
                setLotacaoMax();
                setTempoMinLimp("");
                setLimpo("");
                setAtivo("");
                setReservas([]);
                setLoading(false);
            })
            .catch(err => {
                setLoading(false);
                setIsError(true);
            });
    }


    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="container">
                <h3 className="mb-4 text-gray-800">Criar sala</h3>
                    <div classNames="form-group">
                        <label htmlFor="idCentro">A que centro pertence</label>
                        <input
                            type="text"
                            className="form-control"
                            id="idCentro"
                            placeholder="Enter name"
                            value={idCentro}
                            onChange={e => setIdCentro(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="nSala">Número da sala</label>
                        <input
                            type="text"
                            className="form-control"
                            id="nSala"
                            placeholder="Enter name"
                            value={nSala}
                            onChange={e => setNSala(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="lotaxaoMax">Qual a lotação máxima</label>
                        <input
                            type="text"
                            className="form-control"
                            id="lotaxaoMax"
                            placeholder="Enter name"
                            value={lotacaoMax}
                            onChange={e => setLotacaoMax(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="tempoMinLimp">Qual o tempo minimo de limpeza</label>
                        <input
                            type="text"
                            className="form-control"
                            id="tempoMinLimp"
                            placeholder="Enter name"
                            value={tempoMinLimp}
                            onChange={e => setTempoMinLimp(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="limpo">Limpo</label>
                        <p><input name='limpo' value="true" onChange={e => setLimpo(e.target.value)} type="radio" /> Limpo <input name='limpo' onChange={e => setLimpo(e.target.value)} value="false" type="radio" /> Não limpo</p>
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="ativo">Ativo</label>
                        <p><input name='ativo' value="true" onChange={e => setAtivo(e.target.value)} type="radio" /> Ativo <input name='ativo' onChange={e => setAtivo(e.target.value)} value="false" type="radio" /> Inativo</p>
                    </div>
                    <button
                        type="submit"
                        className="btn btn-primary mt-3"
                        onClick={handleSubmit}
                        disabled={loading}
                    >{loading ? 'Loading...' : 'Submit'}</button>
                
            </div>

        </div>


    );


}




