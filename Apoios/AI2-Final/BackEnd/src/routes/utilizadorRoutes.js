const express = require('express');
const router = express.Router();

const utilizador_controller = require('../controllers/utilizadores');

router.get('/list', utilizador_controller.list);

router.get('/get/:id', utilizador_controller.get);

router.post('/update/:id', utilizador_controller.update);

router.post('/delete/:id', utilizador_controller.delete);



module.exports = router;