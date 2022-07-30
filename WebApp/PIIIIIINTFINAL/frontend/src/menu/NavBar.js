import React, { useState } from "react";

// ICONS
import * as FaIcons from "react-icons/fa"; //Now i get access to all the icons
import * as AiIcons from "react-icons/ai";

import { IconContext } from "react-icons";

// ROUTING

import { Link } from "react-router-dom";

export default function Navbar() {
   
  
    return (
    
        <div id="wrapper">
          <nav >
          <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark" id="Sidebar">

<a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashboard.html">
    <div class="sidebar-brand-text mx-3">Room Booker</div>
</a>


<li class="nav-item">
    <a class="nav-link" href="dashboard">
        <span>Dashboard</span></a>
</li>


<div class="sidebar-heading">
    Salas
</div>

<li class="nav-item">
    <a class="nav-link collapsed" href="salasmaisutilizadas" aria-expanded="true" aria-controls="collapseTwo">
        <span>Salas Mais Utilizadas</span>
    </a>
</li>

<li class="nav-item">
    <a class="nav-link collapsed" href="gestaosalas" data-toggle="collapse" data-target="#collapseUtilities"
        aria-expanded="true" aria-controls="collapseUtilities">
        <span>Gestão de Salas</span>
    </a>
</li>
<li class="nav-item">
    <a class="nav-link collapsed" href="limpeza" data-toggle="collapse" data-target="#collapseUtilities"
        aria-expanded="true" aria-controls="collapseUtilities">
        <span>Limpeza</span>
    </a>
</li>


<div class="sidebar-heading">
    Utilizadores
</div>

<li class="nav-item">
    <a class="nav-link collapsed" href="utilizador" data-toggle="collapse" data-target="#collapseUtilities"
        aria-expanded="true" aria-controls="collapseUtilities">
        <span>Utilizadores Registados</span>
    </a>
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
</ul>
          </nav>
        </div>
    );
  }
  