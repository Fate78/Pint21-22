#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using RoomBooker_API.Service;

namespace RoomBooker_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SalasController : Controller
    {
        private readonly ISalasService salasService;

        public SalasController(ISalasService service)
        {
            salasService = service;
        }

        // GET: api/Salas
        [HttpGet]
        public IEnumerable<Sala> GetSalas()
        {
            return salasService.GetAll();
        }

        // GET: api/Salas/5
        [HttpGet("{id}")]
        public ActionResult<Sala> GetSala(int id)
        {
            return salasService.Get(id);
        }

        [HttpGet("{id}/Reservas")]
        public ActionResult<Sala> GetReservasbySala(int id)
        {
            return salasService.Get(id);
        }

        // PUT: api/Salas/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public Task<IActionResult> PutSala(int id, Sala sala)
        {
            return salasService.Put(id, sala);
        }

        //// POST: api/Salas
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public Task<ActionResult<Sala>> PostSala(Sala sala)
        {
            return salasService.Post(sala);
        }
    }
}
