import React, { useEffect, useState, useRef } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import '../../CSS/stylesdashboard1.css';
import '../../CSS/style.css'
import axios from "axios"
import useAuth from '../../hooks/useAuth';
import { AuthProvider } from '../../context/AuthProvider';

const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {
    const { setAuth } = useAuth();

    const [utilizador, setUtilizador] = useState();

    const navigate = useNavigate();
    let location
    const verify = "/verificar"

    const userRef = useRef();
    const errRef = useRef();

    const [user, setUser] = useState('');
    const [password, setPassword] = useState('');
    const [errMsg, setErrMsg] = useState('');


    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [user, password])



    const handleSubmit = async (e) => {
        e.preventDefault();
        let token
        try {
            const response = await axios.post(baseUrl + "/utilizadores/authenticate",
                { UtilizadorInput: user, PalavraPasse: password },
                {
                    Headers: { 'Content-Type': 'application/json' },
                },
            )
            
            const { token } = response.data;
            
            localStorage.setItem("token", token);
            localStorage.setItem("user", user);
            localStorage.setItem("password", password);
            const accesstoken = localStorage.getItem("token")
            axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`
            const get = await axios.get(baseUrl + "/utilizadores/" + user)
                    setUtilizador(
                        get.data
                    )
                    
                console.log(get)
            console.log(user, password)
            setUser(user);
            setPassword('');
            setAuth({ user, password });

            //await Util()

            if(get.data.password_Verificada && get.data.email_Verificado) {
                location = "/dashboard"
            } else if(!get.data.password_Verificada) {
                location = "/verificar"
            } else if(!get.data.email_Verificado) {
                location = "/emailverificar"
            }
            //const location = "/dashboard"
            navigate(location, { replace: true });

            console.log(user, password)
            console.log(get.data.passwordVerificada)
            
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No server response');
            } else if (err.response?.status === 400) {
                setErrMsg('Missing username or password')
            } else if (err.response?.status === 401) {
                setErrMsg('Sem autorização')
            } else {
                setErrMsg('Login failed')
            }
            errRef.current.focus();
        }

    }

    /*async function Util() {
        const accesstoken = localStorage.getItem("token")
        axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`


        axios.get(baseUrl + "/utilizadores/" + user)
            .then(data => {
                setUtilizador(
                    data.data
                )
            })
            .catch(err => {
                console.log(err)
            })
        console.log(utilizador)
    }
*/

    return (
        <div className="vh-100">
            <div className="container-fluid h-custom">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-md-9 col-lg-6 col-xl-5">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" className="img-fluid" alt="Sample image" />
                    </div>
                    <div className="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive"> {errMsg} </p>
                        <h1>Entrar</h1>
                        <form onSubmit={handleSubmit}>
                            <div className="form-outline mb-4">
                                <label htmlFor='password' className="form-label"> Nome de Utilizador:</label>
                                <input type='text'
                                    id='username'
                                    ref={userRef}
                                    autoComplete='off'
                                    onChange={(e) => setUser(e.target.value)}
                                    value={user}
                                    required
                                    className="form-control form-control-lg"
                                />
                            </div>
                            <div className="form-outline mb-3">
                                <label htmlFor='password' className="form-label"> Password:</label>
                                <input type='password'
                                    id='password'
                                    onChange={(e) => setPassword(e.target.value)}
                                    value={password}
                                    required
                                    className="form-control form-control-lg"
                                />
                            </div>
                            <button>Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}




