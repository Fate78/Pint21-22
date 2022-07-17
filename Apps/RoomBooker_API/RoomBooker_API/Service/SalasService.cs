using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using Microsoft.AspNetCore.Mvc;

namespace RoomBooker_API.Service
{
    public class SalasService : ISalasService
    {
        PintContext pintContext;

        public SalasService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<Sala> GetAll()
        {
            return pintContext.Salas;
        }

        public ActionResult<Sala> Get(int id)
        {
            var sala = pintContext.Salas
                .FirstOrDefault(i => i.IdSala == id);
            //Check if null, return notfound
            if (sala == null)
            {
                return new NotFoundResult();
            }

            return sala;
        }

        public ActionResult<Sala> GetReservasbySala(int n_sala)
        {
            var sala = pintContext.Salas
                .Include(i => i.Reservas.OrderByDescending(i => i.DataReserva)
                .ThenByDescending(i => i.HoraInicio))
                .Single(sala => sala.NSala == n_sala);
            //Check if null, return notfound
            if (sala == null)
            {
                return new NotFoundResult();
            }

            return sala;
        }

        public async Task<IActionResult> Put(int id, Sala sala)
        {
            if (id != sala.IdSala)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(sala).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SalaExists(id))
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
        public async Task<ActionResult<Sala>> Post(Sala sala)
        {
            pintContext.Salas.Add(sala);
            await pintContext.SaveChangesAsync();

            return new CreatedAtRouteResult("GetSalas", new { id = sala.IdSala }, sala);
        }
        private bool SalaExists(int id)
        {
            return pintContext.Salas.Any(e => e.IdSala == id);
        }
    }
}
