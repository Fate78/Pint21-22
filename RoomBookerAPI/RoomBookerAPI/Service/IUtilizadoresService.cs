using Microsoft.AspNetCore.Mvc;
using RoomBookerAPI.Models;

namespace RoomBookerAPI.Service
{
    public interface IUtilizadoresService
    {
        IEnumerable<Utilizador> GetAll();

        ActionResult<Utilizador> Get(int id);

        Task<IActionResult> Put(int id, Utilizador utilizador);

        Task<ActionResult<Utilizador>> Post(Utilizador utilizador);

        //Task<IActionResult> Delete(int id);
    }
}
