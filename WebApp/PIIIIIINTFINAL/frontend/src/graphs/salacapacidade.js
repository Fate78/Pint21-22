import React, { useEffect, useState } from "react";
import axios from "axios";
import BarChart from "../menu/BarChar";
import DatePicker from "react-datepicker";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function SalaCapacidade() {

    const [state, setState] = useState();

    const [graph, setGraph] = useState({
        labels: [],
        datasets: [],
    });

    

    useEffect(() => {
        
        axios.get(baseUrl + "/reservas/utilSalas")
            .then(data => {            
                    setState(
                        data.data
                    )               
                
            })
            .catch(err => {
                console.log(err);
            });
        setGraph({
            labels: state?.map((data) => data.nSala),
            
            datasets: [
                {
                    label: "Percentagem de Salas mais utilizadas",
                    data: state?.map((data) => data.percentUtilizacao),
                    backgroundColor: [
                        "rgba(75,192,192,1)",
                        "#ecf0f1",
                        "#50AF95",
                        "#f3ba2f",
                        "#2a71d0",
                    ],
                    borderColor: "black",
                    borderWidth: 2,
                },
            ],
        })
    }, [state])
    return (
        
        <div style={{ width: 500 }}>
            <BarChart chartData={graph} />
        </div>
    )

}
