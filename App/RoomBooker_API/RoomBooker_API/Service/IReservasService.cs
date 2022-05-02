using Microsoft.AspNetCore.Mvc;
using RoomBooker_API.Models;


namespace RoomBooker_API.Service
{
    public interface IReservasService
    {
        IEnumerable<Reserva> GetAll();

        ActionResult<Reserva> Get(int id);

        Task<IActionResult> Put(int id, Reserva reserva);

        Task<ActionResult<Reserva>> Post(Reserva reserva);
    }
}
