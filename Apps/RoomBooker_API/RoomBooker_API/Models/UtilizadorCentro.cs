using System;
using System.Collections.Generic;

namespace RoomBooker_API.Models
{
    public partial class UtilizadorCentro
    {
        public int IdUtilizador { get; set; }
        public int IdCentro { get; set; }
        public bool Ativo { get; set; }

        public virtual Centro IdCentroNavigation { get; set; } = null!;
        public virtual Utilizador IdUtilizadorNavigation { get; set; } = null!;
    }
}
