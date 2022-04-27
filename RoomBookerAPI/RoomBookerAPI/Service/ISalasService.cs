using Microsoft.AspNetCore.Mvc;
using RoomBookerAPI.Models;

namespace RoomBookerAPI.Service
{
    public interface ISalasService
    {
        IEnumerable<Sala> GetAll();

        ActionResult<Sala> Get(int id);

        Task<IActionResult> Put(int id, Sala sala);

        Task<ActionResult<Sala>> Post(Sala sala);
    }
}
