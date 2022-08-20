import React from "react";
import '../../CSS/stylesdashboard1.css';
import useAuth from "../../hooks/useAuth";
import { Link } from "react-router-dom";

export default function Navbar() {

    const auth = useAuth();

    return (

        <div id="wrapper">
            <nav>
                <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark fixed-top" id="Sidebar">

                    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashboard.html">
                        <div class="sidebar-brand-text mx-3">Room Booker</div>
                    </a>

                    <Link to={`/dashboard`}>
                    <li class="nav-item">
                        <a class="nav-link">
                            <span>Dashboard</span></a>
                    </li>
                    </Link>
                    <Link to={`/reservas`}>
                    <li class="nav-item">
                        <a class="nav-link collapsed"  data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Reservas</span>
                        </a>
                    </li>
                    </Link>
                    <div class="sidebar-heading">
                        Salas
                    </div>
                    
                    <li class="nav-item">
                        <a class="nav-link collapsed" href="salasmaisutilizadas" aria-expanded="true" aria-controls="collapseTwo">
                            <span>Salas Mais Utilizadas</span>
                        </a>
                    </li>
                    <Link to={`/gestaosalas`}>
                    <li class="nav-item">
                        <a class="nav-link collapsed"  data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Gestão de Salas</span>
                        </a>
                    </li>
                    </Link>
                    <Link to={`/centros`}>
                    <li class="nav-item">
                        <a class="nav-link collapsed"  data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Centros</span>
                        </a>
                    </li>
                    </Link>
                    <li class="nav-item">
                        <a class="nav-link collapsed" href="limpeza" data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Limpeza</span>
                        </a>
                    </li>


                    <div class="sidebar-heading">
                        Utilizadores
                    </div>
                    <Link to={`/utilizadores`}>
                    <li class="nav-item">
                        <a class="nav-link collapsed" data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Utilizadores Registados</span>
                        </a>
                    </li>
                    </Link>

                    <Link to={`/bulkinsert`}>
                    <li class="nav-item">
                        <a class="nav-link collapsed"  data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Bulk</span>
                        </a>
                    </li>
                    </Link>

                    <div class="sidebar-heading">
                        Necessita de Ajuda?
                    </div>

                    <li class="nav-item">
                        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Suporte</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    );
}
