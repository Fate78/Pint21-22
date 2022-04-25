using RoomBookerAPI.Models;

namespace RoomBookerAPI.Service
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
    }
}
