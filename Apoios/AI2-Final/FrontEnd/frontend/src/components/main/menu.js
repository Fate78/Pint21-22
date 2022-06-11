import React from 'react';
import {Route} from "react-router-dom";
import Inicio from './menu/Inicio';

//Criar
import CriarUtilizador from './menu/create/utilizadoresCreate';
import CriarZona from './menu/create/zonasCreate';

//Listar
import UtilizadorList from './menu/list/utilizadoresList';
import ZonaList from './menu/list/zonasList';
import Zona from './menu/getone/Zona';
import ReportList from './menu/list/reportsList';
import Utilizador from './menu/getone/Utilizador';
import ZonaMais from './menu/list/zonamais';
import Semana from './menu/list/semana';
import Dia from './menu/list/dia';

//Editar
import ZonaEdit from './menu/edit/zonaEdit';

const Menu = () => (
    

    <div className="container-fluid h-100 noScroll" style={{padding: '0'}}>
        <Route path="/inicio" component={Inicio} />

        <Route path="/utilizadorlist" component={UtilizadorList} />
        <Route path="/reportlist" component={ReportList} />
        <Route path="/zonalist" component={ZonaList} />
        <Route path="/zona/:id" component={Zona} />
        <Route path="/semanal" component={Semana} />
        <Route path="/diario" component={Dia} />
        <Route path="/utilizador/:id" component={Utilizador} />
        <Route path="/definicoes" component={Utilizador} />
        <Route path="/zonamaisconcentrada" component={ZonaMais} />

        <Route path="/criarutilizador" component={CriarUtilizador} />
        <Route path="/criarzona" component={CriarZona} />

        <Route path="/editar/:id" component={ZonaEdit} />
    </div>
);



export default Menu;
