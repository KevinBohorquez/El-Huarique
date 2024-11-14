package testeos.Controllers.Structures;

import java.time.LocalDateTime;

public class ListaEnlazadaCliente {
    private Cliente cabeza;
    private String hora;

    public ListaEnlazadaCliente() {
        cabeza = null;
        hora = String.valueOf(LocalDateTime.now().getHour())+":"+String.valueOf(LocalDateTime.now().getMinute());
    }

    public String getHoraAsignacion(){
        return this.hora;
    }

    public void agregar(String nombre, String DNI, String mesero){
        Cliente nuevoCliente = new Cliente(nombre, DNI, mesero);
        nuevoCliente.hora_asignacion = hora;
        if (cabeza == null) {
            cabeza = nuevoCliente;
        } else {
            Cliente actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoCliente;
        }
    }

    public void eliminar(String dni) {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        if (cabeza.DNI == dni) {
            cabeza = cabeza.siguiente;
            return;
        }

        Cliente actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.DNI != dni) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }
}
