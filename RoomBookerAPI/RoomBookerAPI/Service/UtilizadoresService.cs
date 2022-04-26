﻿using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using Microsoft.EntityFrameworkCore;
using RoomBookerAPI.Models;
using RoomBookerAPI.Service;
using RoomBookerAPI.Controllers;
using System.Net;
using System.Web.Http;

namespace RoomBookerAPI.Service
{
    public class UtilizadoresService : IUtilizadoresService
    {
        PintContext pintContext;
        public UtilizadoresService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<Utilizador> GetAll()
        {
            return pintContext.Utilizadores;
        }

        public ActionResult<Utilizador> Get(int id)
        {
            var utilizador = pintContext.Utilizadores.Find(id);
            //Check if null, return notfound
            if(utilizador == null)
            {
                return new NotFoundResult();
            }

            return utilizador;
        }

        public async Task<IActionResult> Put(int id, Utilizador utilizador)
        {
            if (id != utilizador.IdUtilizador)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(utilizador).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UtilizadorExists(id))
                {
                    return new NotFoundResult();
                }
                else
                {
                    throw;
                }
            }

            return new NoContentResult();
        }
        public async Task<ActionResult<Utilizador>> Post(Utilizador utilizador)
        {
            pintContext.Utilizadores.Add(utilizador);
            await pintContext.SaveChangesAsync();

            return new CreatedAtActionResult("GetUtilizador", "GetUtilizadores", new { id = utilizador.IdUtilizador }, utilizador);
        }
        //public async Task<IActionResult> Delete(int id)
        //{
        //    var utilizador = await pintContext.Utilizadores.FindAsync(id);
        //    if (utilizador == null)
        //    {
        //        return new NotFoundResult();
        //    }

        //    pintContext.Utilizadores.Remove(utilizador);
        //    await pintContext.SaveChangesAsync();

        //    return new NoContentResult();
        //}
        private bool UtilizadorExists(int id)
        {
            return pintContext.Utilizadores.Any(e => e.IdUtilizador == id);
        }
    }
}
