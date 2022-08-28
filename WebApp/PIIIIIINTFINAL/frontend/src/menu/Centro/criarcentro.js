import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import axios from 'axios'
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";




export default function Pagina() {
    const [nomeCentro, setNomeCentro] = useState('');
    const [ativo, setAtivo] = useState('');
    const [salas, setSalas] = useState([]);
    const [utilizadorCentros, setUtilizadorCentros] = useState([]);
    const [loading, setLoading] = useState(false);
    const [isError, setIsError] = useState(false);
    const [data, setData] = useState(null);
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    const handleSubmit = () => {
        setLoading(true);
        setIsError(false);
        const data = {
            nomeCentro: nomeCentro,
            ativo: ativo,
            salas: salas,
            utilizadorCentros: utilizadorCentros,
        }
        axios.post(baseUrl + "/centros", data)
            .then(res => {
                setData(res.data);
                setNomeCentro('');
                setAtivo([]);
                setSalas([]);
                setUtilizadorCentros('');
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
                <h3 className="mb-4 text-gray-800">Informações do centro</h3>
                
                    <div classNames="form-group">
                        <label htmlFor="name">Nome Centro</label>
                        <input
                            type="text"
                            className="form-control"
                            id="nomeCentro"
                            placeholder="Enter name"
                            value={nomeCentro}
                            onChange={e => setNomeCentro(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="name">Ativo</label>
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




