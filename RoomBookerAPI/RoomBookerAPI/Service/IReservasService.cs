using Microsoft.AspNetCore.Mvc;
using RoomBookerAPI.Models;


namespace RoomBookerAPI.Service
{
    public interface IReservasService
    {
        IEnumerable<Reserva> GetAll();

        ActionResult<Reserva> Get(int id);

        Task<IActionResult> Put(int id, Reserva reserva);

        Task<ActionResult<Reserva>> Post(Reserva reserva);
    }
}
