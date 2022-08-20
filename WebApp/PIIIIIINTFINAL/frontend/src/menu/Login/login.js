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

    

    const navigate = useNavigate();
    const location = "/dashboard"

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

        try {
            const response = await axios.post(baseUrl + "/utilizadores/authenticate",
                {UtilizadorInput: user, PalavraPasse: password},
                {
                    Headers: { 'Content-Type': 'application/json' },
                },
            );
            const { token } = response.data;
            localStorage.setItem("token", token);
            console.log(JSON.stringify(response?.data))
            setUser('');
            setPassword('');
            setAuth({ user, password });
            navigate(location, { replace: true });
            
            console.log(user, password)

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

    return (
        <div>
            <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive"> {errMsg} </p>
            <h1>Entrar</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor='password'> Nome de Utilizador:</label>
                <input type='text'
                    id='username'
                    ref={userRef}
                    autoComplete='off'
                    onChange={(e) => setUser(e.target.value)}
                    value={user}
                    required
                />

                <label htmlFor='password'> Password:</label>
                <input type='password'
                    id='password'
                    onChange={(e) => setPassword(e.target.value)}
                    value={password}
                    required
                />
                <button>Entrar</button>
            </form>
            <p>
                Queres criar uma conta?<br />
                <span className='line'>
                    {/*Router Link*/}
                    <a href="#">Registar</a>
                </span>
            </p>
        </div>
    );
}




