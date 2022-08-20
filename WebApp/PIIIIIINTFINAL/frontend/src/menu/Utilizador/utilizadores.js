import React, { useState } from 'react';
import '../../CSS/stylesdashboard1.css';
import { Link } from "react-router-dom";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            utilizadores: [],
        };

    }

    componentDidMount() {
        // get all entities - GET

        this.loadGestao();


    }

    loadGestao() {
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
                    utilizadores: response
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
                    <h1 class="h3 mb-4 text-gray-800">Utilizadores</h1>



                    <div className='box'>


                        {this.loadFillData()}

                    </div>
                </div>

            </div>


        );
    }

    loadFillData() {

        return this.state.utilizadores.map((data, index) => {

            
                return (

                    <tr key={index}>
                        <div className='boxes'>
                        <div className='salaNome'>
                                <Link to={`/utilizador/${data.idUtilizador}`}>
                                    <p>{data.nomeUtilizador}</p>
                                </Link>
                            </div>
                        </div>
                    </tr>
                )
            
        })
    }

}
export default Pagina;
