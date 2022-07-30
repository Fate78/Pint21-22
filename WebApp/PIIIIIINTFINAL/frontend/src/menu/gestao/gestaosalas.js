import React, { useState } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom"
import '../../assets/bootstrap/bootstrap/css/bootstrap.css';
import '../../assets/CSS/stylesdashboard1.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

class Pagina extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      gestaosalas: [],
    };

  }

  componentDidMount() {
    // get all entities - GET
    fetch(baseUrl + "/salas", {
        "method": "GET",
        "headers": {
            "Accept": "application/json",
            "Access-Control-Allow-Origin" : "true"
        }
    })
    .then(response => response.json())
    .then(response => {
        this.setState({
        gestaosalas: response
        })
        
    })
    .catch(err => { console.log(err); 
    });
    
    
    
}


  render() {
    return (
      
      <div id="content-wrapper" class="d-flex flex-column">

          

              <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                  <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                      <i class="fa fa-bars"></i>
                  </button>

                  <form
                      class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                      <div class="input-group">
                          <input type="text" class="form-control bg-light border-0 small" placeholder="Pesquise aqui..." aria-label="Search" aria-describedby="basic-addon2" />
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
              
              <div className ="container-fluid">
              <h1 class="h3 mb-4 text-gray-800">Gestão de Salas</h1>
              
              <div className='box'>
              

               {this.loadFillData()}

               </div>
             
              

        </div>
          <footer class="sticky-footer bg-white">
              <div class="container my-auto">
                  <div class="copyright text-center my-auto">
                      <span> Room Booker&copy; 2022</span>
                  </div>
              </div>
          </footer>


          <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
      aria-hidden="true">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Terminar sessão</h5>
                  <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">×</span>
                  </button>
              </div>
              <div class="modal-body">Tem a certeza que pretende terminar sessão?</div>
              <div class="modal-footer">
                  <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                  <a class="btn btn-primary" href="login.html">Logout</a>
              </div>
          </div>
      </div>
      </div>
      </div>
      
    );
  }

  loadFillData() {
    
    return this.state.gestaosalas.map((data, index) =>{
        
        
        return(
            <tr key={index}>
                
                {this.limpo()}
                {this.ativo()} 
                <div className='boxes'>
                <p>Sala {data.nSala}</p>
                <p>Lotação máxima: {data.lotacaoMax}</p>
                <p>Tempo de Limpeza: {data.tempoMinLimp}</p>
                <p>{data.limpo}</p>
                <p>{data.ativo}</p>       
                </div> 
            </tr>
        )
    })
}

limpo() {
    return this.state.gestaosalas.map((data, index) =>{
        if(data.limpo = 'true') {
            data.limpo = <p>Encontra-se limpo!!</p>
        }
        else {
            data.limpo = <p>Limpeza urgente!!</p>
        }
    })
}

ativo() {
    return this.state.gestaosalas.map((data, index) =>{
        if(data.ativo = 'true') {
            data.ativo = <p>Sala encontra-se disponível.</p>
        }
        else {
            data.ativo = <p>Sala não se encontra disponível.</p>
        }
    })
}

sala() {
    return this.state.gestaosalas.map((data, index) =>{
        <p>Sala {data.nSala}</p>
    })
}



}
export default Pagina;
