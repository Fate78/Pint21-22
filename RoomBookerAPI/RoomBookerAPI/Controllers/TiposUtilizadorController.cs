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
    public class TiposUtilizadorController : Controller
    {
        private readonly  ITiposUtilizadorService tiposUtilizadorService;

        public TiposUtilizadorController(ITiposUtilizadorService service)
        {
            tiposUtilizadorService = service;
        }

        // GET: api/TiposUtilizador
        [HttpGet]
        public IEnumerable<TipoUtilizador> GetTiposUtilizador()
        {
            return tiposUtilizadorService.GetAll();
        }

        // GET: api/TiposUtilizador/5
        [HttpGet("{id}")]
        public ActionResult<TipoUtilizador> GetTipoUtilizador(int id)
        {
            return tiposUtilizadorService.Get(id);
        }

        // PUT: api/TiposUtilizador/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public Task<IActionResult> PutUtilizador(int id, TipoUtilizador tipoUtilizador)
        {
            return tiposUtilizadorService.Put(id, tipoUtilizador);
        }

        //// POST: api/TiposUtilizador
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public Task<ActionResult<TipoUtilizador>> PostUtilizador(TipoUtilizador tipoUtilizador)
        {
            return tiposUtilizadorService.Post(tipoUtilizador);
        }
    }
}
