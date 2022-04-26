﻿#nullable disable
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
        [HttpGet("{id}")]
        public ActionResult<Utilizador> GetUtilizador(int id)
        {
            return utilizadoresService.Get(id);
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
