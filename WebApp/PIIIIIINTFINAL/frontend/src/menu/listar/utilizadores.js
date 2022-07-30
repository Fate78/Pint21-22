import React from 'react';
import axios from 'axios';
import { Link } from "react-router-dom"
import '../../assets/bootstrap/bootstrap/css/bootstrap.css';
import '../../assets/CSS/stylesdashboard1.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

class Pagina extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      utilizadores: [],
      nomeUtilizador: '',
      nomeCompleto: '',
    };

    this.create = this.create.bind(this);
    this.update = this.update.bind(this);
    this.delete = this.delete.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    // get all entities - GET
    fetch(baseUrl + "/utilizadores", {
        "method": "GET",
        "headers": {
            "Accept": "application/json",
            "Access-Control-Allow-Origin" : "true"
        }
    })
    .then(response => response.json())
    .then(response => {
        this.setState({
        utilizadores: response
        })
    })
    .catch(err => { console.log(err); 
    });

}

  create(e) {
    // add entity - POST
    e.preventDefault();

  }

  update(e) {
    // update entity - PUT
    e.preventDefault();

  }

  delete(e) {
    // delete entity - DELETE
    e.preventDefault();

  }

  handleChange(changeObject) {
    this.setState(changeObject)
  }

  render() {
    return (
      <div id="wrapper" style={{overflow: 'scroll'}}>
      
      <div id="content-wrapper" class="d-flex flex-column">

          <div id="content">

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

                  <h1 class="h3 mb-4 text-gray-800">Utilizadores Registados</h1>
<<<<<<< HEAD
                  <div className='box'>
                    {this.loadFillData()}
                  </div>
                
=======
                  <button type="" href="utilizadores2.html">Update</button>
                <div class="container-fluid">


                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"> Lista De Utilizadores Registados</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                
                                        <tr>
                                            <th>ID Utilizador</th>
                                            <th>Nome Utilizador</th>
                                            <th>Reservas</th>
                                            <th>ID Tipo</th>
                                            <th>Tickets</th>
                                            <th>Utilizador de Centros</th>
                                            <th>Nome Completo</th>
                                            <th>Email </th>
                                            <th>Data de Nascimento</th>
                                            <th>Ativo</th>
                                            <th>Verificado</th>
                                        </tr> 
                                    </thead>
                                    <tfoot>

                                    </tfoot>
                                    <tbody>
                                        {this.loadFillData()}
                                       </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>

>>>>>>> 5f4dcc453b0ad8009396eb3b1c9ddf252841e4b8
            </div>

          <footer class="sticky-footer bg-white">
              <div class="container my-auto">
                  <div class="copyright text-center my-auto">
                      <span> Room Booker&copy; 2022</span>
                  </div>
              </div>
          </footer>

      </div>
      
  
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
    return this.state.utilizadores.map((data, index) =>{
        return(
            <tr key={index}>
                <div className='boxes'>
                <td>{data.nomeCompleto}</td>
                <button class="button button2">Blue</button>
                </div>
            </tr>
        )
    })
}
}
export default Pagina;

