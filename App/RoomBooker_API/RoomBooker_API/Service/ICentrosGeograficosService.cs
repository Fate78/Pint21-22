using Microsoft.AspNetCore.Mvc;
using RoomBooker_API.Models;

namespace RoomBooker_API.Service
{
    public interface ICentrosGeograficosService
    {
        IEnumerable<CentroGeografico> GetAll();

        ActionResult<CentroGeografico> Get(int id);

        Task<IActionResult> Put(int id, CentroGeografico centroGeografico);

        Task<ActionResult<CentroGeografico>> Post(CentroGeografico centroGeografico);
    }
}
