package testeos.Controllers.Structures;

public class ColaClientes {
    private Nodo frente;
    private Nodo ultimo;
    private int contador;

    public static class Nodo {
        public String nombre;
        public String DNI;
        public String hora_asignacion;
        public String mesero;
        public int numMesa;
        public Nodo siguiente;

        Nodo(String nombre, String DNI, String hora_asignacion, String mesero, int numMesa) {
            this.nombre = nombre;
            this.DNI = DNI;
            this.hora_asignacion = hora_asignacion;
            this.mesero = mesero;
            this.siguiente = null;
            this.numMesa = numMesa;
        }
    }

    public ColaClientes() {
        frente = null;
        ultimo = null;
        contador = 0;
    }

    public void Encolar(String nombre, String DNI, String hora_asignacion, String mesero, int numMesa) {
        Nodo nuevoNodo = new Nodo(nombre, DNI, hora_asignacion, mesero, numMesa);
        if (ultimo != null) {
            ultimo.siguiente = nuevoNodo;
        }
        ultimo = nuevoNodo;
        if (frente == null) {
            frente = nuevoNodo;
        }
        contador++;
    }

    public void desencolarPorId(String dni) {
        if (frente == null) {
            return;
        }

        if (frente.DNI == dni) {
            frente = frente.siguiente;
            if (frente == null) {
                ultimo = null;
            }
            return;
        }

        Nodo actual = frente;
        while (actual.siguiente != null && actual.siguiente.DNI != dni) {
            actual = actual.siguiente;
        }

        if (actual.siguiente == null) {
            return;
        } else {
            actual.siguiente = actual.siguiente.siguiente;
            if (actual.siguiente == null) {
                ultimo = actual;
            }
        }
    }

    public Nodo getFrente(){
        return frente;
    }

}
