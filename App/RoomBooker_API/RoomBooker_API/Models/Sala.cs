using System;
using System.Collections.Generic;

namespace RoomBooker_API.Models
{
    public partial class Sala
    {
        public Sala()
        {
            Reservas = new HashSet<Reserva>();
        }

        public int IdSala { get; set; }
        public int? IdCentro { get; set; }
        public int NSala { get; set; }
        public int LotacaoMax { get; set; }
        public TimeSpan TempoMinLimp { get; set; }
        public bool Ativo { get; set; }

        public virtual CentroGeografico? IdCentroNavigation { get; set; }
        public virtual ICollection<Reserva> Reservas { get; set; }
    }
}
