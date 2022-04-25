#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RoomBookerAPI.Models;
using RoomBookerAPI.Service;

namespace RoomBookerAPI.Controllers
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
        //[HttpGet("{id}")]
        //public async Task<ActionResult<Utilizador>> GetUtilizador(int id)
        //{
        //    var utilizador = await _context.Utilizadores.FindAsync(id);

        //    if (utilizador == null)
        //    {
        //        return NotFound();
        //    }

        //    return utilizador;
        //}

        // PUT: api/Utilizadores/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        //[HttpPut("{id}")]
        //public async Task<IActionResult> PutUtilizador(int id, Utilizador utilizador)
        //{
        //    if (id != utilizador.IdUtilizador)
        //    {
        //        return BadRequest();
        //    }

        //    _context.Entry(utilizador).State = EntityState.Modified;

        //    try
        //    {
        //        await _context.SaveChangesAsync();
        //    }
        //    catch (DbUpdateConcurrencyException)
        //    {
        //        if (!UtilizadorExists(id))
        //        {
        //            return NotFound();
        //        }
        //        else
        //        {
        //            throw;
        //        }
        //    }

        //    return NoContent();
        //}

        //// POST: api/Utilizadores
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        //[HttpPost]
        //public async Task<ActionResult<Utilizador>> PostUtilizador(Utilizador utilizador)
        //{
        //    _context.Utilizadores.Add(utilizador);
        //    await _context.SaveChangesAsync();

        //    return CreatedAtAction("GetUtilizador", new { id = utilizador.IdUtilizador }, utilizador);
        //}

        //// DELETE: api/Utilizadores/5
        //[HttpDelete("{id}")]
        //public async Task<IActionResult> DeleteUtilizador(int id)
        //{
        //    var utilizador = await _context.Utilizadores.FindAsync(id);
        //    if (utilizador == null)
        //    {
        //        return NotFound();
        //    }

        //    _context.Utilizadores.Remove(utilizador);
        //    await _context.SaveChangesAsync();

        //    return NoContent();
        //}

        //private bool UtilizadorExists(int id)
        //{
        //    return _context.Utilizadores.Any(e => e.IdUtilizador == id);
        //}
    }
}
