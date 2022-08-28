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

    const [state, setState] = useState({
        value: ""
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
        setState({ value: event.target.value });
    }

    function VerifyEmail() {
        axios.post(baseUrl + "/utilizadores/VerifyEmailCode/" + state.value + "/" + utilizadores.email)
            .then(data => {
                setUtilizadores(
                    data.data
                )

            })
            .catch(err => {
                console.log(err)
            })
    }

    const { value } = state;

    return (
        
        <div id="content-wrapper" class="d-flex flex-column">
            <p>Para puder proceder tem de confirmar o seu email!</p>
            <form>
                <input
                    id="codigo"
                    type="text"
                    value={value}
                    onChange={handleChange}
                    required
                />
                <Link to={`/login`} >
                <button onClick={VerifyEmail}>
                    Verificar
                </button>
                </Link>
            </form>
        </div>
    )
}