import {BrowserRouter, BrowserRouter as Router, Route, Routes} from "react-router-dom";
import './App.css';

import Utilizadores from './menu/listar/utilizadores'
import SalasMaisUtilizadas from './menu/stats/salasmaisutilizadas'
import GestaoSalas from './menu/gestao/gestaosalas'
import Login from './menu/login/login'
import Dashboard from './menu/dashboard/dashboard'
import Limpeza from './menu/gestao/limpeza'
import UpdateSala from './menu/edit/updatesala'
import Navbar from './menu/NavBar'
import Footer from './menu/Footer'

function App() {
  return (
    <div id="wrapper">
    <Router>
      <Navbar />
      <Routes>
        
        <Route path='/utilizador' element={<Utilizadores />} />
        <Route path='/salasmaisutilizadas' element={<SalasMaisUtilizadas />} />
        <Route path='/gestaosalas' element={<GestaoSalas />} />
        <Route path='/login' element={<Login />} />
        <Route path='/dashboard' element={<Dashboard />} />
        <Route path='/limpeza' element={<Limpeza />} />
        <Route path='/updatesala' element={<UpdateSala />} />
        
        
      </Routes>
    </Router>
    </div>
  );
}

export default App;
