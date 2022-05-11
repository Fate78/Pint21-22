#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using RoomBooker_API.Service;

namespace RoomBooker_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CentrosGeograficosController : Controller
    {
        private readonly ICentrosGeograficosService centrosGeograficosService;

        public CentrosGeograficosController(ICentrosGeograficosService service)
        {
            centrosGeograficosService = service;
        }

        // GET: api/CentrosGeograficos
        [HttpGet]
        public IEnumerable<CentroGeografico> GetCentrosGeograficos()
        {
            return centrosGeograficosService.GetAll();
        }

        // GET: api/CentrosGeograficos/5
        [HttpGet("{id}")]
        public ActionResult<CentroGeografico> GetCentrosGeografico(int id)
        {
            return centrosGeograficosService.Get(id);
        }

        // PUT: api/CentrosGeograficos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public Task<IActionResult> PutCentroGeografico(int id, CentroGeografico centroGeografico)
        {
            return centrosGeograficosService.Put(id, centroGeografico);
        }

        //// POST: api/CentrosGeograficos
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public Task<ActionResult<CentroGeografico>> PostCentroGeografico(CentroGeografico centroGeografico)
        {
            return centrosGeograficosService.Post(centroGeografico);
        }
    }
}
