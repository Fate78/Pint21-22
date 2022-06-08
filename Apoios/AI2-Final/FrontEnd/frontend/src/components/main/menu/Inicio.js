import React from 'react';
import '../../../assets/css/CSS_HTML.css'
import '../../../assets/css/bootstrap.css';
import '../../../assets/css/bootstrap.min.css'
import axios from 'axios';


class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            redirectMain: false, //saber qual a próxima rota
            redirectPathMain: '', //rota para redirecionar
            menuMain: sessionStorage.getItem('menu'),
            firstInMain: true,
            statUti:[],
            statReg: [],
            statZona: [],
            statAva: [],
        }
    }
    

    componentDidMount() {
        const url = "http://localhost:5000/stats/utilizadores/ativos"
        axios.get(url)
        .then(res =>{
            if (res.data.success) {
                const data = res.data.data
                this.setState({ statUti: data })
            }
            else {
                alert("Error web service")
            }
        })
        const url1 = "http://localhost:5000/stats/classificados/diarios"
        axios.get(url1)
        .then(res =>{
            if (res.data.success) {
                const data = res.data.data
                this.setState({ statReg: data })
            }
            else {
                alert("Error web service")
            }
        })
        const url2 = "http://localhost:5000/stats/zonas/configuradas"
        axios.get(url2)
        .then(res =>{
            if (res.data.success) {
                const data = res.data.data
                this.setState({ statZona: data })
            }
            else {
                alert("Error web service")
            }
        })
        const url3 = "http://localhost:5000/stats/avatares/registados"
        axios.get(url3)
        .then(res =>{
            if (res.data.success) {
                const data = res.data.data
                this.setState({ statAva: data })
            }
            else {
                alert("Error web service")
            }
        })
    }

    render() {
        return (
          <body>
        <div class="main-header">
            <div style={{ height: '100%', width: '100%' }}>
                Mapa
            </div>
        </div>
        

        <div class="dados aumento">
            <strong>DADOS</strong>
            <div class="linha"></div>
            <div class="dados-caixa"><div class="dados-texto">Utilizadores ativos</div><div class="dados-numero">{this.state.statUti}</div></div>
            <div class="dados-caixa"><div class="dados-texto">Reports diários</div><div class="dados-numero">{this.state.statReg}</div></div>
            <div class="dados-caixa"><div class="dados-texto">Locais configurados</div><div class="dados-numero">{this.state.statZona}</div></div>
            <div class="dados-caixa-1"><div class="dados-texto">Perfis registados</div><div class="dados-numero">{this.state.statAva}</div></div>
        
        </div>

          </body>
        );
    }
}
export default Pagina;