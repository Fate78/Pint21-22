using Microsoft.AspNetCore.Mvc;
using RoomBookerAPI.Models;

namespace RoomBookerAPI.Service
{
    public interface ICentrosGeograficosService
    {
        IEnumerable<CentroGeografico> GetAll();

        ActionResult<CentroGeografico> Get(int id);

        Task<IActionResult> Put(int id, CentroGeografico centroGeografico);

        Task<ActionResult<CentroGeografico>> Post(CentroGeografico centroGeografico);
    }
}
