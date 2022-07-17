using RoomBooker_API.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Mvc;

namespace RoomBooker_API.Service
{
    public class TiposUtilizadorService : ITiposUtilizadorService
    {
        PintContext pintContext;

        public TiposUtilizadorService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<TipoUtilizador> GetAll()
        {
            return pintContext.TiposUtilizador;
        }

        public ActionResult<TipoUtilizador> Get(int id)
        {
            var tipoUtilizador = pintContext.TiposUtilizador.Find(id);
            //Check if null, return notfound
            if (tipoUtilizador == null)
            {
                return new NotFoundResult();
            }

            return tipoUtilizador;
        }

        public async Task<IActionResult> Put(int id, TipoUtilizador tipoUtilizador)
        {
            if (id != tipoUtilizador.IdTipo)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(tipoUtilizador).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TipoUtilizadorExists(id))
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
        public async Task<ActionResult<TipoUtilizador>> Post(TipoUtilizador tipoUtilizador)
        {
            pintContext.TiposUtilizador.Add(tipoUtilizador);
            await pintContext.SaveChangesAsync();

            return new CreatedAtRouteResult("GetTiposUtilizador", new { id = tipoUtilizador.IdTipo }, tipoUtilizador);
        }
        private bool TipoUtilizadorExists(int id)
        {
            return pintContext.TiposUtilizador.Any(e => e.IdTipo == id);
        }
    }
}
