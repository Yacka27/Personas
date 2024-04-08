import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private double sueldoHora;
    private String cargo;

    public Persona(String nombre, String apellido, int edad, String genero, double sueldoHora, String cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.sueldoHora = sueldoHora;
        this.cargo = cargo;
    }

    // Getters y setters para los atributos

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getSueldoHora() {
        return sueldoHora;
    }

    public void setSueldoHora(double sueldoHora) {
        this.sueldoHora = sueldoHora;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}

public class Principal {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();

        // Ingresar datos de personas
        System.out.println("Ingrese los datos de las personas (nombre, apellido, edad, género, sueldo por hora, cargo), o escriba 'fin' para terminar:");

        while (true) {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            if (nombre.equalsIgnoreCase("fin")) break;
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());
            System.out.print("Género: ");
            String genero = scanner.nextLine();
            System.out.print("Sueldo por hora: ");
            double sueldoHora = Double.parseDouble(scanner.nextLine());
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();

            personas.add(new Persona(nombre, apellido, edad, genero, sueldoHora, cargo));
        }

        // Calcular la cantidad de personas almacenadas
        long cantidadPersonas = personas.stream().count();
        System.out.println("\na. Cantidad de personas almacenadas: " + cantidadPersonas);

        // Calcular el promedio de edades de las personas
        double promedioEdades = personas.stream()
                                        .mapToInt(Persona::getEdad)
                                        .average()
                                        .orElse(0);
        System.out.println(" Promedio de edades: " + promedioEdades);

        // Mostrar la cantidad de personas mayores de edad
        long cantidadMayoresEdad = personas.stream()
                                           .filter(persona -> persona.getEdad() >= 18)
                                           .count();
        System.out.println(" Cantidad de personas mayores de edad: " + cantidadMayoresEdad);

        // Mostrar las personas cuyos nombres empiecen con "A"
        System.out.println(" Personas cuyos nombres empiezan con 'A':");
        personas.stream()
                .filter(persona -> persona.getNombre().startsWith("A"))
                .forEach(persona -> System.out.println(persona.getNombre() + " " + persona.getApellido()));

        // Mostrar las personas cuyos apellidos contengan la letra "M"
        System.out.println(" Personas cuyos apellidos contienen la letra 'M':");
        personas.stream()
                .filter(persona -> persona.getApellido().contains("M"))
                .forEach(persona -> System.out.println(persona.getNombre() + " " + persona.getApellido()));

        // Operaciones con lambdas
        // Mostrar el sueldo de 8 horas de cada persona con cargo de director de género masculino (M)
        System.out.println(" Sueldo por 8 horas de directores masculinos:");
        personas.stream()
                .filter(persona -> persona.getCargo().equalsIgnoreCase("Director"))
                .filter(persona -> persona.getGenero().equalsIgnoreCase("Masculino"))
                .forEach(persona -> System.out.println("Nombre: " + persona.getNombre() + " " + persona.getApellido() + " Sueldo por 8 horas: $" + (persona.getSueldoHora() * 8)));

        // Mostrar la primera persona que sea "desarrollador" y sea de género femenino
        System.out.println(" Primera desarrolladora femenina:");
        personas.stream()
                .filter(persona -> persona.getCargo().equalsIgnoreCase("Desarrollador"))
                .filter(persona -> persona.getGenero().equalsIgnoreCase("Femenino"))
                .findFirst()
                .ifPresent(persona -> System.out.println(persona.getNombre() + " " + persona.getApellido()));

        // Mostrar la persona con cargo de desarrollador que más gana por hora
        System.out.println(" Desarrollador que más gana por hora:");
        Optional<Persona> masGana = personas.stream()
                                            .filter(persona -> persona.getCargo().equalsIgnoreCase("Desarrollador"))
                                            .max(Comparator.comparingDouble(Persona::getSueldoHora));
        masGana.ifPresent(persona -> System.out.println(persona.getNombre() + " " + persona.getApellido()));

        // Mostrar todas las mujeres ordenadas por su nombre
        System.out.println(" Mujeres ordenadas por nombre:");
        personas.stream()
                .filter(persona -> persona.getGenero().equalsIgnoreCase("Femenino"))
                .sorted(Comparator.comparing(Persona::getNombre))
                .forEach(persona -> System.out.println(persona.getNombre() + " " + persona.getApellido()));
    }
}
