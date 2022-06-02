using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using Microsoft.AspNetCore.Mvc;
using RoomBooker_API.Service;

namespace RoomBooker_API.Service
{
    public class TicketsService : ITicketsService
    {
        PintContext pintContext;

        public TicketsService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<Ticket> GetAll()
        {
            return pintContext.Tickets;
        }

        public ActionResult<Ticket> Get(int id)
        {
            var ticket = pintContext.Tickets.Find(id);
            //Check if null, return notfound
            if (ticket == null)
            {
                return new NotFoundResult();
            }

            return ticket;
        }

        public async Task<IActionResult> Put(int id, Ticket ticket)
        {
            if (id != ticket.IdTicket)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(ticket).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TicketExists(id))
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
        public async Task<ActionResult<Ticket>> Post(Ticket ticket)
        {
            pintContext.Tickets.Add(ticket);
            await pintContext.SaveChangesAsync();

            return new CreatedAtRouteResult("GetTickets", new { id = ticket.IdTicket }, ticket);
        }
        private bool TicketExists(int id)
        {
            return pintContext.Tickets.Any(e => e.IdTicket == id);
        }
    }
}
