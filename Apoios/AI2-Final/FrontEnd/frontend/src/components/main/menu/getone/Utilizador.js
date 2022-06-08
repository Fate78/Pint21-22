import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';


class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            campNome: "",
            campSobrenome:"",
            campNomeconta:"",
            campEmail:"",
            campIdade:"",
            campTelemovel:"",
            campPontos:""
        }
    } 
    
    componentDidMount() {
        let utilizadorId = this.props.match.params.id;
        const url = "http://localhost:5000/utilizadores/get/" + utilizadorId
        axios.get(url)
            .then(res => {
                if (res.data.success) {
                    const data = res.data.data
                    this.setState({ 
                        campNome: data.nome,
                        campSobrenome: data.sobrenome,
                        campNomeconta: data.nome_conta,
                        campEmail: data.email,
                        campIdade: data.data_nascimento,
                        campTelemovel: data.telemovel,
                        campPontos: data.pontos
                     })
                     console.log(JSON.stringify(data))
                }
                else {
                    alert("Error web service")
                }
            })
            .catch(error => {
                alert("Error server: " + error)
            })
    }
    render() {
        const { currentUser } = this.state;
        return (
            <div>
                <div class="dados2">
                    <strong>FOTO DE PERFIL</strong>
                    <div class="linha"></div>

                    <img class="fotoperfil" src="/Imagens/Foto.png" /><br></br>
                    <div class="mail centro">

                                <strong><span class="borderinfotele">{this.state.campNomeconta}</span></strong>


                            </div>

                    <div class="linha"></div>

                    <div class="social">

                        <div class="morada">
                            <div class="mail">
                                <span class="borderinfomail"><strong>NOME</strong></span>
                                <span class="borderinfotele">{this.state.campNome}&nbsp;{this.state.campSobrenome}</span>
                            </div>
                            <div class="linha"></div>
                            <div class="mail">

                                <span class="borderinfomail"><strong>E-MAIL</strong></span>
                                <span class="borderinfotele">{this.state.campEmail}</span>


                            </div>
                            <div class="linha"></div>
                            <div class="mail">

                                <span class="borderinfomail"><strong>DATA DE NASCIMENTO</strong></span>
                                <span class="borderinfotele">{this.state.campIdade}</span>


                            </div>
                            <div class="linha"></div>
                            <div class="mail">

                                <span class="borderinfomail"><strong>TELEMÓVEL</strong></span>
                                <span class="borderinfotele">{this.state.campTelemovel}</span>
                            </div>
                            <div class="linha"></div>
                            <div class="mail">

                                <span class="borderinfomail"><strong>PONTOS</strong></span>
                                <span class="borderinfotele">{this.state.campPontos}</span>
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