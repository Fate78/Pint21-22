using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using Microsoft.AspNetCore.Mvc;

namespace RoomBooker_API.Service
{
    public class UtilizadoresCentrosService : IUtilizadoresCentrosService
    {
        PintContext pintContext;
        public UtilizadoresCentrosService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<UtilizadorCentro> GetAll()
        {
            return pintContext.UtilizadoresCentros;
        }

        public ActionResult<UtilizadorCentro> Get(int id_utilizador, int id_centro)
        {
            var utilizadorCentro = pintContext.UtilizadoresCentros.Find(id_utilizador, id_centro);
            //Check if null, return notfound
            if (utilizadorCentro == null)
            {
                return new NotFoundResult();
            }

            return utilizadorCentro;
        }

        public async Task<IActionResult> Put(int id_utilizador, int id_centro, UtilizadorCentro utilizadorCentro)
        {
            if (id_utilizador != utilizadorCentro.IdUtilizador && id_centro != utilizadorCentro.IdCentro)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(utilizadorCentro).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UtilizadorCentroExists(id_utilizador, id_centro))
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
        public async Task<ActionResult<UtilizadorCentro>> Post(UtilizadorCentro utilizadorCentro)
        {
            pintContext.UtilizadoresCentros.Add(utilizadorCentro);
            await pintContext.SaveChangesAsync();

            return new CreatedAtRouteResult("GetUtilizadoresCentros", new { id_utilizador = utilizadorCentro.IdUtilizador, id_centro = utilizadorCentro.IdCentro }, utilizadorCentro);
        }
        private bool UtilizadorCentroExists(int id_utilizador, int id_centro)
        {
            return pintContext.UtilizadoresCentros.Any(e => e.IdUtilizador == id_utilizador && e.IdCentro == id_centro);
        }
    }
}
