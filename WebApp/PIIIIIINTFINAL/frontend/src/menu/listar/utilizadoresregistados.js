import React, { useState } from 'react';
import '../CSS/stylesdashboard1.css';
import { Link } from "react-router-dom";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            utilizadoresregistados: [],
        };

    }

    componentDidMount() {
        // get all entities - GET

        this.loadUtilizadoresReg();


    }

    loadUtilizadoresReg() {
        fetch(baseUrl + "/utilizadores", {
            "method": "GET",
            "headers": {
                "Accept": "application/json",
                "Access-Control-Allow-Origin": "true"
            }
        })
            .then(response => response.json())
            .then(response => {

                this.setState({
                    utilizadoresregistados: response
                })

            })
            .catch(err => {
                console.log(err);
            });
    }


    render() {
        return (
            <div id="content-wrapper" class="d-flex flex-column">

                <div className="main">
                    <h1 class="h3 mb-4 text-gray-800">Utilizadores Registados</h1>

                    <div className='box'>


                        {this.loadFillData()}

                    </div>
                </div>

            </div>


        );
    }

    loadFillData() {

        return this.state.utilizadoresregistados.map((data, index) => {
            return (
                <tr key={index}>

                    {this.ativo()}
                    {this.verificado()}
                    
                    <div className='boxes'>
                        <div className='salaNome'>
                            <Link to={`/utilizador/${data.idUtilizador}`}>
                                <p>{data.nomeUtilizador}</p>
                            </Link>
                        </div>
                        <hr />
                        <p>Nome Completo: {data.nomeCompleto}</p>
                        <p>Email: {data.email}</p>
                        <p>{data.ativo}</p>
                        <p>{data.verificado}</p>
                    </div>
                </tr>
            )
        })
    }

    verificado() {
        return this.state.utilizadoresregistados.map((data, index) => {
            if (data.verificado === true) {
                data.verificado = <p> Verificado</p>
            }
            else if(data.verificado === false) {
                data.verificado = <p>Não Verificado</p>
            }
        })
    }

    ativo() {
        return this.state.utilizadoresregistados.map((data, index) => {
            if (data.ativo === true) {
                data.ativo = <p>Estado: Ativo</p>
            }
            else if(data.ativo === false) {
                data.ativo = <p>Estado: Inativo</p>
            }
        })
    }

    user() {
        return this.state.utilizadoresregistados.map((data, index) => {
            <p>Utilizador {data.nomeUtilizador}</p>
        })
    }



}
export default Pagina;