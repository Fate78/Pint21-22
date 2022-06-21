import React from 'react';
import axios from 'axios';
import 'sweetalert2/src/sweetalert2.scss';
import '../../../../assets/css/CSS_HTML.css';
import { Link } from "react-router-dom"


class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            listUtilizadores: []
        }
    }
    
    componentDidMount() {
        this.loadUtilizador();
    }

    loadUtilizador() {
        const url = "https://roombookerapi.azurewebsites.net/api/utilizadores/";
        axios.get(url)
        .then(res => {
            if(res.data.success){
                const data = res.data.data;
                this.setState({ listUtilizadores:data });
            }else{
                alert("Error Web Service!");
            }
        })
        .catch(error => {
            alert(error)
        });
    }

    render() {
        return(
           
            <div id="content">

               
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                  
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Pesquise aqui..."
                                aria-label="Search" aria-describedby="basic-addon2"></input>
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <span>Pesquisar</span>
                                </button>
                            </div>
                        </div>
                    </form>

                    
                    <ul class="navbar-nav ml-auto">

                        
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                        </li>
                       
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Notificações
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class=" bg-primary">
                                            <i class=" text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="bg-success">
                                            <i class=" text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class=" bg-warning">
                                            <i class=" text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Mostrar mais</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Username</span>
                                
                            </a>
                         
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Perfil
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Definições
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Registo de Atividade
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
              

                
                <div class="container-fluid">

                    
                    <h1 class="h3 mb-4 text-gray-800">Utilizadores Registados</h1>

                </div>
                

            </div>
        );
    }

    loadFillData() {
        return this.state.listUtilizadores.map((data, index) =>{
            return(
                <tr key={index}>
                    
                    <th>{data.idUtilizador}</th>
                    <td>{data.idTipo}</td>
                    <td>{data.nomeUtilizador}</td>
                    <td>{data.nomeCompleto}</td>
                    <td>{data.email}</td>
                    <td>{data.dataNascimento}</td>
                    <td>{data.verificado}</td>
                    <td>{data.ativo}</td>
                    <td>{data.reservas}</td>
                    <td>{data.tickets}</td>
                    <td>{data.utilizadorCentros}</td>
                    <td><Link class="btn btn-outline-info " to={"/utilizador/"+data.id} >Ver</Link></td>
                </tr>
            )
        })
    }

}

export default Pagina;