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
    public class CentrosController : Controller
    {
        private readonly ICentrosService centrosService;

        public CentrosController(ICentrosService service)
        {
            centrosService = service;
        }

        // GET: api/CentrosGeograficos
        [HttpGet]
        public IEnumerable<Centro> GetCentros()
        {
            return centrosService.GetAll();
        }

        // GET: api/CentrosGeograficos/5
        [HttpGet("{id:int}")]
        public ActionResult<Centro> GetCentros(int id)
        {
            return centrosService.Get(id);
        }

        //GET: api/Centros/name
        [HttpGet("{centroName}")]
        public ActionResult<Centro> GetCentrobyName(string centroName)
        {
            return centrosService.GetbyName(centroName);
        }

        // PUT: api/CentrosGeograficos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public Task<IActionResult> PutCentro(int id, Centro centro)
        {
            return centrosService.Put(id, centro);
        }

        //// POST: api/CentrosGeograficos
        //// To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public Task<ActionResult<Centro>> PostCentro(Centro centro)
        {
            return centrosService.Post(centro);
        }
    }
}
