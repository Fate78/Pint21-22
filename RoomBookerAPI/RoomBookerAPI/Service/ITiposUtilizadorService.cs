using Microsoft.AspNetCore.Mvc;
using RoomBookerAPI.Models;

namespace RoomBookerAPI.Service
{
    public interface ITiposUtilizadorService
    {
        IEnumerable<TipoUtilizador> GetAll();

        ActionResult<TipoUtilizador> Get(int id);

        Task<IActionResult> Put(int id, TipoUtilizador tipoUtilizador);

        Task<ActionResult<TipoUtilizador>> Post(TipoUtilizador tipoUtilizador);
    }
}
