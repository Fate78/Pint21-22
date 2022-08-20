import { BrowserRouter, BrowserRouter as Router, Route, Routes } from "react-router-dom";
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

//Login
import Login from './menu/Login/login'
import RequireAuth from "./menu/Login/RequireAuth";
import PersistLogin from "./menu/Login/PersistLogin";

import Layout from './layout/Layout'
import NavBarTop from "./menu/Navs/NavBarTop";
import Navbar from "./menu/Navs/NavBar";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Layout />}>
          {/*routes publicos*/}
          <Route path="/login" element={<Login />} />

          {/*routes protegidos*/}

          <Route element={<RequireAuth />}>

            <Route path="/reservas" element={<Reservas />} />
            <Route path="/sala/*" element={<Sala />} />
            <Route path="/centro/*" element={<Centro />} />
            <Route path="/criarsala" element={<CriarSala />} />
            <Route path="/criarcentro" element={<CriarCentro />} />
            <Route path="/sala/editar/*" element={<EditarSala />} />
            <Route path="/centro/editar/*" element={<EditarCentro />} />
            <Route path="/utilizador/*" element={<Utilizador />} />
            <Route path='/gestaosalas' element={<GestaoSalas />} />
            <Route path='/dashboard' element={<Dashboard />} />
            <Route path='/utilizadores' element={<Utilizadores />} />
            <Route path='/centros' element={<Centros />} />
            <Route path='/bulkinsert' element={<Bulk />} />

          </Route>

        </Route>
      </Routes>
    </div>
  );
}

export default App;
