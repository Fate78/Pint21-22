using System;
using System.Collections.Generic;

namespace RoomBookerAPI.Models
{
    public partial class UtilizadorCentro
    {
        public int IdUtilizador { get; set; }
        public int IdCentro { get; set; }
        public bool Ativo { get; set; }

        public virtual CentroGeografico IdCentroNavigation { get; set; } = null!;
        public virtual Utilizador IdUtilizadorNavigation { get; set; } = null!;
    }
}
