import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';
const baseUrl = "http://localhost:5000";

class Pagina extends React.Component{
    constructor(props){
        super(props);
        this.state = {
                descr: "",
                raio: "",
                latitude: "",
                longitude: ""
        }
    }
    componentDidMount(){
        let zonaId = this.props.match.params.id
        const url = baseUrl+"/zonas/get/"+zonaId
        axios.get(url)
        .then(res=>{
            if (res.data.success) {
                const data = res.data.data
                this.setState({
                    descr: data.descr,
                    raio:data.raio,
                    latitude:data.latitude,
                    longitude:data.longitude
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

    render(){
        return (
        <div>
            <div class="dados2">
                <strong>FOTO DE PERFIL</strong>
                <div class="linha"></div>
                
                <img class="fotoperfil" src="/Imagens/Foto.png" />

                <div class="linha"></div>

                <strong>DEFINIÇÕES GERAIS</strong>
                <div class="linha"></div>

                <div class="social">

                  <div class="morada">
                      <div class="mail">
                          <span class="borderinfomail"><strong>NOME</strong></span>
                          <span class="borderinfotele">{this.state.descr}</span>
                      </div>
                      <div class="linha"></div>
                      <div class="mail">

                          <span class="borderinfomail"><strong>RAIO</strong></span>
                          <span class="borderinfotele">{this.state.raio}</span>


                      </div>
                      <div class="linha"></div>
                      <div class="mail">

                          <span class="borderinfomail"><strong>LATITUDE</strong></span>
                          <span class="borderinfotele">{this.state.latitude}</span>


                      </div>
                      <div class="linha"></div>
                      <div class="mail">

                          <span class="borderinfomail"><strong>LONGITUDE</strong></span>
                          <span class="borderinfotele">{this.state.longitude}</span>
                      </div>
                      <div class="linha"></div>
                     
                  </div>

              </div>

              </div>
        </div>
        );
    }
}
export default Pagina;