const express = require('express');
const router = express.Router();

const protesto_controller = require('../controllers/protestos');

router.post('/create',protesto_controller.create);

router.get('/list', protesto_controller.list);

router.get('/get/:id', protesto_controller.get);

router.post('/update/:id', protesto_controller.update);

router.post('/delete/:id', protesto_controller.delete)

module.exports = router;