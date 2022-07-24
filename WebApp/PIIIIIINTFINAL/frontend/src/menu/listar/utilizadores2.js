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
            camp_idUtilizador: '',
            camp_idTipo: '',
            camp_nomeUtilizador: '',
            camp_nomeCompleto: '',
            camp_email: '',
            camp_verificado: '',
            camp_ativo: '',
          }
    };

    componentDidMount() {
        const url = baseUrl+"/utilizadores/"
        axios.get(url)
        .then(res=>{
            if (res.data.success) {
                const data = res.data.data[0]
                this.setState({
                    camp_idUtilizador:   data.idUtilizador,
                    camp_idTipo:         data.idTipo,
                    camp_nomeUtilizador: data.nomeUtilizador,
                    camp_nomeCompleto:   data.nomeCompleto,
                    camp_email:          data.email,
                    camp_verificado:     data.verificado,
                    camp_ativo:          data.ativo,
                 
                })
                console.log(JSON.stringify(data))
            }
            else {
                alert("Error web service")
            }
        })
        .catch(error=>{
            alert("Error server: "+error)
        })
    }

    sendUpdate(){
        // url de backend
        const url = baseUrl+"/utilizadores/"
        // parametros de datos post
        const datapost = {
            idUtilizador:   this.state.camp_idUtilizador,
            idTipo:         this.state.camp_idTipo,
            nomeUtilizador: this.state.camp_nomeUtilizador,
            nomeCompleto:   this.state.camp_nomeCompleto,
            email:          this.state.camp_email,
            verificado:     this.state.camp_verificado,
            ativo:          this.state.camp_ativo,

        }
        axios.post(url,datapost)
        .then(response=>{
            if (response.data.success===true) {
                alert(response.data.message)
                this.props.history.push('/utilizadores/');
            }
            else {
                alert("Error")
            }
        })
        .catch(error=>{
            alert("Error 34 "+error)
        })
        }
        
    render(){
    return (
    <div>
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label> ID Utilizador </label>
                <input type="text" class="form-control" placeholder="ID Utilizador" value={this.state.camp_idUtilizador} onChange={(value)=> this.setState({camp_idUtilizador:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label> ID Tipo </label>
                <input type="text" class="form-control" placeholder="ID Tipo" value={this.state.camp_idTipo} onChange={(value)=> this.setState({camp_idTipo:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label> Nome de Utilizador </label>
                <input type="text" class="form-control" placeholder="Nome de Utilizador" value={this.state.camp_nomeUtilizador} onChange={(value)=> this.setState({camp_nomeUtilizador:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label> Nome Completo </label>
                <input type="text" class="form-control" placeholder="Nome Completo" value={this.state.camp_nomeCompleto} onChange={(value)=> this.setState({camp_nomeCompleto:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label> Email </label>
                <input type="text" class="form-control" placeholder="Email" value={this.state.camp_email} onChange={(value)=> this.setState({camp_email:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label> Verificado </label>
                <input type="text" class="form-control" placeholder="Verificado (true ou false)" value={this.state.camp_verificado} onChange={(value)=> this.setState({camp_verificado:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label> Ativo </label>
                <input type="text" class="form-control" placeholder="Ativo (true ou false)" value={this.state.camp_ativo} onChange={(value)=> this.setState({camp_ativo:value.target.value})}/>
            </div>

        </div>
        <button type="submit" class="btn btn-primary" onClick={()=>this.sendUpdate()}>Update</button>
    </div>
    );
    }
    }
    export default Pagina;