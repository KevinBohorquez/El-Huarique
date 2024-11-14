package testeos.Controllers.Structures;

public class Cliente {
    public String nombre;
    public String DNI;
    public String hora_asignacion;
    public String mesero;
    public Cliente siguiente;

    Cliente(String nombre, String DNI, String mesero) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.mesero = mesero;
        this.siguiente = null;
    }
}

