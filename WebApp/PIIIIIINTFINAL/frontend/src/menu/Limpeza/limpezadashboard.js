import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import '../../CSS/Main.css';
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
import { Link } from "react-router-dom";
import axios from 'axios';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";


export default function LimpezaDash() {

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
                <select value={selectedOption} onChange={change} className=""  data-bs-toggle="dropdown">
                    
                        
                    {state.utilizadorCentro.map(centro => <option className='dropdown-item' key={centro.idCentro}  value={centro.idCentro}>{centro.nomeCentro}</option>)}
                    
                </select>
            )

    }



    function loadFillData() {

        return salas.map((data, index) => {

            if (data.idCentro == selectedOption && data.limpo === false) {
            return (

                <tr key={index}>
                    <th > <Link to={`/sala/${data.idSala}`} style={{ textDecoration: "none", color: "black" }}>Sala {data.nSala}</Link></th>
                    <td >Esta sala necessita de Limpeza</td>
                </tr>

            )
            }

        })

    }

    function change(changeEvent) {
        setSelectedOption(changeEvent.target.value)
    }


    return (
        <div className='card ' >
        <div id="content-wrapper" class="d-flex flex-column">
            
            <div className="container table-responsive">
                <h1 class="h3 mb-4 text-gray-800">Limpeza</h1>
                {loadcentro()}

                
                <table className='table '>
                    <thead>
                        <tr>
                            <th scope="col">Número da sala</th>
                            <th scope="col">Necessita de limpeza</th>
                        </tr>
                    </thead>
                    <tbody className=''>
                        {loadFillData()}
                    </tbody>

                </table>
                </div>

            </div>

        </div>
    );



}
