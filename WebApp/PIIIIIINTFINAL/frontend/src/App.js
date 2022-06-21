import {BrowserRouter, BrowserRouter as Router, Route, Routes} from "react-router-dom";
import './App.css';

import Utilizadores from './menu/listar/utilizadores'

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/utilizador' element={<Utilizadores />} />
      </Routes>
    </Router>
  );
}

export default App;
