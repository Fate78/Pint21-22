import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router";
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css'
import '../../CSS/Main.css'
import axios from 'axios';
import Image from '../../img/user.png'
import { Link, useNavigate } from 'react-router-dom';
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {

    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    const [submit, setSubmit] = useState({
        palavraPasse: "",
        password_Verificada: "",
    })

    function handleChange(event) {
        const { name, value } = event.target
        setSubmit(prevFormData => ({
            ...prevFormData,
            [name]: value
        }))

    }

    const changePassword = async (e) => {
        axios.post(baseUrl + "/utilizadores/changepassword/" + accesstoken + "/" + submit.palavraPasse)
            .then(data => {
                setSubmit({
                    palavraPasse: data.data.palavraPasse,
                    password_Verificada: data.data.password_Verificada
                })
            })
            .catch(err => {
                console.log(err)
            })
    }

    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="container">
                <form>
                    <p>Necessita de mudar a palavra-passe para puder continuar a usar o website. </p>
                    <p><input name="palavraPasse" onChange={handleChange} value={submit.palavraPasse} placeholder="Palavra-Passe" /></p>
                    <p>
                        <Link to={(-1)}>
                            <button className='btn btn-primary' onClick={changePassword} >Alterar</button>
                        </Link>
                    </p>
                </form>
            </div>
        </div >
    );


}




