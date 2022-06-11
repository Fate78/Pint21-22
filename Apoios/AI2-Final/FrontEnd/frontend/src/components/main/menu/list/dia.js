import React from 'react';
import axios from 'axios';
import 'sweetalert2/src/sweetalert2.scss'

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            Dia: []
        }
    }
    
    componentDidMount() {
        this.loadZona();
    }

    loadZona() {
        const url = "http://localhost:5000/stats/registodia";
        axios.get(url)
            .then(res => {
                if (res.data.success) {
                    const data = res.data.data;
                    this.setState({ Dia: data });
                } else {
                    alert("Error Web Service!");
                }
            })
            .catch(error => {
                alert(error)
            });
    }

    render() {
        return(
            <body>
            <div class="dados">
                <table class="table table-striped table-dark table-bordered">
                    <thead calss="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">ID Zona</th>
                            <th scope="col">Concentração</th>
                        </tr>
                    </thead>
                    <tbody class="tbody-light">
                        {this.loadFillData()}
                    </tbody>
                </table>
            </div>
            </body>
        );
    }    

    loadFillData() {
        return this.state.Dia.map((data, index) =>{
            return(
                <tr key={index}>
                    <th>{data.id}</th>
                    <td>{data.id_zona}</td>
                    <td>{data.nota}</td>
                </tr>
            )
        })
    }

}

export default Pagina;