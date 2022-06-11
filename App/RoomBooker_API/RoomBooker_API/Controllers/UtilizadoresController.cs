#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using RoomBooker_API.Service;

namespace RoomBooker_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UtilizadoresController : ControllerBase
    {
        private readonly IUtilizadoresService utilizadoresService;

        public UtilizadoresController(IUtilizadoresService service)
        {
            utilizadoresService = service;
        }

        // GET: api/Utilizadores
        [HttpGet]
        public IEnumerable<Utilizador> GetUtilizadores()
        {
            return utilizadoresService.GetAll();
        }

        // GET: api/Utilizadores/5
        [HttpGet("{id:int}")]
        public ActionResult<Utilizador> GetUtilizador(int id)
        {
            return utilizadoresService.Get(id);
        }

        //GET: api/Utilizadores/username
        [HttpGet("{string_input}")]
        public ActionResult<Utilizador> GetUtilizadorbyString(string string_input)
        {
            return utilizadoresService.GetbyString(string_input);
        }

        [HttpGet("{id:int}/Reservas")]
        public ActionResult<Utilizador> GetReservasbyUtilizadorId(int id)
        {
            return utilizadoresService.GetReservasbyUtilizadorId(id);
        }

        [HttpGet("{username}/Reservas")]
        public ActionResult<Utilizador> GetReservasbyUtilizador(string username)
        {
            return utilizadoresService.GetReservasbyUtilizador(username);
        }

        // PUT: api/Utilizadores/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public Task<IActionResult> PutUtilizador(int id, Utilizador utilizador)
        {
            return utilizadoresService.Put(id, utilizador);
        }

        //// POST: api/Utilizadores
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public Task<ActionResult<Utilizador>> PostUtilizador(Utilizador utilizador)
        {
            return utilizadoresService.Post(utilizador);
        }

        //// DELETE: api/Utilizadores/5
        //[HttpDelete("{id}")]
        //public Task<IActionResult> DeleteUtilizador(int id)
        //{
        //    return utilizadoresService.Delete(id);
        //}

    }
}
