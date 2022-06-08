import React from 'react';
import axios from 'axios';
import 'sweetalert2/src/sweetalert2.scss';

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            listRegistos: []
        }
    }
    
    componentDidMount() {
        const url = "http://localhost:5000/registos/list";
        const url1 = "http://localhost:5000/zonas/list"
        axios.get(url)
        .then(res => {
            if(res.data.success){
                const data = res.data.data;
                this.setState({ listRegistos:data });
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
                            <th scope="col">ID Local</th>
                            <th scope="col">Nota</th>
                            <th scope="col">Data de report</th>
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
        return this.state.listRegistos.map((data, index) =>{
            return(
                <tr>
                    <th>{data.id}</th>
                    <td>{data.id_zona}</td>
                    <td>{data.nota}</td>
                    <td>{data.createdAt}</td>
                </tr>
            )
        })
    }

}

export default Pagina;