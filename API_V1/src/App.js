const express = require('express');
const bodyParser = require('body-parser');
var Sequelize = require('sequelize');
const path = require('path');

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

// Configurar CORS
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Headers', 'Authorization, X-API-KEY, Origin, X-Requested-With, Content-Type,Accept, Access-Control-Allow-Request-Method');
  res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE');
  res.header('Allow', 'GET, POST, OPTIONS, PUT, DELETE');
  next();
  }); 

//Local routes
app.use('/centrosgeograficos', require('./routes/centrosgeograficos'));
app.use('/utilizadores', require('./routes/utilizadores'));
app.use('/registos', require('./routes/registos'));

app.use('/login', require('./routes/loginRoute'));
app.use('/salas', require('./routes/salas'));
app.use('/reservas', require('./routes/reservas'));
app.use('/tickets', require('./routes/tickets'));
app.use('/tipoutilizador', require('./routes/tipoutilizador'));



//Configurações
app.set('port', process.env.PORT|| 5000);


app.listen(app.get('port'),()=>{
console.log("Start server on port "+app.get('port'))
})
