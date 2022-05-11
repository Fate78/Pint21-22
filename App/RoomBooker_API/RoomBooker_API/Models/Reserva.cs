using System;
using System.Collections.Generic;

namespace RoomBooker_API.Models
{
    public partial class Reserva
    {
        public int IdReserva { get; set; }
        public int? IdSala { get; set; }
        public int IdUtilizador { get; set; }
        public TimeSpan HoraInicio { get; set; }
        public TimeSpan HoraFim { get; set; }
        public DateTime DataReserva { get; set; }
        public int NumPessoas { get; set; }
        public bool Ativo { get; set; }

        public virtual Sala? IdSalaNavigation { get; set; }
        public virtual Utilizador IdUtilizadorNavigation { get; set; } = null!;
    }
}
