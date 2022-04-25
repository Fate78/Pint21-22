using System;
using System.Collections.Generic;

namespace RoomBookerAPI.Models
{
    public partial class Ticket
    {
        public int IdTicket { get; set; }
        public int IdUtilizador { get; set; }
        public string Assunto { get; set; } = null!;
        public string Categoria { get; set; } = null!;
        public string Descricao { get; set; } = null!;
        public bool Resolvido { get; set; }

        public virtual Utilizador IdUtilizadorNavigation { get; set; } = null!;
    }
}
