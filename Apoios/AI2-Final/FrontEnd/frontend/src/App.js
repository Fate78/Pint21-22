import React, { useState } from 'react';
import './App.css';
import {BrowserRouter, BrowserRouter as Router, Route, Switch} from "react-router-dom";

import './assets/css/bootstrap.css';
import "./assets/timepicker.js/timepicker.min.css";
import "./assets/css/CSS_HTML.css";


import Main from './components/main/all';



function App() {
  
  return (
    <Router >
      <Route path="/inicio" component={Main} />

      <Route path="/editar/:id" component={Main} />

      <Route path="/utilizadorlist" component={Main} />
      <Route path="/urgente" component={Main} />
      <Route path="/semanal" component={Main} />
      <Route path="/diario" component={Main} />
      <Route path="/reportlist" component={Main} />
      <Route path="/zonalist" component={Main} />
      <Route path="/zona/:id" component={Main} />
      <Route path="/utilizador/:id" component={Main} />
      <Route path="/definicoes" component={Main} />
      <Route path="/zonamaisconcentrada" component={Main} />

      <Route path="/criarutilizador" component={Main} />
      <Route path="/criarzona" component={Main} />
    </Router>
  );
}

export default App;
