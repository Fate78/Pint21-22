import React, { useEffect, useState } from 'react';
import '../../CSS/Main.css';
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
import { Link } from "react-router-dom";
import axios from 'axios';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {
    const [salas, setSalas] = useState([]);
    const [centros, setCentros] = useState([]);
    const [selectedOption, setSelectedOption] = useState(1);
    const user = localStorage.getItem("user")
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    useEffect(() => {
        axios.get(baseUrl + "/salas")
            .then(data => {

                setSalas(
                    data.data
                )

            })
            
            .catch(err => {
                console.log(err);
            });
            
    }, [])
    
    const [state, setState] = useState({
        idUtilizador: "",
        idTipo: "",
        nomeUtilizador: "",
        nomeCompleto: "",
        palavraPasse: "",
        email: "",
        dataNascimento: "",
        email_Verificado: "",
        password_Verificada: "",
        ativo: "",
        verificado: "",
        reservas: [],
        tickets: [],
        utilizadorCentro: []
    })

    useEffect(() => {
        axios.get(baseUrl + "/utilizadores/" + user)
            .then(response => {
                setState({
                    idUtilizador: response.data.idUtilizador,
                    idTipo: response.data.idTipo,
                    nomeUtilizador: response.data.nomeUtilizador,
                    nomeCompleto: response.data.nomeCompleto,
                    palavraPasse: response.data.palavraPasse,
                    email: response.data.email,
                    dataNascimento: response.data.dataNascimento,
                    email_Verificado: response.data.email_Verificado,
                    password_Verificada: response.data.password_Verificada,
                    ativo: response.data.ativo,
                    verificado: response.data.verificado,
                    reservas: response.data.reservas,
                    tickets: response.data.tickets,
                    utilizadorCentro: response.data.utilizadorCentro
                })

            })
            .catch(err => {
                console.log(err);
            })
    }, [])

    function loadcentro() {
        
            return (
                <select value={selectedOption} onChange={change} className="">
                    
                        
                    {state.utilizadorCentro.map(centro => <option className='dropdown-item' key={centro.idCentro}  value={centro.idCentro}>{centro.nomeCentro}</option>)}
                    
                </select>
            )

    }

    function loadFillData() {

        return salas.map((data, index) => {

            if (data.idCentro == selectedOption) {
                return (

                    <div key={index} className='col'>
                        <Link to={`/sala/${data.idSala}`} style={{ textDecoration: "none", color: "black" }}>

                            <div className='card h-100'>
                                <div className='card-body'>

                                    <div className='card-title'>

                                        <p>Sala {data.nSala}</p>

                                    </div>
                                    <hr />
                                    <p>Lotação máxima: {data.lotacaoMax}</p>
                                    <p>Tempo de Limpeza: {data.tempoMinLimp}</p>
                                    <p>Encontra-se {data.limpo ? "limpo" : "por limpar"}</p>
                                    <p>Está {data.ativo ? "ativo" : "inativo"}</p>
                                </div>
                            </div>

                        </Link>
                    </div>
                )
            }

        })

    }

    function change(changeEvent) {
        setSelectedOption(changeEvent.target.value)
    }



    return (
        <div id="content-wrapper" class="d-flex flex-column">

            <div className="container">
                <h1 class="h3 mb-4 text-gray-800">Gestão de Salas</h1>
                <p>
                    <Link to={`/criarsala`}>
                        <button className="btn btn-primary me-3 h-100">Criar sala</button>
                    </Link>
                
                    {loadcentro()}
                </p>

                <div className='row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4 mx-auto justify-content-center'>

                    {loadFillData()}



                </div>

            </div>

        </div>
    );



}
