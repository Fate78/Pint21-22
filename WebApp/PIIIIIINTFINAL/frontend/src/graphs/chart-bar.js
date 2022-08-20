import React, { useEffect, useState } from "react";
import axios from "axios";
import BarChart from "../menu/BarChar";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function ChartBar() {

    const [state, setState] = useState();
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(null);

    const [graph, setGraph] = useState({
        labels: [],
        datasets: [],
    });

    const onChange = (dates) => {
        const [start, end] = dates;
        setStartDate(start);
        setEndDate(end);
      };

    useEffect(() => {
        axios.get(baseUrl + "/reservas/alocacaodiaria/2022/8")
            .then(data => {
                setState(
                    data.data
                )
            })
            .catch(err => {
                console.log(err);
            });
        setGraph({
            labels: state?.map((data) => data.dataReserva),
            datasets: [
                {
                    label: "Percentagem de Alocação Diária",
                    data: state?.map((data) => data.percentAlocacao),
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
