using System;
using System.Collections.Generic;

namespace RoomBookerAPI.Models
{
    public partial class TipoUtilizador
    {
        //public TipoUtilizador()
        //{
        //    Utilizadores = new HashSet<Utilizador>();
        //}

        public int IdTipo { get; set; }
        public string NomeTipo { get; set; } = null!;
        public string? Descricao { get; set; }

        //public virtual ICollection<Utilizador> Utilizadores { get; set; }
    }
}
