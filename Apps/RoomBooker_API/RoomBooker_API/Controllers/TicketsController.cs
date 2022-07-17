#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using RoomBooker_API.Service;

namespace RoomBooker_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TicketsController : Controller
    {
        private readonly ITicketsService ticketsService;

        public TicketsController(ITicketsService service)
        {
            ticketsService = service;
        }

        // GET: api/Tickets
        [HttpGet]
        public IEnumerable<Ticket> GetTickets()
        {
            return ticketsService.GetAll();
        }

        // GET: api/Tickets/5
        [HttpGet("{id}")]
        public ActionResult<Ticket> GetTicket(int id)
        {
            return ticketsService.Get(id);
        }

        // PUT: api/Tickets/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public Task<IActionResult> PutTicket(int id, Ticket ticket)
        {
            return ticketsService.Put(id, ticket);
        }

        //// POST: api/Tickets
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public Task<ActionResult<Ticket>> PostUtilizador(Ticket ticket)
        {
            return ticketsService.Post(ticket);
        }
    }
}
