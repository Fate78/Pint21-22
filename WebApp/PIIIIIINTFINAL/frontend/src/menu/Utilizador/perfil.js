import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css'
import '../../CSS/Main.css'
import axios from 'axios';
import Image from '../../img/user.png'
import { Link, useNavigate } from 'react-router-dom';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {

    const navigate = useNavigate();

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
        utilizadorCentros: []
    })

    const [submit, setSubmit] = useState({
        nomeUtilizador: "",
        nomeCompleto: "",
        email: "",
        dataNascimento: "",
        ativo: ""
    })

    function handleChange(event) {
        const { name, value } = event.target
        setSubmit(prevFormData => ({
            ...prevFormData,
            [name]: value
        }))
    }

    const iDUtilizador = useLocation();
    useEffect(() => {

        axios.get(baseUrl + "/utilizadores/" + iDUtilizador.pathname.split("/")[2])
            .then(response => {
                console.log(response)
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
                    utilizadorCentros: response.data.utilizadorCentros
                })

            })
            .catch(err => {
                console.log(err);
            })
    }, [iDUtilizador])

    function tipoUtil() {
        if (state.idTipo === 1) {
            return "Administrador"
        } else if (state.idTipo === 2) {
            return "Gestor"
        } else if (state.idTipo === 3) {
            return "Utilizador"
        } else if (state.idTipo === 4) {
            return "Limpezas"
        }
    }

    function useUpdate() {
        // update entity - PUT
        axios.put(baseUrl + "/utilizadores/" + iDUtilizador.pathname.split("/")[2],
            {
                idUtilizador: state.idUtilizador,
                idTipo: state.idTipo,
                nomeUtilizador: submit.nomeUtilizador === "" ? state.nomeUtilizador : submit.nomeUtilizador,
                nomeCompleto: submit.nomeCompleto === "" ? state.nomeCompleto : submit.nomeCompleto,
                palavraPasse: state.palavraPasse,
                email: submit.email === "" ? state.email : submit.email,
                dataNascimento: submit.dataNascimento === "" ? state.dataNascimento : submit.dataNascimento,
                email_Verificado: state.email_Verificado,
                password_Verificada: state.password_Verificada,
                ativo: submit.ativo === "" ? state.ativo : submit.ativo,
                verificado: state.verificado,
                reservas: state.reservas,
                tickets: state.tickets,
                utilizadorCentros: state.utilizadorCentros
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

            if(submit.nomeUtilizador == "") {
                localStorage.setItem("user", state.nomeUtilizador)
            } else {
                localStorage.setItem("user", submit.nomeUtilizador)
            }
            
    }



    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="container">
                <div class="row">
                    <div class="col-xl-4">
                        <div class="card mb-4 mb-xl-0">
                            <div class="card-header">Profile Picture</div>
                            <div class="card-body text-center">
                                <img class="img-account-profile rounded-circle mb-2 img" src={Image} alt="" />
                                <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                                <button class="btn btn-primary" type="button">Upload new image</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <div class="card mb-4">
                            <div class="card-header">Account Details</div>
                            <div class="card-body">
                                <form>
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Nome de Utilizador</label>
                                        <input class="form-control" name='nomeUtilizador' type="text" onChange={handleChange} placeholder={state.nomeUtilizador} value={submit.nomeUtilizador} />
                                    </div>
                                    <div class="row gx-3 mb-3">
                                        <div class="col-md-12">
                                            <label class="small mb-1" for="inputFirstName">Nome Completo</label>
                                            <input class="form-control" name='nomeCompleto' type="text" onChange={handleChange} placeholder={state.nomeCompleto} value={submit.nomeCompleto} />
                                        </div>
                                    </div>
                                    <div class="row gx-3 mb-3">
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputOrgName">Tipo de Utilizador</label>
                                            <input class="form-control" name='idTipo' type="text" placeholder={tipoUtil()} value={tipoUtil()} />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputLocation">Centro</label>
                                            <input class="form-control" name='nomeUtilizador' type="text" placeholder="Enter your location" value="San Francisco, CA" />
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputEmailAddress">Email</label>
                                        <input class="form-control" name='email' type="email" onChange={handleChange} placeholder={state.email} value={submit.email} />
                                    </div>
                                    <div class="row gx-3 mb-3">
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputPhone">Ativo</label>
                                            <p><input name='ativo' onChange={handleChange} value="true" type="radio"  /> Ativo <input name='ativo' onChange={handleChange} value="false" type="radio" />Inativo</p>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputBirthday">Data de Nascimento</label>
                                            <input class="form-control" type="text" onChange={handleChange} name="dataNascimento" placeholder={state.dataNascimento} value={submit.dataNascimento} />
                                        </div>
                                    </div>
                                    <Link to={-1} className="btn btn-primary">
                                        <button type='button' className='btn btn-primary' onClick={useUpdate}>Guardar</button>
                                    </Link>
                                    <Link to={`/utilizador/password/${iDUtilizador.pathname.split("/")[2]}`} className="btn btn-primary">
                                        <button type='button' className='btn btn-primary'>Alterar Password</button>
                                    </Link>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        </div >
    );


}




