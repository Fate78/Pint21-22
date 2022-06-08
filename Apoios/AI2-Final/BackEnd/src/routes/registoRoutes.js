const express = require('express');
const router = express.Router();

const registo_controller = require('../controllers/registos');



router.get('/list', registo_controller.list);



module.exports = router;