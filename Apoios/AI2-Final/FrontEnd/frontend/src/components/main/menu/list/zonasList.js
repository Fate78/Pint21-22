import React from 'react';
import axios from 'axios';
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'
import { Link } from "react-router-dom"

class Pagina extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            listZonas: []
        }
    }
    
    componentDidMount() {
        this.loadZona();
    }

    loadZona() {
        const url = "http://localhost:5000/zonas/list";
        axios.get(url)
            .then(res => {
                if (res.data.success) {
                    const data = res.data.data;
                    this.setState({ listZonas: data });
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
                            <th scope="col">Nome</th>
                            <th scope="col">Raio</th>
                            <th scope="col">Latitude</th>
                            <th scope="col">Longitude</th>
                            <th colSpan="3">Ações</th>
                        </tr>
                    </thead>
                    <tbody class="tbody-light">
                        {this.loadFillData()}
                    </tbody>
                </table>
                <td><Link class="btn btn-outline-info " to={"/criarzona"} >Criar</Link></td>
            </div>
            </body>
        );
    }

    onDelete(id){
        Swal.fire({
        title: 'Are you sure?',
            text: 'You will not be able to recover this imaginary file!',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, keep it'
        }).then((result) => {
            if (result.value) {
                this.sendDelete(id)
            } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire(
                'Cancelled',
                'Your imaginary file is safe :)',
                'error'
        )
        }
    })
    }

    sendDelete(id) {
        // url do backend
        const baseUrl = "http://localhost:5000/zonas/delete"
        // network
        axios.post(baseUrl, {
            id: id
        })
            .then(response => {
                if (response.data.success) {
                    Swal.fire(
                        'Deleted!',
                        'Your employee has been deleted.',
                        'success'
                    )
                    this.loadZona();
                }
            })
            .catch(error => {
                alert("Error 325 ")
            })
    }
        

    loadFillData() {
        return this.state.listZonas.map((data, index) =>{
            return(
                <tr key={index}>
                    <th>{data.id}</th>
                    <td>{data.descr}</td>
                    <td>{data.raio}</td>
                    <td>{data.latitude}</td>
                    <td>{data.longitude}</td>
                    <td><Link class="btn btn-outline-info " to={"/zona/"+data.id} >Ver</Link></td>
                    <td><Link class="btn btn-outline-info " to={"/editar/"+data.id} >Edit</Link></td>
                    <td><button class="btn btn-outline-danger" onClick={()=>this.onDelete(data.id)}> Delete </button></td>
                </tr>
            )
        })
    }

}

export default Pagina;