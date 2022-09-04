import React, { useEffect } from "react";
import '../../CSS/stylesdashboard1.css';
import useAuth from "../../hooks/useAuth";
import { Link } from "react-router-dom";
import {Nav} from "react-bootstrap";


export default function Navbar() {

    const auth = useAuth();
    

    return (

        <div >
            <Nav class="navbar-nav bg-gradient-primary sidebar sidebar-dark fixed-top">

                    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashboard.html">
                        <div class="sidebar-brand-text mx-3">Room Booker</div>
                    </a>

                    
                    <li class="nav-item">
                    <Link to={`/dashboard`}>
                        <a class="nav-link">
                            <span>Dashboard</span></a>
                            </Link>
                    </li>
                    
                    
                    <li className="nav-item">
                    <Link to={`/reservas`}>
                        <a class="nav-link collapsed"  data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Reservas</span>
                        </a>
                    </Link>
                    </li>
                    
                    <div class="sidebar-heading">
                        Salas
                    </div>
                    
                    
                    <li class="nav-item">
                    <Link to={`/gestaosalas`}>
                        <a class="nav-link collapsed"  data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Gestão de Salas</span>
                        </a>
                        </Link>
                    </li>
                    
                    
                    <li class="nav-item">
                    <Link to={`/centros`}>
                        <a class="nav-link collapsed"  data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Centros</span>
                        </a>
                        </Link>
                    </li>
                    
                    <li class="nav-item">
                    <Link to={`/limpeza`}>
                        <a class="nav-link collapsed" href="limpeza" data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Limpeza</span>
                        </a>
                    </Link>
                    </li>


                    <div class="sidebar-heading">
                        Utilizadores
                    </div>

                    
                    <li class="nav-item">
                    <Link to={`/utilizadores`}>
                        <a class="nav-link collapsed" data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Utilizadores Registados</span>
                        </a>
                        </Link>
                    </li>
                    

                    
                    <li class="nav-item">
                    <Link to={`/bulkinsert`}>
                        <a class="nav-link collapsed"  data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Bulk</span>
                        </a>
                        </Link>
                    </li>
                    
                    <div class="sidebar-heading">
                        Necessita de Ajuda?
                    </div>

                    <li class="nav-item">
                        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                            aria-expanded="true" aria-controls="collapseUtilities">
                            <span>Suporte</span>
                        </a>
                    </li>
                
            </Nav>
        </div>
    );
}
