import React, { useState } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom"
import '../../assets/bootstrap/bootstrap/css/bootstrap.css';
import '../../assets/CSS/stylesdashboard1.css';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

class Pagina extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            campDescr: "",
            campRaio:"",
            campLatitude:"",
            campLongitude:""
        }
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

    sendUpdate(){
        // get parameter id
        let zonaid = this.props.match.params.id;
        // url de backend
        const url = baseUrl+"/zonas/update/"+zonaid
        // parametros de datos post
        const datapost = {
            descr : this.state.campDescr,
            raio : this.state.campRaio,
            latitude : this.state.campLatitude,
            longitude : this.state.campLongitude
        }
        axios.post(url,datapost)
        .then(response=>{
            if (response.data.success===true) {
                alert(response.data.message)
                this.props.history.push('/zonalist');
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
                <label for="inputPassword4">Nome</label>
                <input type="text" class="form-control" placeholder="Nome" value={this.state.campDescr} onChange={(value)=> this.setState({campDescr:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label for="inputEmail4">Raio</label>
                <input type="email" class="form-control" placeholder="Raio" value={this.state.campRaio} onChange={(value)=> this.setState({campRaio:value.target.value})}/>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Latitude</label>
                <input type="number" class="form-control" placeholder="Latitude" value={this.state.campLatitude} onChange={(value)=> this.setState({campLatitude:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label for="inputEmail4">Longitude</label>
                <input type="number" class="form-control" placeholder="Longitude" value={this.state.campLongitude} onChange={(value)=> this.setState({campLongitude:value.target.value})}/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" onClick={()=>this.sendUpdate()}>Update</button>
    </div>
    );
    }
    }
    export default Pagina;