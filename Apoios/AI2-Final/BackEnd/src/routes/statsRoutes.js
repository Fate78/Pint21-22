const express = require('express');
const router = express.Router();

const stats_controller = require('../controllers/stats');

router.get('/utilizadores/ativos', stats_controller.utilizadores);
router.get('/zonas/configuradas', stats_controller.zonas);
router.get('/avatares/registados', stats_controller.avatares);
router.get('/classificados/diarios', stats_controller.reports);
router.get('/zonas/registos', stats_controller.registos);
router.get('/denspopulacional/urgente', stats_controller.urgente);
router.get('/registosemana', stats_controller.semana);
router.get('/registodia', stats_controller.dia);

module.exports = router;
