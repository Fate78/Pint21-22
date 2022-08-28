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

    function SendEmail() {
        axios.post(baseUrl + "/utilizadores/sendemail/" + utilizadores.email)
            .then(data => {
                setUtilizadores(
                    data.data
                )

            })
            .catch(err => {
                console.log(err)
            })
    }

    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <p>Para puder proceder tem de confirmar o seu email!</p>
            <form>
                <p>
                    <Link to={`/emailconfirmar`}>
                        <button onClick={SendEmail}>
                            Enviar email de confirmação
                        </button>
                    </Link>
                </p>
            </form>
        </div>
    )
}