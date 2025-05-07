import java.util.LinkedList;
import java.util.Scanner;

// Clase base 
//Patron prototype
class Identidad implements Cloneable {
    private String nombreCompleto;
    private String puesto;
    private String identificador;

    // Constructor 
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

    // Método clonar
    public Identidad clone() {
        try {
            return (Identidad) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No se pudo clonar la identidad", e);
        }
    }

    public String toString() {
        return "Nombre: " + nombreCompleto + " | Puesto: " + puesto + " | ID: " + identificador;
    }
}

class CreadorIdentidades {
    private Identidad prototipo;

    public CreadorIdentidades(Identidad prototipo) {
        this.prototipo = prototipo;
    }

    public Identidad generar(String nombre, String cargo, String id) {
        Identidad copia = prototipo.clone();
        copia.modificar(nombre, cargo, id);
        return copia;
    }

    public void cambiarPrototipo(Identidad nuevoPrototipo) {
        this.prototipo = nuevoPrototipo;
    }
}

public class AplicacionIdentidadess {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList<Identidad> registros = new LinkedList<>();

        Identidad baseInicial = new Identidad("Base", "Inicial", "00000000-0");
        CreadorIdentidades generador = new CreadorIdentidades(baseInicial);

        int eleccion;
        do {
            System.out.println("\n*** MENU IDENTIDADES ***");
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

                    Identidad nueva = generador.generar(nombre, cargo, id);
                    registros.add(nueva);
                    System.out.println("Creado correctamentee");
                    break;

                case 2:
                    if (registros.isEmpty()) {
                        System.out.println("No hay ninguna identidad");
                    } else {
                        System.out.println("Identidades registradas -> ");
                        for (Identidad i : registros) {
                            System.out.println(i);
                        }
                    }
                    break;

                case 3:
                    System.out.println("CHAOOO :)");
                    break;

                default:
                    System.out.println("Opción inválida, trata denuevo");
            }

        } while (eleccion != 3);

        input.close();
    }
}