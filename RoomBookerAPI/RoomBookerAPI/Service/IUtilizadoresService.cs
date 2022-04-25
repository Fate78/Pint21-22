using RoomBookerAPI.Models;

namespace RoomBookerAPI.Service
{
    public interface IUtilizadoresService
    {
        IEnumerable<Utilizador> GetAll();
    }
}
