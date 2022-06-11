const express = require('express');
const router = express.Router();

const denspopulacional_controller = require('../controllers/denspopulacionais');

router.post('/create',denspopulacional_controller.create);

router.get('/list', denspopulacional_controller.list);

router.get('/get/:id', denspopulacional_controller.get);

router.post('/update/:id', denspopulacional_controller.update);

router.post('/delete/:id', denspopulacional_controller.delete)

module.exports = router;