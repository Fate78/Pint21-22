import { BrowserRouter, BrowserRouter as Router, Route, Routes } from "react-router-dom";
import './App.css';

/*import Utilizadores from './menu/listar/utilizadores'
import SalasMaisUtilizadas from './menu/stats/salasmaisutilizadas'
import Login from './menu/login/login'
import Dashboard from './menu/dashboard/dashboard'
import Limpeza from './menu/gestao/limpeza'
import UpdateSala from './menu/edit/updatesala'*/
import Sala from './menu/sala'
import GestaoSalas from './menu/gestaosalas'
import Navbar from './menu/NavBar'
import NavBarTop from './menu/NavBarTop'
import Footer from "./menu/Footer";

function App() {
  return (
    <div>
      <Router>

        <NavBarTop />
        <Navbar />
        <Routes>
          <Route path="/sala/*" element={<Sala />} />
          <Route path='/gestaosalas' element={<GestaoSalas />} />
        </Routes>
      </Router>
      <Footer />
    </div>
  );
}

export default App;
