using Microsoft.AspNetCore.Mvc;
using RoomBooker_API.Models;

namespace RoomBooker_API.Service
{
    public interface ICentrosService
    {
        IEnumerable<Centro> GetAll();

        ActionResult<Centro> Get(int id);

        ActionResult<Centro> GetbyName(string centroName);

        Task<IActionResult> Put(int id, Centro centro);

        Task<ActionResult<Centro>> Post(Centro centro);
    }
}
