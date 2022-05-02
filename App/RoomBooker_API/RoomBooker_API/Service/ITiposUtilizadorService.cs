using Microsoft.AspNetCore.Mvc;
using RoomBooker_API.Models;

namespace RoomBooker_API.Service
{
    public interface ITiposUtilizadorService
    {
        IEnumerable<TipoUtilizador> GetAll();

        ActionResult<TipoUtilizador> Get(int id);

        Task<IActionResult> Put(int id, TipoUtilizador tipoUtilizador);

        Task<ActionResult<TipoUtilizador>> Post(TipoUtilizador tipoUtilizador);
    }
}
