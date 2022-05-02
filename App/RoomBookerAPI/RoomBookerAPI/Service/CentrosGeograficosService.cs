using Microsoft.EntityFrameworkCore;
using RoomBookerAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace RoomBookerAPI.Service
{
    public class CentrosGeograficosService : ICentrosGeograficosService
    {
        PintContext pintContext;
        public CentrosGeograficosService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<CentroGeografico> GetAll()
        {
            return pintContext.CentrosGeograficos;
        }

        public ActionResult<CentroGeografico> Get(int id)
        {
            var centroGeografico = pintContext.CentrosGeograficos.Find(id);
            //Check if null, return notfound
            if (centroGeografico == null)
            {
                return new NotFoundResult();
            }

            return centroGeografico;
        }

        public async Task<IActionResult> Put(int id, CentroGeografico centroGeografico)
        {
            if (id != centroGeografico.IdCentro)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(centroGeografico).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CentroGeograficoExists(id))
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
        public async Task<ActionResult<CentroGeografico>> Post(CentroGeografico centroGeografico)
        {
            pintContext.CentrosGeograficos.Add(centroGeografico);
            await pintContext.SaveChangesAsync();

            return new CreatedAtActionResult("GetCentroGeografico", "GetCentrosGeograficos", new { id = centroGeografico.IdCentro }, centroGeografico);
        }
        private bool CentroGeograficoExists(int id)
        {
            return pintContext.CentrosGeograficos.Any(e => e.IdCentro == id);
        }
    }
}
