import axios from "axios"

export default function Pagina() {

    const accesstoken = localStorage.getItem("token")
        axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    return(
        <div>
            Ola
        </div>
    )
}