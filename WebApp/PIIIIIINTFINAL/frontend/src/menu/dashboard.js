import axios from "axios";
import { useEffect, useState } from "react";
import BarChart from "../graphs/chart-bar";
import NumUtilizadores from "../graphs/numutilizadores"
import NumReservas from "../graphs/numreservas";
import SalaCapacidade from "../graphs/salacapacidade";
import {
    Card,
    Container,
    Row,
    Col,
} from "react-bootstrap";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

function App() {
    const accesstoken = localStorage.getItem("token")
    const user = localStorage.getItem("user")
    const password = localStorage.getItem("password")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    return (
        <div id="content-wrapper" className="d-flex flex-column">
            <div className="container">

                <div className='col-lg-3-sm-6'>
                    <h3 className="mb-4 text-gray-800">Dashboard</h3>
                    <div className="row">

                        <BarChart />
                        
                        <SalaCapacidade />

                        <NumUtilizadores />

                        <NumReservas />

                    </div>
                </div>
            </div>
        </div>
    );
}

export default App;