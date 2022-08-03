import React, { useState } from 'react';
import '../CSS/stylesdashboard1.css';
import { Link } from "react-router-dom";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            gestaosalas: [],
        };

    }

    componentDidMount() {
        // get all entities - GET

        this.loadGestao();


    }

    loadGestao() {
        fetch(baseUrl + "/salas", {
            "method": "GET",
            "headers": {
                "Accept": "application/json",
                "Access-Control-Allow-Origin": "true"
            }
        })
            .then(response => response.json())
            .then(response => {

                this.setState({
                    gestaosalas: response
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
                    <h1 class="h3 mb-4 text-gray-800">Gestão de Salas</h1>

                    <div className='box'>


                        {this.loadFillData()}

                    </div>
                </div>

            </div>


        );
    }

    loadFillData() {

        return this.state.gestaosalas.map((data, index) => {
            return (
                <tr key={index}>

                    {this.limpo()}
                    {this.ativo()}
                    <div className='boxes'>
                        <div className='salaNome'>
                            <Link to={`/sala/${data.idSala}`}>
                                <p>Sala {data.nSala}</p>
                            </Link>
                        </div>
                        <hr />
                        <p>Lotação máxima: {data.lotacaoMax}</p>
                        <p>Tempo de Limpeza: {data.tempoMinLimp}</p>
                        <p>{data.limpo}</p>
                        <p>{data.ativo}</p>
                    </div>
                </tr>
            )
        })
    }

    limpo() {
        return this.state.gestaosalas.map((data, index) => {
            if (data.limpo = 'true') {
                data.limpo = <p>Encontra-se limpo!!</p>
            }
            else {
                data.limpo = <p>Limpeza urgente!!</p>
            }
        })
    }

    ativo() {
        return this.state.gestaosalas.map((data, index) => {
            if (data.ativo = 'true') {
                data.ativo = <p>Sala encontra-se disponível.</p>
            }
            else {
                data.ativo = <p>Sala não se encontra disponível.</p>
            }
        })
    }

    sala() {
        return this.state.gestaosalas.map((data, index) => {
            <p>Sala {data.nSala}</p>
        })
    }



}
export default Pagina;
