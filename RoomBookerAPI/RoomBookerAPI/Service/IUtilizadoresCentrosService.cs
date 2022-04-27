using Microsoft.AspNetCore.Mvc;
using RoomBookerAPI.Models;

namespace RoomBookerAPI.Service
{
    public interface IUtilizadoresCentrosService
    {
        IEnumerable<UtilizadorCentro> GetAll();

        ActionResult<UtilizadorCentro> Get(int id_utilizador, int id_centro);

        Task<IActionResult> Put(int id_utilizador, int id_centro, UtilizadorCentro utilizadorCentro);

        Task<ActionResult<UtilizadorCentro>> Post(UtilizadorCentro utilizadorCentro);
    }
}
