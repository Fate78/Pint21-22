const express = require('express');
const router = express.Router();

const zona_controller = require('../controllers/zonas');

router.post('/create',zona_controller.create);

router.get('/list', zona_controller.list);

router.get('/get/:id', zona_controller.get);

router.post('/update/:id', zona_controller.update);

router.post('/delete', zona_controller.delete);

module.exports = router;