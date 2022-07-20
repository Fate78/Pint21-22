using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using Microsoft.AspNetCore.Mvc;

namespace RoomBooker_API.Service
{
    public class CentrosService : ICentrosService
    {
        PintContext pintContext;
        public CentrosService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<Centro> GetAll()
        {
            return pintContext.Centros;
        }

        public ActionResult<Centro> Get(int id)
        {
            var centro = pintContext.Centros
                .Include(i => i.Salas)
                .FirstOrDefault(i => i.IdCentro == id);
            //Check if null, return notfound
            if (centro == null)
            {
                return new NotFoundResult();
            }

            return centro;
        }

        public ActionResult<Centro> GetbyName(string centroName)
        {
            var centro = pintContext.Centros
                .Include(i => i.Salas)
                .FirstOrDefault(i => i.NomeCentro == centroName);
            //Check if null, return notfound
            if (centro == null)
            {
                return new NotFoundResult();
            }

            return centro;
        }

        public async Task<IActionResult> Put(int id, Centro centro)
        {
            if (id != centro.IdCentro)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(centro).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CentroExists(id))
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
        public async Task<ActionResult<Centro>> Post(Centro centro)
        {
            pintContext.Centros.Add(centro);
            await pintContext.SaveChangesAsync();

            return new CreatedAtRouteResult("GetCentros", new { id = centro.IdCentro }, centro);
        }
        private bool CentroExists(int id)
        {
            return pintContext.Centros.Any(e => e.IdCentro == id);
        }
    }
}
