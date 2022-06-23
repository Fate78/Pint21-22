import {BrowserRouter, BrowserRouter as Router, Route, Routes} from "react-router-dom";
import './App.css';

import Utilizadores from './menu/listar/utilizadores'
import Notificacoes from './menu/listar/notificacoes'
function App() {
  return (
    <Router>
      <Routes>
        <Route path='/utilizador' element={<Utilizadores />} />
        <Route path='/tickets' element={< Notificacoes/>} />
      </Routes>
    </Router>
  );
}

export default App;
