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
            return pintContext.Reservas
                .OrderByDescending(i => i.DataReserva)
                .ThenByDescending(i => i.HoraInicio);
        }

        public ActionResult<Reserva> Get(int id)
        {
            var reserva = pintContext.Reservas
                .Include(i => i.IdSalaNavigation)
                .FirstOrDefault(i => i.IdReserva == id);
            //Check if null, return notfound
            if (reserva == null)
            {
                return new NotFoundResult();
            }

            return reserva;
        }

        public IEnumerable<Reserva> GetAllbyDate(DateTime date)
        {
            return pintContext.Reservas
                .Where(reserva => reserva.DataReserva == date)
                .OrderByDescending(i => i.HoraInicio);
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

            return new CreatedAtRouteResult("GetReservas", new { id = reserva.IdReserva }, reserva);
        }
        private bool ReservaExists(int id)
        {
            return pintContext.Reservas.Any(e => e.IdReserva == id);
        }
    }
}
