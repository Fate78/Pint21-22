using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;

namespace RoomBooker_API.Service
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

        public ActionResult<Utilizador> GetbyUsername(string username)
        {
            var utilizador = pintContext.Utilizadores.Single(user => user.NomeUtilizador == username);
            //Check if null, return notfound
            if (utilizador == null)
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
        private bool UtilizadorExists(int id)
        {
            return pintContext.Utilizadores.Any(e => e.IdUtilizador == id);
        }
    }
}
