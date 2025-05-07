import java.util.LinkedList;
import java.util.Scanner;

//Clase principal
//Patron SINGLETON
class Identidad implements Cloneable {
    private String nombreCompleto;
    private String puesto;
    private String identificador;
//Constructor
    public Identidad(String nombreCompleto, String puesto, String identificador) {
        this.nombreCompleto = nombreCompleto;
        this.puesto = puesto;
        this.identificador = identificador;
    }

    public void modificar(String nombreCompleto, String puesto, String identificador) {
        this.nombreCompleto = nombreCompleto;
        this.puesto = puesto;
        this.identificador = identificador;
    }

    public Identidad clone() {
        try {
            return (Identidad) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No se pudo clonar", e);
        }
    }

    public String toString() {
        return "Nombre: " + nombreCompleto + " | Puesto: " + puesto + " | ID: " + identificador;
    }
}

class GeneradorIdentidad {
    private static GeneradorIdentidad instancia;
    private final Identidad base;

    private GeneradorIdentidad() {
        base = new Identidad("Base", "Inicial", "00000000-0");
    }

    public static GeneradorIdentidad obtenerInstancia() {
        if (instancia == null) {
            instancia = new GeneradorIdentidad();
        }
        return instancia;
    }

    public Identidad nuevaIdentidad(String nombre, String cargo, String id) {
        Identidad copia = base.clone();
        copia.modificar(nombre, cargo, id);
        return copia;
    }
}

public class AplicacionIdentidades {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList<Identidad> registros = new LinkedList<>();

        int eleccion;
        do {
            System.out.println("*** MENU IDENTIDADES ***");
            System.out.println("1.- Crear identidad");
            System.out.println("2.- Mostrar todas");
            System.out.println("3.- Salir");
            System.out.print("Opción: ");
            eleccion = input.nextInt();
            input.nextLine();

            switch (eleccion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = input.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = input.nextLine();
                    System.out.print("ID: ");
                    String id = input.nextLine();

                    Identidad nueva = GeneradorIdentidad.obtenerInstancia()
                            .nuevaIdentidad(nombre, cargo, id);
                    registros.add(nueva);
                    System.out.println("Creado correctamente, tai listo");
                    break;

                case 2:
                    if (registros.isEmpty()) {
                        System.out.println("No hay nadie ingresado");
                    } else {
                        System.out.println("Identidades registradas:");
                        for (Identidad i : registros) {
                            System.out.println(i);
                        }
                    }
                    break;

                case 3:
                    System.out.println("CHAOO QUE VAYA BIEEN :) ...");
                    break;

                default:
                    System.out.println("Opción incorrecta, ve bien el menu po");
            }
        } while (eleccion != 3);

        input.close();
    }
}
