import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            campDescr: "",
            campRaio: "",
            campLatitude: "",
            campLongitude: ""
        }
    } 
    render() {
        return (
            <div class="dados">
                <div class="form-row justify-content-center">
                    <div class="form-group col-md-6">
                        <label for="inputPassword4">Nome </label>
                        <input type="text" class="form-control" placeholder="Nome" value={this.state.campDescr} onChange={(value) => this.setState({ campDescr: value.target.value })} />
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputEmail4">Raio</label>
                        <input type="text" class="form-control" placeholder="Raio" value={this.state.campRaio} onChange={(value) => this.setState({ campRaio: value.target.value })} />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputEmail4">Latitude</label>
                        <input type="text" class="form-control" placeholder="Latitude" value={this.state.campLatitude} onChange={(value) => this.setState({ campLatitude: value.target.value })} />
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputEmail4">Longitude</label>
                        <input type="text" class="form-control" placeholder="Longitude" value={this.state.campLongitude} onChange={(value) => this.setState({ campLongitude: value.target.value })} />
                </div>
                </div>
                <button type="submit" class="btn btn-primary" onClick={() => this.sendSave()}> Save </button>
            </div>
        );
    }
    sendSave() {
        if (this.state.campDescr === "") {
            alert("Insert the Phone!")
        }
        else if (this.state.campRaio === "") {
            alert("Insert Name!")
        }
        else if (this.state.campLatitude === "") {
            alert("Insert Email!")
        }
        else if (this.state.campLongitude === "") {
            alert("Insert Address!")
        }
        else {
            const baseUrl = "http://localhost:5000/zonas/create"
            const datapost = {
                descr: this.state.campDescr,
                raio: this.state.campRaio,
                latitude: this.state.campLatitude,
                longitude: this.state.campLongitude
            }
            axios.post(baseUrl, datapost)
                .then(response => {
                    if (response.data.success === true) {
                        alert(response.data.message)
                    }
                    else {
                        alert(response.data.message)
                    }
                }).catch(error => {
                    alert("Error 34 " + error)
                })
        }
    }
}
export default Pagina;