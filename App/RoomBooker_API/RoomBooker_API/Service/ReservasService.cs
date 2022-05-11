using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using Microsoft.AspNetCore.Mvc;

namespace RoomBooker_API.Service
{
    public class ReservasService : IReservasService
    {
        PintContext pintContext;
        public ReservasService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<Reserva> GetAll()
        {
            return pintContext.Reservas;
        }

        public ActionResult<Reserva> Get(int id)
        {
            var reserva = pintContext.Reservas.Find(id);
            //Check if null, return notfound
            if (reserva == null)
            {
                return new NotFoundResult();
            }

            return reserva;
        }

        public async Task<IActionResult> Put(int id, Reserva reserva)
        {
            if (id != reserva.IdReserva)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(reserva).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ReservaExists(id))
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
        public async Task<ActionResult<Reserva>> Post(Reserva reserva)
        {
            pintContext.Reservas.Add(reserva);
            await pintContext.SaveChangesAsync();

            return new CreatedAtActionResult("GetReserva", "GetReservas", new { id = reserva.IdReserva }, reserva);
        }
        private bool ReservaExists(int id)
        {
            return pintContext.Reservas.Any(e => e.IdReserva == id);
        }
    }
}
