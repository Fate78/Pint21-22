using System;
using System.Collections.Generic;

namespace RoomBookerAPI.Models
{
    public partial class CentroGeografico
    {
        public CentroGeografico()
        {
            Salas = new HashSet<Sala>();
            UtilizadorCentros = new HashSet<UtilizadorCentro>();
        }

        public int IdCentro { get; set; }
        public string NomeCentro { get; set; } = null!;
        public bool Ativo { get; set; }

        public virtual ICollection<Sala> Salas { get; set; }
        public virtual ICollection<UtilizadorCentro> UtilizadorCentros { get; set; }
    }
}
