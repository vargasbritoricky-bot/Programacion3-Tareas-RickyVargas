import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class NombreVacioException extends Exception {
    public NombreVacioException(String mensaje) {
        super(mensaje);
    }
}

class FormatoFechaException extends Exception {
    public FormatoFechaException(String mensaje) {
        super(mensaje);
    }
}

class MenorDeEdadException extends Exception {
    public MenorDeEdadException(String mensaje) {
        super(mensaje);
    }
}

public class RegistroEstudiante {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Solicitar nombre
            System.out.print("Ingrese el nombre completo del estudiante: ");
            String nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                throw new NombreVacioException("El nombre no puede estar vacío.");
            }

            String nombreMayus = nombre.toUpperCase();
            int caracteres = nombre.length();

            System.out.print("Ingrese la fecha de nacimiento (dd/MM/yyyy): ");
            String fechaTexto = scanner.nextLine().trim();

            LocalDate fechaNacimiento;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fechaNacimiento = LocalDate.parse(fechaTexto, formatter);
            } catch (DateTimeParseException e) {
                throw new FormatoFechaException("La fecha no tiene el formato correcto (dd/MM/yyyy).");
            }

            LocalDate hoy = LocalDate.now();
            int edad = Period.between(fechaNacimiento, hoy).getYears();

            if (edad < 18) {
                throw new MenorDeEdadException("El estudiante debe tener al menos 18 años. Edad actual: " + edad);
            }

            System.out.println("\n✅ Registro exitoso:");
            System.out.println("Nombre completo: " + nombreMayus);
            System.out.println("Cantidad de caracteres: " + caracteres);
            System.out.println("Fecha de nacimiento: " + fechaNacimiento);
            System.out.println("Edad: " + edad + " años");

        } catch (NombreVacioException | FormatoFechaException | MenorDeEdadException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }

        scanner.close();
    }
}
