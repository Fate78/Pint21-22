using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace RoomBooker_API.Models
{
    public partial class PintContext : DbContext
    {
        public PintContext()
        {
        }

        public PintContext(DbContextOptions<PintContext> options)
            : base(options)
        {
        }

        public virtual DbSet<CentroGeografico> CentrosGeograficos { get; set; } = null!;
        public virtual DbSet<Reserva> Reservas { get; set; } = null!;
        public virtual DbSet<Sala> Salas { get; set; } = null!;
        public virtual DbSet<Ticket> Tickets { get; set; } = null!;
        public virtual DbSet<TipoUtilizador> TiposUtilizador { get; set; } = null!;
        public virtual DbSet<Utilizador> Utilizadores { get; set; } = null!;
        public virtual DbSet<UtilizadorCentro> UtilizadoresCentros { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseSqlServer("Data Source=DESKTOP-LODJ3UU;Initial Catalog=Pint;User ID=pint;Password=pint");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CentroGeografico>(entity =>
            {
                entity.HasKey(e => e.IdCentro);

                entity.ToTable("CENTRO_GEOGRAFICO");

                entity.HasIndex(e => e.NomeCentro, "UQ__CENTRO_G__C618F5F65FDF14ED")
                    .IsUnique();

                entity.Property(e => e.IdCentro).HasColumnName("ID_CENTRO");

                entity.Property(e => e.Ativo).HasColumnName("ATIVO");

                entity.Property(e => e.Imagem)
                    .HasMaxLength(1)
                    .HasColumnName("IMAGEM");

                entity.Property(e => e.NomeCentro)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("NOME_CENTRO");
            });

            modelBuilder.Entity<Reserva>(entity =>
            {
                entity.HasKey(e => e.IdReserva);

                entity.ToTable("RESERVA");

                entity.Property(e => e.IdReserva).HasColumnName("ID_RESERVA");

                entity.Property(e => e.Ativo).HasColumnName("ATIVO");

                entity.Property(e => e.DataReserva)
                    .HasColumnType("date")
                    .HasColumnName("DATA_RESERVA");

                entity.Property(e => e.HoraFim).HasColumnName("HORA_FIM");

                entity.Property(e => e.HoraInicio).HasColumnName("HORA_INICIO");

                entity.Property(e => e.IdSala).HasColumnName("ID_SALA");

                entity.Property(e => e.IdUtilizador).HasColumnName("ID_UTILIZADOR");

                entity.Property(e => e.NumPessoas).HasColumnName("NUM_PESSOAS");

                entity.HasOne(d => d.IdSalaNavigation)
                    .WithMany(p => p.Reservas)
                    .HasForeignKey(d => d.IdSala)
                    .HasConstraintName("FK_RESERVA_RELATIONS_SALA");

                entity.HasOne(d => d.IdUtilizadorNavigation)
                    .WithMany(p => p.Reservas)
                    .HasForeignKey(d => d.IdUtilizador)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_RESERVA_RELATIONS_UTILIZAD");
            });

            modelBuilder.Entity<Sala>(entity =>
            {
                entity.HasKey(e => e.IdSala);

                entity.ToTable("SALA");

                entity.Property(e => e.IdSala).HasColumnName("ID_SALA");

                entity.Property(e => e.Ativo).HasColumnName("ATIVO");

                entity.Property(e => e.IdCentro).HasColumnName("ID_CENTRO");

                entity.Property(e => e.LotacaoMax).HasColumnName("LOTACAO_MAX");

                entity.Property(e => e.NSala).HasColumnName("N_SALA");

                entity.Property(e => e.TempoMinLimp).HasColumnName("TEMPO_MIN_LIMP");

                entity.HasOne(d => d.IdCentroNavigation)
                    .WithMany(p => p.Salas)
                    .HasForeignKey(d => d.IdCentro)
                    .HasConstraintName("FK_SALA_RELATIONS_CENTRO_G");
            });

            modelBuilder.Entity<Ticket>(entity =>
            {
                entity.HasKey(e => e.IdTicket);

                entity.ToTable("TICKET");

                entity.Property(e => e.IdTicket).HasColumnName("ID_TICKET");

                entity.Property(e => e.Assunto)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("ASSUNTO");

                entity.Property(e => e.Categoria)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("CATEGORIA");

                entity.Property(e => e.Descricao)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("DESCRICAO");

                entity.Property(e => e.IdUtilizador).HasColumnName("ID_UTILIZADOR");

                entity.Property(e => e.Resolvido).HasColumnName("RESOLVIDO");

                entity.HasOne(d => d.IdUtilizadorNavigation)
                    .WithMany(p => p.Tickets)
                    .HasForeignKey(d => d.IdUtilizador)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_TICKET_RELATIONS_UTILIZAD");
            });

            modelBuilder.Entity<TipoUtilizador>(entity =>
            {
                entity.HasKey(e => e.IdTipo);

                entity.ToTable("TIPO_UTILIZADOR");

                entity.HasIndex(e => e.IdTipo, "UQ__TIPO_UTI__DB4DE8AA58CA1A36")
                    .IsUnique();

                entity.Property(e => e.IdTipo)
                    .ValueGeneratedNever()
                    .HasColumnName("ID_TIPO");

                entity.Property(e => e.Descricao)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("DESCRICAO");

                entity.Property(e => e.NomeTipo)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("NOME_TIPO");
            });

            modelBuilder.Entity<Utilizador>(entity =>
            {
                entity.HasKey(e => e.IdUtilizador);

                entity.ToTable("UTILIZADOR");

                entity.HasIndex(e => e.Email, "UQ__UTILIZAD__161CF72445ECEEF7")
                    .IsUnique();

                entity.HasIndex(e => e.NomeUtilizador, "UQ__UTILIZAD__E28486C9B8DFE0D1")
                    .IsUnique();

                entity.Property(e => e.IdUtilizador).HasColumnName("ID_UTILIZADOR");

                entity.Property(e => e.Ativo).HasColumnName("ATIVO");

                entity.Property(e => e.DataNascimento)
                    .HasColumnType("date")
                    .HasColumnName("DATA_NASCIMENTO");

                entity.Property(e => e.Email)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("EMAIL");

                entity.Property(e => e.IdTipo).HasColumnName("ID_TIPO");

                entity.Property(e => e.NomeCompleto)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("NOME_COMPLETO");

                entity.Property(e => e.NomeUtilizador)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("NOME_UTILIZADOR");

                entity.Property(e => e.PalavraPasse)
                    .HasMaxLength(1024)
                    .IsUnicode(false)
                    .HasColumnName("PALAVRA_PASSE");

                entity.Property(e => e.Verificado).HasColumnName("VERIFICADO");

                entity.HasOne(d => d.IdTipoNavigation)
                    .WithMany(p => p.Utilizadors)
                    .HasForeignKey(d => d.IdTipo)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UTILIZAD_RELATIONS_TIPO_UTI");
            });

            modelBuilder.Entity<UtilizadorCentro>(entity =>
            {
                entity.HasKey(e => new { e.IdUtilizador, e.IdCentro });

                entity.ToTable("UTILIZADOR_CENTRO");

                entity.Property(e => e.IdUtilizador).HasColumnName("ID_UTILIZADOR");

                entity.Property(e => e.IdCentro).HasColumnName("ID_CENTRO");

                entity.Property(e => e.Ativo).HasColumnName("ATIVO");

                entity.HasOne(d => d.IdCentroNavigation)
                    .WithMany(p => p.UtilizadorCentros)
                    .HasForeignKey(d => d.IdCentro)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UTILIZAD_GERE_CENTRO_G");

                entity.HasOne(d => d.IdUtilizadorNavigation)
                    .WithMany(p => p.UtilizadorCentros)
                    .HasForeignKey(d => d.IdUtilizador)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UTILIZAD_E_GERIDA_UTILIZAD");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
