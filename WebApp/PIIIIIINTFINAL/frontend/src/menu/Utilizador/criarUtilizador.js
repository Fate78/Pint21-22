import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import axios from 'axios'
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";




export default function Pagina() {
    const [idTipo, setIdTipo] = useState();
    const [nomeUtilizador, setNomeUtilizador] = useState("");
    const [nomeCompleto, setNomeCompleto] = useState("");
    const [palavraPasse, setPalavraPasse] = useState("");
    const [email, setEmail] = useState("");
    const [dataNascimento, setDataNascimento] = useState("");
    const [email_Verificado, setEmail_Verificado] = useState(false);
    const [password_Verificada, setPassword_Verificada] = useState(false);
    const [ativo, setAtivo] = useState("");
    const [reservas, setReservas] = useState([]);
    const [tickets, setTickets] = useState([]);
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
            idTipo: idTipo,
            nomeUtilizador: nomeUtilizador,
            nomeCompleto: nomeCompleto,
            palavraPasse: palavraPasse,
            email: email,
            dataNascimento: dataNascimento,
            email_Verificado: email_Verificado,
            password_Verificada: password_Verificada,
            ativo: ativo,
            reservas: reservas,
            tickets: tickets,
            utilizadorCentros: utilizadorCentros,
        }
        axios.post(baseUrl + "/utilizadores", data)
            .then(res => {
                setData(res.data);
                setIdTipo();
                setNomeUtilizador("");
                setNomeCompleto("");
                setPalavraPasse("");
                setEmail("");
                setDataNascimento("");
                setEmail_Verificado(false);
                setPassword_Verificada(false);
                setAtivo("");
                setReservas([]);
                setTickets([]);
                setUtilizadorCentros([]);
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
                        <label htmlFor="idTipo">Que tipo de utilizador é:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="idTipo"
                            placeholder="Enter name"
                            value={idTipo}
                            onChange={e => setIdTipo(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="nomeUtilizador">Nome de Utilizador:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="nomeUtilizador"
                            placeholder="Enter name"
                            value={nomeUtilizador}
                            onChange={e => setNomeUtilizador(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="nomeCompleto">Nome Completo:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="nomeCompleto"
                            placeholder="Enter name"
                            value={nomeCompleto}
                            onChange={e => setNomeCompleto(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="palavraPasse">Palavra-Passe:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="palavraPasse"
                            placeholder="Enter name"
                            value={palavraPasse}
                            onChange={e => setPalavraPasse(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="email">E-mail:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="email"
                            placeholder="Enter name"
                            value={email}
                            onChange={e => setEmail(e.target.value)} />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="dataNascimento">Data de Nascimento:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="dataNascimento"
                            placeholder="1987-05-24"
                            value={dataNascimento}
                            onChange={e => setDataNascimento(e.target.value)} />
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




