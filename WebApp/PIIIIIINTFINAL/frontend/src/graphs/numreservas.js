import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {
    Card,
    Container,
    Row,
    Col,
    Image,
  } from "react-bootstrap";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

export default function NumReservas() {

    const [numReservas, setNumReservas] = useState();
    const [reservas, setReservas] = useState();
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(null);
    const [myRef, setMyRef] = useState(false)
    const closeCalendar = () => {
        myRef.setOpen(false)
    }

    const onChange = (dates) => {
        const [start, end] = dates;
        setStartDate(start);
        setEndDate(end);
    };


    useEffect(() => {
        let count = 0
        axios.get(baseUrl + "/reservas")
            .then((nRes) => {
                setNumReservas(
                    nRes.data.length
                )

                for (let a = 0; a < numReservas; a++) {
                    const date = new Date(nRes.data[a].dataReserva);
                    if (endDate == null) {
                        if (startDate < date) {
                            count++;

                        }
                    } else {
                        if (startDate < date && date < endDate) {
                            count++;
                            console.log(date)
                        }

                    }
                }

                setReservas(
                    count
                )
            })
            .catch((err) => {
                console.log(err)
            })

    }, [startDate, endDate])



    return (
        <div >
            <div className="col" style={{ textDecoration: "none", color: "black" }}>
                <div className="card">
                    <div className="card-body">
                        <div className="card-title">
                            Total de reservas num range de datas
                        </div>
                    
                        
                            <p className="card-category"><DatePicker selected={startDate} onChange={onChange} startDate={startDate} endDate={endDate} selectsRange /> </p>

                            <p>Tem um total de {reservas} reservas.</p>
                        
                    
                </div>
                </div>
            </div>
        </div>
    );
}