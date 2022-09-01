import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import axios from 'axios'
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";




export default function Pagina() {

    const user = localStorage.getItem("user")
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    const [selectedOption, setSelectedOption] = useState(1);

    const [submit, setSubmit] = useState({
        idTipo: "",
        nomeUtilizador: "",
        nomeCompleto: "",
        palavraPasse: "",
        email: "",
        dataNascimento: "",
        ativo: "",

    })

    const [centro, setCentro] = useState({
        idUtilizador: "",
        idCentro: "",
    })

    const [state, setState] = useState({
        idUtilizador: "",
    });

    const [utilizador, setUtilizador] = useState();

    function handleChange(event) {
        const { name, value } = event.target
        setSubmit(prevFormData => ({
            ...prevFormData,
            [name]: value
        }))
        setCentro(prevFormData => ({
            ...prevFormData,
            [name]: value
        }))
    }

    function useUpdate() {
        // update entity - PUT
        axios.post(baseUrl + "/utilizadores",
            {
                idTipo: submit.idTipo,
                nomeUtilizador: submit.nomeUtilizador,
                nomeCompleto: submit.nomeCompleto,
                palavraPasse: submit.palavraPasse,
                email: submit.email,
                dataNascimento: submit.dataNascimento,
                ativo: submit.ativo
            }
        )
            .then(data => {
                setSubmit(anterior => ({
                    ...anterior,
                    ...submit
                }))
                setState(data.data)
            })
            .catch(err => {
                console.log(err);
            })
    }

    useEffect(() => {
        axios.post(baseUrl + "/utilizadorescentros",
        {
            idUtilizador: state.idUtilizador,
            idCentro: centro.idCentro
        }
        )
        .then(response => {
            console.log(response)
            setCentro(anterior => ({
                ...anterior,
                ...centro
            }))
        })
        .catch(err => {
            console.log(err)
        })
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [state])

    useEffect(() => {
        axios.get(baseUrl + "/utilizadores/" + user)
            .then(response => {
                setUtilizador({
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
            <select value={selectedOption} onChange={change} className="form-control" name="idCentro" required>
                
                    
                {utilizador.utilizadorCentro.map(centro => <option className='dropdown-item' key={centro.idCentro}  value={centro.idCentro}>{centro.nomeCentro}</option>)}
                
            </select>
        )

}

    function change(changeEvent) {
        setSelectedOption(changeEvent.target.value)
    }

    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="container">
                <h3 className="mb-4 text-gray-800">Criar sala</h3>
                    <div classNames="form-group">
                        <label htmlFor="idTipo">Que tipo de utilizador é:</label>
                        <input
                            type="text"
                            name="idTipo"
                            className="form-control"
                            placeholder="Enter name"
                            value={submit.idTipo}
                            onChange={handleChange}
                            required />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="nomeUtilizador">Nome de Utilizador:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="nomeUtilizador"
                            placeholder="Enter name"
                            value={submit.nomeUtilizador}
                            onChange={handleChange}
                            required />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="nomeCompleto">Nome Completo:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="nomeCompleto"
                            placeholder="Enter name"
                            value={submit.nomeCompleto}
                            onChange={handleChange}
                            required />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="palavraPasse">Palavra-Passe:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="palavraPasse"
                            placeholder="Enter name"
                            value={submit.palavraPasse}
                            onChange={handleChange}
                            required />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="email">E-mail:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="email"
                            placeholder="Enter name"
                            value={submit.email}
                            onChange={handleChange}
                            required />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="dataNascimento">Data de Nascimento:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="dataNascimento"
                            placeholder="1987-05-24"
                            value={submit.dataNascimento}
                            onChange={handleChange}
                            required />
                    </div>
                    <div classNames="form-group">
                        <label htmlFor="dataNascimento">Centro:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="idCentro"
                            value={centro.idCentro}
                            onChange={handleChange}
                            required />
                    </div>
                    
                    
                    <div classNames="form-group">
                        <label htmlFor="ativo">Ativo</label>
                        <p><input name='ativo' value="true" onChange={handleChange} type="radio" /> Ativo <input name='ativo' onChange={handleChange} value="false" type="radio" /> Inativo</p>
                    </div>
                    <button
                        type="submit"
                        className="btn btn-primary mt-3"
                        onClick={useUpdate}
                        >
                        Guardar
                    </button>
                
            </div>

        </div>


    );


}




