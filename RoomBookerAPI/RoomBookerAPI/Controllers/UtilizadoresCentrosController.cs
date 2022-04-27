#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using RoomBookerAPI.Models;
using RoomBookerAPI.Service;

namespace RoomBookerAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UtilizadoresCentrosController : Controller
    {
        private readonly IUtilizadoresCentrosService utilizadoresCentrosService;

        public UtilizadoresCentrosController(IUtilizadoresCentrosService service)
        {
            utilizadoresCentrosService = service;
        }

        // GET: api/UtilizadoresCentros
        [HttpGet]
        public IEnumerable<UtilizadorCentro> GetUtilizadores()
        {
            return utilizadoresCentrosService.GetAll();
        }

        // GET: api/UtilizadoresCentros/actions?id_utilizador=4&id_centro=1
        [HttpGet("{id}")]
        public ActionResult<UtilizadorCentro> GetUtilizadorCentro(int id_utilizador, int id_centro)
        {
            return utilizadoresCentrosService.Get(id_utilizador, id_centro);
        }

        // PUT: api/UtilizadoresCentros/actions?id_utilizador=4&id_centro=1
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public Task<IActionResult> PutUtilizador(int id_utilizador, int id_centro, UtilizadorCentro utilizadorCentro)
        {
            return utilizadoresCentrosService.Put(id_utilizador, id_centro, utilizadorCentro);
        }

        //// POST: api/UtilizadoresCentros
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public Task<ActionResult<UtilizadorCentro>> PostUtilizadorCentro(UtilizadorCentro utilizadorCentro)
        {
            return utilizadoresCentrosService.Post(utilizadorCentro);
        }
    }
}
