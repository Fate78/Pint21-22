import { BrowserRouter, BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import './App.css';

//Centros
import Centros from "./menu/Centro/centros";
import Centro from "./menu/Centro/centro";
import CriarCentro from "./menu/Centro/criarcentro"
import EditarCentro from "./menu/Centro/editarcentro"

//Salas
import Sala from './menu/Sala/sala'
import GestaoSalas from './menu/Sala/gestaosalas'
import EditarSala from './menu/Sala/editarsala'
import CriarSala from "./menu/Sala/criarSala"

//Utilizador
import Utilizadores from './menu/Utilizador/utilizadores'
import Utilizador from './menu/Utilizador/utilizador'
import Dashboard from './menu/dashboard'
import Bulk from './menu/Utilizador/bulkinsert'

//Reserva
import Reservas from "./menu/Reserva/reservas"
import Reserva from "./menu/Reserva/reserva"

//Login
import Login from './menu/Login/login'
import RequireAuth from "./menu/Login/RequireAuth";
import PersistLogin from "./menu/Login/PersistLogin";

import Layout from './layout/Layout'
import withNav from "./layout/withNav";
import NavBarTop from "./menu/Navs/NavBarTop";
import Navbar from "./menu/Navs/NavBar";
import EditarReserva from "./menu/Reserva/editarreserva"
import Verificar from "./menu/verificar"
import { useEffect, useState } from "react";
import axios from "axios";

const baseUrl = "https://roombookerapi.azurewebsites.net/api";

function App() {

  return (
    <div>

      <Routes>

        <Route path="/" element={<Layout />}>
          {/*routes publicos*/}
          <Route path="/login" element={<Login />} />
          <Route path="/verificar" element = {<Verificar />} />

          {/*routes protegidos*/}

          <Route element={<RequireAuth />}>

            {/*SALAS*/}
            <Route path="/sala/*" element={<Sala />} />
            <Route path="/criarsala" element={<CriarSala />} />
            <Route path='/gestaosalas' element={<GestaoSalas />} />
            <Route path="/sala/editar/*" element={<EditarSala />} />

            {/*UTILIZADORES*/}
            <Route path="/utilizador/*" element={<Utilizador />} />
            <Route path='/utilizadores' element={<Utilizadores />} />
            <Route path='/bulkinsert' element={<Bulk />} />

            {/*RESERVAS*/}
            <Route path="/reservas" element={<Reservas />} />
            <Route path="/reserva/*" element={<Reserva />} />
            <Route path="/reserva/editar/*" element={<EditarReserva />} />

            {/*CENTROS*/}
            <Route path="/centro/*" element={<Centro />} />
            <Route path="/criarcentro" element={<CriarCentro />} />
            <Route path="/centro/editar/*" element={<EditarCentro />} />
            <Route path='/centros' element={<Centros />} />
            <Route path='/dashboard' element={<Dashboard />} />
          </Route>
        </Route>

      </Routes>
    </div>
  );
}

export default App;
