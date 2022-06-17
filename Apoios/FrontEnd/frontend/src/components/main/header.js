import React from 'react';
import '../../assets/css/CSS_HTML.css';
import '../../assets/css/bootstrap.css';
import '../../assets/css/bootstrap.min.css';
import { NavDropdown, Nav, Navbar, Container } from 'react-bootstrap';

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            redirectMain: false, //saber qual a próxima rota
            redirectPathMain: '', //rota para redirecionar
            menuMain: sessionStorage.getItem('menu'),
            firstInMain: true
        }
    }

    componentDidMount() {
    }

    render() {
        return (
          <header>
           <Navbar variant="dark" bg="dark" expand="lg">
             <Container fluid>
                  <a class="navbar-brand" href="/inicio">Aplicações para Internet II</a>
                  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                  </button>
                  <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav mr-auto">
                    <span class="navbar-text">
                    <Navbar.Collapse id="navbar-dark-example">
                      <Nav>
                        <NavDropdown id="nav-dropdown-dark-example" title="Locais" menuVariant="dark" >
                          <NavDropdown.Item style={{ color: 'black' }} href="/zonalist">Zonas</NavDropdown.Item>
                          <NavDropdown.Item style={{ color: 'black' }} href="/diario">Zonas mais concentradas hoje</NavDropdown.Item>
                          <NavDropdown.Item style={{ color: 'black' }} href="/semanal">Zonas mais concentradas na última semana</NavDropdown.Item>
                          <NavDropdown.Item style={{ color: 'black' }} href="/zonamaisconcentrada">Zonas mais concentradas</NavDropdown.Item>
                        </NavDropdown>
                      </Nav>
                    </Navbar.Collapse>
                      
                    </span>
                    <span class="navbar-text">
                    <Navbar.Collapse id="navbar-dark-example">
                      <Nav>
                        <NavDropdown id="nav-dropdown-dark-example" title="Utilizadores" menuVariant="dark">
                          <NavDropdown.Item style={{ color: 'black' }} href="/utilizadorlist">Utilizadores</NavDropdown.Item>
                        </NavDropdown>
                      </Nav>
                    </Navbar.Collapse>
                      
                    </span>
                      <Navbar.Collapse id="navbar-dark-example">
                      <Nav>
                        <NavDropdown id="nav-dropdown-dark-example" title="Reports" menuVariant="dark">
                          <NavDropdown.Item style={{ color: 'black' }} href="/reportlist">Todos os reports</NavDropdown.Item>
                        </NavDropdown>
                      </Nav>
                    </Navbar.Collapse>
                    </ul>
                    <span class="navbar-text">
                    <Navbar.Collapse id="navbar-dark-example">
                    <img src="/Imagens/perfil.png" />
                      <Nav>
                        <NavDropdown alignRight id="nav-dropdown-dark-example" menuVariant="dark">
                          <NavDropdown.Item style={{ color: 'black' }} href="/definicoes">Definições</NavDropdown.Item>
                          <NavDropdown.Item style={{ color: 'black' }} onClick={this.logout.bind(this)}>Terminar sessão</NavDropdown.Item>
                        </NavDropdown>
                      </Nav>
                    </Navbar.Collapse>
                      
                    </span>
                </div>
                </Container>
              </Navbar>
        </header>
        );
    }

    logout(event) {
        sessionStorage.removeItem('userType');
        window.location.replace('http://' + window.location.host + '/login');
    }
}

export default Pagina;
