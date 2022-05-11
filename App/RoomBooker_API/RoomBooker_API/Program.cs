using Microsoft.EntityFrameworkCore;
using RoomBooker_API.Models;
using RoomBooker_API.Service;


var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddDbContext<PintContext>(options => options.UseSqlServer(builder.Configuration.GetConnectionString("DatabaseConnection")));
builder.Services.AddScoped<IUtilizadoresService, UtilizadoresService>();
builder.Services.AddScoped<ITiposUtilizadorService, TiposUtilizadorService>();
builder.Services.AddScoped<ISalasService, SalasService>();
builder.Services.AddScoped<ICentrosGeograficosService, CentrosGeograficosService>();
builder.Services.AddScoped<IUtilizadoresCentrosService, UtilizadoresCentrosService>();
builder.Services.AddScoped<ITicketsService, TicketsService>();
builder.Services.AddScoped<IReservasService, ReservasService>();
builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
