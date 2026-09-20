import java.util.Calendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // =========================
    // DATOS DEL INVENTARIO
    // =========================
    static String[] productos = {
        "Laptop", "Mouse", "Teclado", "Monitor", "Audífonos"
    };

    static double[] precios = {
        45000.0, 850.0, 1200.0, 12000.0, 2500.0
    };

    static int[] cantidades = {
        10, 50, 30, 8, 25
    };

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n======================================");
            System.out.println("       SISTEMA DE EJERCICIOS");
            System.out.println("======================================");
            System.out.println("1. Gestión de inventario");
            System.out.println("2. Matriz de calificaciones");
            System.out.println("3. Búsqueda de producto");
            System.out.println("4. Procesador de datos CSV");
            System.out.println("5. Validador de datos");
            System.out.println("6. Calculadora de fechas");
            System.out.println("0. Salir");
            System.out.println("======================================");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    ejercicio1();
                    break;

                case 2:
                    ejercicio2();
                    break;

                case 3:
                    ejercicio3();
                    break;

                case 4:
                    ejercicio4();
                    break;

                case 5:
                    ejercicio5();
                    break;

                case 6:
                    ejercicio6();
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    // =====================================================
    // EJERCICIO 1 - GESTIÓN DE INVENTARIO
    // =====================================================
    public static void ejercicio1() {

        System.out.println("\n===== EJERCICIO 1: INVENTARIO =====");

        mostrarInventario();

        // Calcular total general
        double totalGeneral = 0;

        for (int i = 0; i < productos.length; i++) {
            totalGeneral += precios[i] * cantidades[i];
        }

        System.out.println("\nTotal general: "
                + String.format("%,.2f", totalGeneral));

        // Producto más caro y más barato
        int posicionCaro = 0;
        int posicionBarato = 0;

        for (int i = 1; i < productos.length; i++) {

            if (precios[i] > precios[posicionCaro]) {
                posicionCaro = i;
            }

            if (precios[i] < precios[posicionBarato]) {
                posicionBarato = i;
            }
        }

        System.out.println("\nProducto más caro: "
                + productos[posicionCaro] + " - "
                + String.format("%,.2f", precios[posicionCaro]));

        System.out.println("Producto más barato: "
                + productos[posicionBarato] + " - "
                + String.format("%,.2f", precios[posicionBarato]));

        // Inventario bajo
        System.out.println("\n--- ALERTA DE INVENTARIO BAJO ---");

        for (int i = 0; i < productos.length; i++) {

            if (cantidades[i] < 15) {
                System.out.println(productos[i]
                        + " - " + cantidades[i] + " unidades");
            }
        }

        ordenarInventario();

        System.out.println("\n--- INVENTARIO ORDENADO DE MAYOR A MENOR PRECIO ---");
        mostrarInventario();
    }

    public static void mostrarInventario() {

        System.out.println();
        System.out.println(String.format(
                "%-15s %12s %10s %15s",
                "PRODUCTO", "PRECIO", "CANTIDAD", "VALOR TOTAL"
        ));

        System.out.println("------------------------------------------------------------");

        double total = 0;

        for (int i = 0; i < productos.length; i++) {

            double valorTotal = precios[i] * cantidades[i];
            total += valorTotal;

            System.out.println(String.format(
                    "%-15s %,12.2f %10d %,15.2f",
                    productos[i],
                    precios[i],
                    cantidades[i],
                    valorTotal
            ));
        }

        System.out.println("------------------------------------------------------------");

        System.out.println(String.format(
                "%-39s %,15.2f",
                "TOTAL GENERAL",
                total
        ));
    }

    public static void ordenarInventario() {

        for (int i = 0; i < productos.length - 1; i++) {

            for (int j = 0; j < productos.length - 1 - i; j++) {

                if (precios[j] < precios[j + 1]) {

                    String tempProducto = productos[j];
                    productos[j] = productos[j + 1];
                    productos[j + 1] = tempProducto;

                    double tempPrecio = precios[j];
                    precios[j] = precios[j + 1];
                    precios[j + 1] = tempPrecio;

                    int tempCantidad = cantidades[j];
                    cantidades[j] = cantidades[j + 1];
                    cantidades[j + 1] = tempCantidad;
                }
            }
        }
    }

    // =====================================================
    // EJERCICIO 2 - MATRIZ DE CALIFICACIONES
    // =====================================================
    public static void ejercicio2() {

        System.out.println("\n===== EJERCICIO 2: CALIFICACIONES =====");

        String[] nombres = {
            "Ana", "Luis", "Maria", "Carlos"
        };

        double[][] parciales = {
            {8.5, 9.0, 7.5},
            {6.0, 5.5, 7.0},
            {9.0, 8.5, 9.5},
            {4.5, 6.0, 5.0}
        };

        System.out.println(String.format(
                "%-12s %10s %10s %10s %12s %12s",
                "NOMBRE", "P1", "P2", "P3", "PROMEDIO", "ESTADO"
        ));

        System.out.println("------------------------------------------------------------");

        int aprobados = 0;
        int reprobados = 0;

        double mejorPromedio = -1;
        double peorPromedio = 100;

        String mejorEstudiante = "";
        String peorEstudiante = "";

        for (int i = 0; i < nombres.length; i++) {

            double suma = 0;

            for (int j = 0; j < parciales[i].length; j++) {
                suma += parciales[i][j];
            }

            double promedio = suma / parciales[i].length;

            String estado;

            if (promedio >= 6.0) {
                estado = "Aprobado";
                aprobados++;
            } else {
                estado = "Reprobado";
                reprobados++;
            }

            System.out.println(String.format(
                    "%-12s %10.1f %10.1f %10.1f %12.2f %12s",
                    nombres[i],
                    parciales[i][0],
                    parciales[i][1],
                    parciales[i][2],
                    promedio,
                    estado
            ));

            if (promedio > mejorPromedio) {
                mejorPromedio = promedio;
                mejorEstudiante = nombres[i];
            }

            if (promedio < peorPromedio) {
                peorPromedio = promedio;
                peorEstudiante = nombres[i];
            }
        }

        // Promedio de cada parcial
        System.out.println("\n--- PROMEDIO DE CADA PARCIAL ---");

        for (int j = 0; j < 3; j++) {

            double suma = 0;

            for (int i = 0; i < nombres.length; i++) {
                suma += parciales[i][j];
            }

            double promedioParcial = suma / nombres.length;

            System.out.println(
                    "Parcial " + (j + 1) + ": "
                    + String.format("%.2f", promedioParcial)
            );
        }

        System.out.println("\nMejor estudiante: "
                + mejorEstudiante + " - "
                + String.format("%.2f", mejorPromedio));

        System.out.println("Peor estudiante: "
                + peorEstudiante + " - "
                + String.format("%.2f", peorPromedio));

        System.out.println("\nEstudiantes aprobados: " + aprobados);
        System.out.println("Estudiantes reprobados: " + reprobados);
    }

    // =====================================================
    // EJERCICIO 3 - BÚSQUEDA Y REEMPLAZO
    // =====================================================
    public static void ejercicio3() {

        System.out.println("\n===== EJERCICIO 3: BÚSQUEDA =====");

        String[] busquedas = {
            "lap", "MOUSE", "tor"
        };

        for (String busqueda : busquedas) {

            int indice = buscarProducto(productos, busqueda);

            System.out.println("\nBúsqueda: " + busqueda);

            if (indice != -1) {

                System.out.println("Producto encontrado:");
                System.out.println("Nombre: " + productos[indice]);
                System.out.println("Precio: "
                        + String.format("%,.2f", precios[indice]));
                System.out.println("Cantidad: " + cantidades[indice]);

            } else {
                System.out.println("Producto no encontrado.");
            }
        }
    }

    public static int buscarProducto(
            String[] productos,
            String busqueda) {

        for (int i = 0; i < productos.length; i++) {

            if (productos[i]
                    .toLowerCase()
                    .contains(busqueda.toLowerCase())) {

                return i;
            }
        }

        return -1;
    }

    // =====================================================
    // EJERCICIO 4 - PROCESADOR CSV
    // =====================================================
    public static void ejercicio4() {

        System.out.println("\n===== EJERCICIO 4: DATOS CSV =====");

        String datos =
                "Juan Perez,juanp@empresa.com,Sistemas,35000\n"
                + "Ana Lopez,anal@empresa.com,Contabilidad,28000\n"
                + "Carlos Ruiz,carlosr@gmail.com,Sistemas,42000\n"
                + "Maria Diaz,mariad@empresa.com,RRHH,31000\n"
                + "Luis Marte,luism@gmail.com,Sistemas,38000";

        String[] empleados = datos.split("\n");

        System.out.println(String.format(
                "%-15s %-25s %-15s %12s",
                "NOMBRE", "CORREO", "DEPARTAMENTO", "SALARIO"
        ));

        System.out.println("----------------------------------------------------------------");

        double sumaSalarios = 0;

        double mayorSalario = -1;
        double menorSalario = Double.MAX_VALUE;

        String empleadoMayor = "";
        String empleadoMenor = "";

        for (String empleado : empleados) {

            StringTokenizer tokenizer =
                    new StringTokenizer(empleado, ",");

            String nombre = tokenizer.nextToken();
            String correo = tokenizer.nextToken();
            String departamento = tokenizer.nextToken();
            double salario =
                    Double.parseDouble(tokenizer.nextToken());

            System.out.println(String.format(
                    "%-15s %-25s %-15s %,12.2f",
                    nombre,
                    correo,
                    departamento,
                    salario
            ));

            sumaSalarios += salario;

            if (salario > mayorSalario) {
                mayorSalario = salario;
                empleadoMayor = nombre;
            }

            if (salario < menorSalario) {
                menorSalario = salario;
                empleadoMenor = nombre;
            }
        }

        System.out.println("\n--- EMPLEADOS DE SISTEMAS ---");

        for (String empleado : empleados) {

            String[] datosEmpleado = empleado.split(",");

            if (datosEmpleado[2].equals("Sistemas")) {
                System.out.println(datosEmpleado[0]);
            }
        }

        System.out.println("\n--- CORREOS @empresa.com ---");

        for (String empleado : empleados) {

            String[] datosEmpleado = empleado.split(",");

            if (datosEmpleado[1].endsWith("@empresa.com")) {
                System.out.println(datosEmpleado[0]
                        + " - "
                        + datosEmpleado[1]);
            }
        }

        System.out.println("\n--- NOMBRES Y CORREOS FORMATEADOS ---");

        for (String empleado : empleados) {

            String[] datosEmpleado = empleado.split(",");

            String nombreMayusculas =
                    datosEmpleado[0].toUpperCase();

            String correoMinusculas =
                    datosEmpleado[1].toLowerCase();

            System.out.println(
                    nombreMayusculas
                    + " - "
                    + correoMinusculas
            );
        }

        double promedioSalario =
                sumaSalarios / empleados.length;

        System.out.println("\nSalario promedio: "
                + String.format("%,.2f", promedioSalario));

        System.out.println("Empleado que gana más: "
                + empleadoMayor + " - "
                + String.format("%,.2f", mayorSalario));

        System.out.println("Empleado que gana menos: "
                + empleadoMenor + " - "
                + String.format("%,.2f", menorSalario));
    }

    // =====================================================
    // EJERCICIO 5 - VALIDADOR
    // =====================================================
    public static void ejercicio5() {

        System.out.println("\n===== EJERCICIO 5: VALIDADOR =====");

        String resultado1 = validarEstudiante(
                "Juan Perez",
                "2024-1234",
                "juan@universidad.edu"
        );

        String resultado2 = validarEstudiante(
                "Ana",
                "2024-1234",
                "ana@universidad.edu"
        );

        String resultado3 = validarEstudiante(
                "Carlos Ruiz",
                "2019-1234",
                "carlos@gmail.com"
        );

        System.out.println("\nCaso 1:");
        System.out.println(resultado1);

        System.out.println("\nCaso 2:");
        System.out.println(resultado2);

        System.out.println("\nCaso 3:");
        System.out.println(resultado3);
    }

    public static String validarEstudiante(
            String nombre,
            String matricula,
            String correo) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return "Error: el nombre está vacío.";
        }

        String[] palabras = nombre.trim().split("\\s+");

        if (palabras.length < 2) {
            return "Error: el nombre debe tener al menos 2 palabras.";
        }

        if (matricula == null
                || !matricula.startsWith("202")
                || matricula.length() != 9) {

            return "Error: la matrícula debe comenzar con 202 y tener 9 caracteres.";
        }

        if (correo == null
                || !correo.contains("@")
                || !correo.endsWith(".edu")) {

            return "Error: el correo debe contener @ y terminar en .edu.";
        }

        return "Datos válidos.";
    }

    // =====================================================
    // EJERCICIO 6 - CALCULADORA DE FECHAS
    // =====================================================
    public static void ejercicio6() {

        System.out.println("\n===== EJERCICIO 6: FECHAS =====");

        Calendar fechaActual = Calendar.getInstance();

        SimpleDateFormat formato1 =
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        SimpleDateFormat formato2 =
                new SimpleDateFormat("dd-MM-yyyy");

        SimpleDateFormat formato3 =
                new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy");

        System.out.println("\nFecha actual:");
        System.out.println(formato1.format(fechaActual.getTime()));
        System.out.println(formato2.format(fechaActual.getTime()));
        System.out.println(formato3.format(fechaActual.getTime()));

        Calendar fecha90Dias = (Calendar) fechaActual.clone();
        fecha90Dias.add(Calendar.DAY_OF_YEAR, 90);

        System.out.println("\nFecha dentro de 90 días: "
                + formato2.format(fecha90Dias.getTime()));

        Calendar fecha6Meses = (Calendar) fechaActual.clone();
        fecha6Meses.add(Calendar.MONTH, -6);

        System.out.println("Fecha hace 6 meses: "
                + formato2.format(fecha6Meses.getTime()));

        System.out.print("\nIngrese su año de nacimiento: ");
        int anioNacimiento = scanner.nextInt();

        int anioActual =
                fechaActual.get(Calendar.YEAR);

        int edad = anioActual - anioNacimiento;

        System.out.println("Edad aproximada: " + edad + " años");

        Calendar finDeAnio = Calendar.getInstance();

        finDeAnio.set(
                anioActual,
                Calendar.DECEMBER,
                31,
                0,
                0,
                0
        );

        finDeAnio.set(Calendar.MILLISECOND, 0);

        long diferencia =
                finDeAnio.getTimeInMillis()
                - fechaActual.getTimeInMillis();

        long diasFaltantes =
                diferencia / (1000 * 60 * 60 * 24);

        System.out.println(
                "Días que faltan para el 31 de diciembre: "
                + diasFaltantes
        );

        scanner.nextLine();
    }
}