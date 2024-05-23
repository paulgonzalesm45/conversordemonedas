import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        //MENU DEL PROGRAMA

        System.out.println("************************************************************************");
        System.out.println("                        Conversor de Monedas");
        System.out.println("************************************************************************\n");
        while(true){
            System.out.println("Elija una opción: ");
            System.out.println("1) Dolar a Peso Argentino");
            System.out.println("2) Peso Argentino a Dolar ");
            System.out.println("3) Dolar a Boliviano");
            System.out.println("4) Boliviano a Dolar ");
            System.out.println("5) Dolar a Real Brasileño");
            System.out.println("6) Real Brasileño a Dolar ");
            System.out.println("7) Dolar a Peso Chileno");
            System.out.println("8) Peso Chileno a Dolar ");
            System.out.println("9) Dolar a Peso Colombiano ");
            System.out.println("10) Peso Colombiano a Dolar ");          ;
            System.out.println("11) Salir.");

            Scanner lectura = new Scanner(System.in);
            Consultar consulta = new Consultar();
            int opcion;
            opcion = lectura.nextInt();

            switch (opcion) {
                case 1, 2, 3, 4, 5, 6, 7, 8,9,10:
                    try {
                        String monedaOrigen = monedaOrigen(opcion);
                        String monedaDestino = monedaDestino(opcion);
                        Moneda moneda = consulta.buscar(monedaOrigen);

                        System.out.println("************************************************************************");
                        System.out.println("Ingrese la cantidad de "+monedaOrigen+" que desea convertir a "+monedaDestino);
                        double cantidad = lectura.nextDouble();
                        System.out.println("************************************************************************");
                        System.out.println("Resultado: ");
                        System.out.println(cantidad + " [" + monedaOrigen + "] equiale a: " +
                                (moneda.obtenerTasaDeConversion(monedaDestino) * cantidad) + "[" + monedaDestino + "]\n");
                        System.out.println("************************************************************************");


                        LocalDateTime hoy = LocalDateTime.now();

                       String Texto = cantidad + " [" + monedaOrigen + "] equiale a: " +
                                (moneda.obtenerTasaDeConversion(monedaDestino) * cantidad) + "[" + monedaDestino + "]" +" "+ "Consulta realizada el:"+hoy;

                        GeneradorDeArchivo generadorDeArchivo=new GeneradorDeArchivo();
                        generadorDeArchivo.guardarJson(Texto);


                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Finalizando el programa.");
                        System.exit(0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 11:
                    lectura.close();
                    System.exit(0);
                default:
                    System.out.println("************************************************************************");
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    System.out.println("************************************************************************");
            }
        }
    }
    private static String monedaOrigen(int opcion) {
        switch (opcion) {
            case 1,3,5,7,9: return "USD";
            case 2: return "ARS";
            case 4: return "BOB";
            case 6: return "BRL";
            case 8: return "CLP";
            case 10: return "COP";
            default:
                throw new IllegalArgumentException("Opción no válida.");
        }
    }
    private static String monedaDestino(int opcion) {
        switch (opcion) {
            case 2,4,6,8,10: return "USD";
            case 1: return "ARS";
            case 3: return "BOB";
            case 5: return "BRL";
            case 7: return "CLP";
            case 9: return "COP";
            default:
                throw new IllegalArgumentException("Opción no válida.");
        }
    }
}
