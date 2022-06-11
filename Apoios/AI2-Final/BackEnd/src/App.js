const express = require('express');
const bodyParser = require('body-parser');
var Sequelize = require('sequelize');
const path = require('path');
const cors = require('cors');
const cookieParser = require('cookie-parser');


//Database
const db = require('./model/database');

//Test db
db.authenticate()
  .then(() => console.log('Database connected'))
  .catch(err => console.log('Error: ' + err))

const app = express();
 
app.get('/', (req, res) => res.send('INDEX'));

//Body Parser
app.use(bodyParser.urlencoded({ extended: false}));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.use(express.json());
app.use(express.urlencoded({extended:true}));

//MiddleWare


// Configurar CORS
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Headers', 'Authorization, X-API-KEY, Origin, X-Requested-With, Content-Type,Accept, Access-Control-Allow-Request-Method');
  res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE');
  res.header('Allow', 'GET, POST, OPTIONS, PUT, DELETE');
  next();
  }); 

//Local routes

const zona_routes = require('./routes/zonaRoutes');
const registo_routes = require('./routes/registoRoutes');
const classificado_routes = require('./routes/classificadoRoutes');
const avatar_routes = require('./routes/avatarRoutes');
const denspopulacional_routes = require('./routes/denspopulacionalRoutes');
const perfil_routes = require('./routes/perfilRoutes');
const protesto_routes = require('./routes/protestoRoutes');
const utilizador_routes = require('./routes/utilizadorRoutes');
const stats_routes = require('./routes//statsRoutes');
const alertas_routes = require('./routes//alertasRoutes');

app.use('/zonas', zona_routes);
app.use('/registos', registo_routes);
app.use('/classificados', classificado_routes);
app.use('/avatares', avatar_routes);
app.use('/denspopulacional', denspopulacional_routes);
app.use('/perfis', perfil_routes);
app.use('/protestos', protesto_routes);
app.use('/utilizadores', utilizador_routes);
app.use('/stats', stats_routes);
app.use(cors());
app.use(cookieParser());
app.use('/alertas', alertas_routes);



//app.use('/login', login_routes);

//Configurações
app.set('port', process.env.PORT|| 5000);


app.listen(app.get('port'),()=>{
console.log("Start server on port "+app.get('port'))
})
