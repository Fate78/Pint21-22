using System;
using System.Collections.Generic;

namespace RoomBooker_API.Models
{
    public partial class TipoUtilizador
    {
        public TipoUtilizador()
        {
            Utilizadors = new HashSet<Utilizador>();
        }

        public int IdTipo { get; set; }
        public string NomeTipo { get; set; } = null!;
        public string? Descricao { get; set; }

        public virtual ICollection<Utilizador> Utilizadors { get; set; }
    }
}
