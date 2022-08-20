import axios from "axios";
import { useEffect, useState } from "react";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function NumUtilizadores() {
    
    const [numUtil, setNumUtil] = useState();

    useEffect(() =>{
        axios.get( baseUrl + "/utilizadores")
        .then((nUtil) =>{
            setNumUtil(
                nUtil.data.length
            )
        })
        .catch((err) => {
            console.log(err)
        })
    }, [])

    return (
        <div className="App">
            <div>
                Utilizadores registados {numUtil}
            </div>
        </div>
    );
}