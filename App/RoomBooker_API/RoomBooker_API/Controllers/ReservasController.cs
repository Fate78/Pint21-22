using Microsoft.AspNetCore.Mvc;
using RoomBooker_API.Models;
using RoomBooker_API.Service;

namespace RoomBooker_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ReservasController : Controller
    {
        private readonly IReservasService reservasService;

        public ReservasController(IReservasService service)
        {
            reservasService = service;
        }

        // GET: api/Reservas
        [HttpGet]
        public IEnumerable<Reserva> GetReservas()
        {
            return reservasService.GetAll();
        }

        // GET: api/Reservas/5
        [HttpGet("{id}")]
        public ActionResult<Reserva> GetReserva(int id)
        {
            return reservasService.Get(id);
        }

        // PUT: api/Reservas/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public Task<IActionResult> PutReserva(int id, Reserva reserva)
        {
            return reservasService.Put(id, reserva);
        }

        //// POST: api/Reservas
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public Task<ActionResult<Reserva>> PostUtilizador(Reserva reserva)
        {
            return reservasService.Post(reserva);
        }
    }
}
