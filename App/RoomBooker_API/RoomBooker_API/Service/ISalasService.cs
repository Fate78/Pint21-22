using Microsoft.AspNetCore.Mvc;
using RoomBooker_API.Models;

namespace RoomBooker_API.Service
{
    public interface ISalasService
    {
        IEnumerable<Sala> GetAll();

        ActionResult<Sala> Get(int id);

        Task<IActionResult> Put(int id, Sala sala);

        Task<ActionResult<Sala>> Post(Sala sala);
    }
}
