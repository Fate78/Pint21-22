import React from 'react';
import axios from 'axios';
import 'sweetalert2/src/sweetalert2.scss';
import '../../../../assets/css/CSS_HTML.css';
import { Link } from "react-router-dom"


class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            listUtilizadores: []
        }
    }
    
    componentDidMount() {
        this.loadUtilizador();
    }

    loadUtilizador() {
        const url = "http://localhost:5000/utilizadores/list";
        axios.get(url)
        .then(res => {
            if(res.data.success){
                const data = res.data.data;
                this.setState({ listUtilizadores:data });
            }else{
                alert("Error Web Service!");
            }
        })
        .catch(error => {
            alert(error)
        });
    }

    render() {
        return(
            <div class="dados">
                <table class="table table-striped table-dark table-bordered">
                    <thead calss="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Nome de conta</th>
                            <th scope="col">Email</th>
                            <th scope="col">Telemóvel</th>
                            <th scope="col">Pontos</th>
                            <th colSpan="3">Ações</th>
                        </tr>
                    </thead>
                    <tbody class="tbody-light">
                        {this.loadFillData()}
                    </tbody>
                </table>
            </div>
        );
    }

    loadFillData() {
        return this.state.listUtilizadores.map((data, index) =>{
            return(
                <tr key={index}>
                    
                    <th>{data.id}</th>
                    <td>{data.nome}&nbsp;{data.sobrenome}</td>
                    <td>{data.nome_conta}</td>
                    <td>{data.email}</td>
                    <td>{data.telemovel}</td>
                    <td>{data.pontos}</td>
                    <td><Link class="btn btn-outline-info " to={"/utilizador/"+data.id} >Ver</Link></td>
                </tr>
            )
        })
    }

}

export default Pagina;