import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';
import '../../../../assets/css/CSS_HTML.css';

class EditComponent extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            campNome: "",
            campSobrenome:"",
            campNomeconta:"",
            campPass:"",
            campDatanascimento:"",
            campEmail:"",
            campTelemovel:""
        }
}
render(){
    return (
    <div class="dados">
        <div class="form-row justify-content-center">
            <div class="form-group col-md-6">
                <label for="inputPassword4">Nome </label>
                <input type="text" class="form-control" placeholder="Name" value={this.state.campNome} onChange={(value)=> this.setState({campNome:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Sobrenome</label>
                <input type="text" class="form-control" placeholder="Email" value={this.state.campSobrenome} onChange={(value)=> this.setState({campSobrenome:value.target.value})}/>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Nome de conta</label>
                <input type="text" class="form-control" placeholder="Email" value={this.state.campNomeconta} onChange={(value)=> this.setState({campNomeconta:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label for="inputEmail4">Password</label>
                <input type="text" class="form-control" placeholder="Phone" value={this.state.campPass} onChange={(value)=> this.setState({campPass:value.target.value})}/>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Data de nascimento</label>
                <input type="text" class="form-control" placeholder="1234 Main St" value={this.state.campDatanascimento} onChange={(value)=> this.setState({campDatanascimento:value.target.value})}/>
            </div>
            <div class="form-group col-md-6">
                <label for="inputEmail4">Email</label>
                <input type="email" class="form-control" placeholder="Phone" value={this.state.campEmail} onChange={(value)=> this.setState({campEmail:value.target.value})}/>
            </div>
        </div>
        <div class="form-row">
        <div class="form-group col-md-6">
                <label for="inputEmail4">Telemovel</label>
                <input type="text" class="form-control" placeholder="Phone" value={this.state.campTelemovel} onChange={(value)=> this.setState({campTelemovel:value.target.value})}/>
        </div>
        </div>
        <button type="submit" class="btn btn-primary" onClick={()=>this.sendSave()}>Save</button>
    </div>
    );
    }
    sendSave(){
        if (this.state.campNome==="") {
            alert("Insert the Phone!")
        }
        else if (this.state.campSobrenome==="") {
            alert("Insert Name!")
        }
        else if (this.state.campNomeconta==="") {
            alert("Insert Email!")
        }
        else if (this.state.campPass==="") {
            alert("Insert Address!")
        }
        else {
            const baseUrl = "http://localhost:5000/utilizadores/create"
            const datapost = {
                nome: this.state.campNome,
                sobrenome:this.state.campSobrenome,
                nome_conta:this.state.campNomeconta,
                pass:this.state.campPass,
                data_nascimento:this.state.campDatanascimento,
                email:this.state.campEmail,
                telemovel:this.state.campTelemovel
            }
            axios.post(baseUrl,datapost)
            .then(response=>{
                if (response.data.success===true) {
                    alert(response.data.message)
                }
                else {
                    alert(response.data.message)
                }
            }).catch(error=>{
                alert("Error 34 "+error)
                })
            }
        }
    }
    export default EditComponent;