using Microsoft.AspNetCore.Mvc;
using RoomBookerAPI.Models;

namespace RoomBookerAPI.Service
{
    public interface ITicketsService
    {
        IEnumerable<Ticket> GetAll();

        ActionResult<Ticket> Get(int id);

        Task<IActionResult> Put(int id, Ticket ticket);

        Task<ActionResult<Ticket>> Post(Ticket ticket);
    }
}
