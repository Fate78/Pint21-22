import axios from "axios"
import { useEffect, useState } from "react"
import { Link } from "react-router-dom";

const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function Pagina() {

    const accesstoken = localStorage.getItem("token")
    const user = localStorage.getItem("user")
    const password = localStorage.getItem("password")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    

    const [utilizadores, setUtilizadores] = useState({
        idUtilizador: "",
        idTipo: "",
        nomeUtilizador: "",
        nomeCompleto: "",
        palavraPasse: "",
        email: "",
        dataNascimento: "",
        email_Verificado: "",
        password_Verificada: "",
        ativo: "",
        reservas: [],
        tickets: [],
        utilizadorCentros: []
    });
    
    const [submit, setSubmit] = useState({
        palavraPasse: ""
    })

    useEffect(() => {
        axios.get(baseUrl + "/utilizadores/" + user)
        .then(data => {
            setUtilizadores(
                data.data
            )
            
        })
        .catch(err => {
            console.log(err)
        })
    }, [])
    console.log(utilizadores)
    

    function handleChange(event) {
        const { name, value } = event.target
        setSubmit(prevFormData => ({
            ...prevFormData,
            [name]: value
        }))
        
    }

    function useUpdate() {
        // update entity - PUT
        axios.put(baseUrl + "/utilizadores/" + utilizadores.idUtilizador,
            {
                idUtilizador: utilizadores.idUtilizador,
                idTipo: utilizadores.idTipo,
                nomeUtilizador: utilizadores.nomeUtilizador,
                nomeCompleto: utilizadores.nomeCompleto,
                palavraPasse: submit.palavraPasse === "" ? utilizadores.palavraPasse : submit.palavraPasse,
                email: utilizadores.email,
                dataNascimento: utilizadores.dataNascimento,
                email_Verificado: utilizadores.email_Verificado,
                password_Verificada: utilizadores.password_Verificada = true,
                ativo: utilizadores.ativo,
                reservas: utilizadores.reservas,
                tickets: utilizadores.tickets,
                utilizadorCentros: utilizadores.utilizadorCentros,
            }
        )
            .then(data => {
                console.log(data)
                setUtilizadores(anterior => ({
                    ...anterior,
                    ...submit
                }))
            })
            .catch(err => {
                console.log(err);
            })
    }

    return (
        <div id="content-wrapper" class="d-flex flex-column">
             <div className="container">
                <div className='budy'>
                    <form>
                        <p>Necessita de mudar a palavra-passe para puder continuar a usar o website. </p>
                        <p><input name="palavraPasse" onChange={handleChange} value={submit.palavraPasse} placeholder="Palavra-Passe" /></p>
                        <p>
                        <Link to={`/login`}>
                            <button className='btn btn-primary' onClick={useUpdate}>Alterar</button>
                        </Link>
                        </p>
                    </form>
                </div>
             </div>
        </div>
    )
}