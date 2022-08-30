import React, { useEffect, useState } from "react";
import '../../CSS/stylesdashboard1.css';
import Dropdown from 'react-bootstrap/Dropdown';
import { Link } from "react-router-dom";
import axios from "axios";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function NavBarTop() {

    const accesstoken = localStorage.getItem("token")
    const user = localStorage.getItem("user")
    const password = localStorage.getItem("password")
    const [isLoggedin, setIsLoggedin] = useState(false);
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`
    const [utilizador, setUtilizador] = useState({
        idUtilizador: "",
        nomeUtilizador: "",
        email: "",
    });

    useEffect(() => {
        axios.get(baseUrl + "/utilizadores/" + user)
            .then(data => {
                setUtilizador({
                    idUtilizador: data.data.idUtilizador,
                    nomeUtilizador: data.data.nomeUtilizador,
                    email: data.data.email
                })
            })
            .catch((err) => {
                console.log(err)
            })

    }, [])

    const logOut = () => {
        localStorage.clear();
        window.location.reload()
    
    }

    
    return (
        
        <div id="wrapper">
            
            <div id="content-wrapper" className="d-flex flex-column">
                <nav className="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow fixed-top ">

                    <button id="sidebarToggleTop" className="btn btn-link d-md-none rounded-circle mr-3">
                        <i className="fa fa-bars"></i>
                    </button>

                    <ul className="navbar-nav ml-auto">

                        <li className="nav-item dropdown no-arrow d-sm-none">
                            <a className="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i className="fas fa-search fa-fw"></i>
                            </a>
                        </li>
                        <Dropdown className="container nav-item dropdown no-arrow mx-1">
                            <Dropdown.Toggle className="nav-link"  id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i className="fas fa-bell fa-fw">bell</i>
                                <span className="badge badge-danger badge-counter">3+</span>
                            </Dropdown.Toggle>
                            <Dropdown.Menu className="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <Dropdown.Item className="dropdown-header">
                                    Notificações
                                </Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>

                        <div className="topbar-divider d-none d-sm-block"></div>

                        <Dropdown className="container dropdown no-arrow">
                            <Dropdown.Toggle className="nav-link dropdown-toggle" >
                                {user}
                            </Dropdown.Toggle>

                            <Dropdown.Menu>
                                <Link to={`/perfil/${utilizador.idUtilizador}`} className="dropdown-item">
                                Perfil
                                </Link>
                                <Dropdown.Item href="#/action-1">Definições</Dropdown.Item>
                                <Dropdown.Item href="#/action-1">Registo de Atividade</Dropdown.Item>
                                <Link to={`/login`} onClick={logOut} className="dropdown-item">    
                                Logout
                                </Link>
                            </Dropdown.Menu>
                        </Dropdown>
                    </ul>

                </nav>
            </div>
        </div>

    );
}
