using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;

namespace RoomBooker_API.Service
{
    public class UtilizadoresService : IUtilizadoresService
    {
        PintContext pintContext;
        public UtilizadoresService(PintContext context)
        {
            pintContext = context;
        }

        public IEnumerable<Utilizador> GetAll()
        {
            return pintContext.Utilizadores;
        }

        public ActionResult<Utilizador> Get(int id)
        {
            var utilizador = pintContext.Utilizadores.Find(id);
            
            if(utilizador == null)
            {
                return new NotFoundResult();
            }

            return utilizador;
        }

        public ActionResult<Utilizador> GetbyString(string string_input)
        {
            var utilizador = new Utilizador();

            //Is string Email
            if (IsValidEmail(string_input))
            {
                utilizador = pintContext.Utilizadores.Single(user => user.Email == string_input);
            }
            else { utilizador = pintContext.Utilizadores.Single(user => user.NomeUtilizador == string_input); }
            
            if (utilizador == null)
            {
                return new NotFoundResult();
            }

            return utilizador;
        }

        bool IsValidEmail(string email)
        {
            var trimmedEmail = email.Trim();

            if (trimmedEmail.EndsWith("."))
            {
                return false;
            }
            try
            {
                var addr = new System.Net.Mail.MailAddress(email);
                return addr.Address == trimmedEmail;
            }
            catch
            {
                return false;
            }
        }

        public ActionResult<Utilizador> GetReservasbyUtilizadorId(int id_utilizador)
        {
            var utilizador = pintContext.Utilizadores
                .Include(i => i.Reservas)
                .Single(utilizador => utilizador.IdUtilizador == id_utilizador);
            
            if (utilizador == null)
            {
                return new NotFoundResult();
            }

            return utilizador;
        }

        public ActionResult<Utilizador> GetReservasbyUtilizador(string username)
        {
            var utilizador = pintContext.Utilizadores
                .Include(i => i.Reservas)
                .Single(utilizador => utilizador.NomeUtilizador == username);
           
            if (utilizador == null)
            {
                return new NotFoundResult();
            }

            return utilizador;
        }

        public async Task<IActionResult> Put(int id, Utilizador utilizador)
        {
            if (id != utilizador.IdUtilizador)
            {
                return new BadRequestResult();
            }

            pintContext.Entry(utilizador).State = EntityState.Modified;

            try
            {
                await pintContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UtilizadorExists(id))
                {
                    return new NotFoundResult();
                }
                else
                {
                    throw;
                }
            }

            return new NoContentResult();
        }
        public async Task<ActionResult<Utilizador>> Post(Utilizador utilizador)
        {
            pintContext.Utilizadores.Add(utilizador);
            await pintContext.SaveChangesAsync();

            return new CreatedAtRouteResult("GetUtilizadores", new { id = utilizador.IdUtilizador }, utilizador);
        }
        private bool UtilizadorExists(int id)
        {
            return pintContext.Utilizadores.Any(e => e.IdUtilizador == id);
        }
    }
}
