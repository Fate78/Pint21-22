const express = require('express');
const router = express.Router();

const avatar_controller = require('../controllers/avatares');

router.post('/create',avatar_controller.create);

router.get('/list', avatar_controller.list);

router.get('/get/:id', avatar_controller.get);

router.post('/update/:id', avatar_controller.update);

router.post('/delete/:id', avatar_controller.delete)

module.exports = router;