using Microsoft.AspNetCore.Mvc;
using RoomBooker_API.Models;

namespace RoomBooker_API.Service
{
    public interface IUtilizadoresService
    {
        IEnumerable<Utilizador> GetAll();

        ActionResult<Utilizador> Get(int id);

        ActionResult<Utilizador> GetbyString(string string_input);

        ActionResult<Utilizador> GetReservasbyUtilizadorId(int id_utilizador);

        ActionResult<Utilizador> GetReservasbyUtilizador(string username);

        Task<IActionResult> Put(int id, Utilizador utilizador);

        Task<ActionResult<Utilizador>> Post(Utilizador utilizador);

        //Task<IActionResult> Delete(int id);
    }
}
