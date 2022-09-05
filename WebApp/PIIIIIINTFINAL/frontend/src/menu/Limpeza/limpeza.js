import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
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
                <select value={selectedOption} onChange={change} className="" >
                    
                        
                    {state.utilizadorCentro.map(centro => <option className='dropdown-item' key={centro.idCentro}  value={centro.idCentro}>{centro.nomeCentro}</option>)}
                    
                </select>
            )

        
    }

    //const [idk, setIdk] = useState();

    


    function loadFillData() {

        return salas.map((data, index) => {

            if (data.idCentro == selectedOption && data.limpo === false) {
                return (

                    <div key={index} className='col'>
                        <Link to={`/sala/${data.idSala}`} style={{ textDecoration: "none", color: "black" }}>

                            <div className='card h-100'>
                                <div className='card-body'>

                                    <div className='card-title'>

                                        <p>Sala {data.nSala}</p>

                                    </div>
                                    <hr />
                                    <p>Próxima Reserva: {data.reserva}</p>
                                    <p>Tempo de Limpeza: {data.tempoMinLimp}</p>
                                    <p>Encontra-se {data.limpo ? "limpo" : "por limpar"}</p>
                                </div>
                            </div>

                        </Link>
                    </div>
                )
            }

        })

    }

    /*useEffect(() => {
        setIdk(
            loadFillData()
        )
    }, [state])*/

    function change(changeEvent) {
        setSelectedOption(changeEvent.target.value)
    }


    return (
        <div id="content-wrapper" class="d-flex flex-column">

            <div className="container">
                <h1 class="h3 mb-4 text-gray-800">Limpeza</h1>
                {loadcentro()}


                <div className='row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4'>
                    {loadFillData()}
                </div>

            </div>

        </div>
    );



}
