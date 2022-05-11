using Microsoft.EntityFrameworkCore;
using RoomBookerAPI.Models;
using RoomBookerAPI.Service;

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

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{   
    app.UseDeveloperExceptionPage();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();