using System;
using System.Collections.Generic;

namespace RoomBookerAPI.Models
{
    public partial class Utilizador
    {
        public Utilizador()
        {
            Reservas = new HashSet<Reserva>();
            Tickets = new HashSet<Ticket>();
            UtilizadorCentros = new HashSet<UtilizadorCentro>();
        }

        public int IdUtilizador { get; set; }
        public int IdTipo { get; set; }
        public string NomeUtilizador { get; set; } = null!;
        public string NomeCompleto { get; set; } = null!;
        public string PalavraPasse { get; set; } = null!;
        public string Email { get; set; } = null!;
        public DateTime? DataNascimento { get; set; }
        public bool Verificado { get; set; }
        public bool Ativo { get; set; }

        //public virtual TipoUtilizador IdTipoNavigation { get; set; } = null!;
        public virtual ICollection<Reserva> Reservas { get; set; }
        public virtual ICollection<Ticket> Tickets { get; set; }
        public virtual ICollection<UtilizadorCentro> UtilizadorCentros { get; set; }
    }
}
