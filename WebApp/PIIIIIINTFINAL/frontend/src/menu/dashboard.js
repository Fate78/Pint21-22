import axios from "axios";
import { useEffect, useState } from "react";
import BarChart from "../graphs/chart-bar";
import NumUtilizadores from "../graphs/numutilizadores"
import NumReservas from "../graphs/numreservas";
const baseUrl = "https://roombookerapi.azurewebsites.net/api";

function App() {
    const accesstoken = localStorage.getItem("token")
    axios.defaults.headers.common['Authorization'] = `Bearer ${accesstoken}`

    return (
        <div id="content-wrapper" class="d-flex flex-column">
            <div className="container">
                <div className='row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4'>
                    <div className='col'>
                        <h3 className="mb-4 text-gray-800">Dashboard</h3>
                        <div>
                            <BarChart />
                        </div>
                        <div>
                            <NumUtilizadores />
                        </div>
                        <div>
                            <NumReservas />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default App;