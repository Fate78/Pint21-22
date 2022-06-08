import React from 'react';
import axios from 'axios';
import 'sweetalert2/src/sweetalert2.scss';
import '../../../../assets/css/CSS_HTML.css';

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            listZonas: []
        }
    }
    
    componentDidMount() {
        this.loadUtilizador();
    }

    loadUtilizador() {
        const url = "http://localhost:5000/registos/list";
        axios.get(url)
        .then(res => {
            if(res.data.success){
                const data = res.data.data;
                this.setState({ listZonas:data });
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
                            <th scope="col">Zona</th>
                            <th scope="col">Concentração</th>
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
        return this.state.listZonas.map((data, index) =>{
            return(
                <tr key={index}>
                    
                    <th>{data.id}</th>
                    <td>{data.id_zona}</td>
                    <td>{data.id_dens}</td>
                </tr>
            )
        })
    }

}

export default Pagina;