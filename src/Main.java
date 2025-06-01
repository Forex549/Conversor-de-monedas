import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        String key = "89e0e4f6580476c85202eabd";

        HttpClient cliente = HttpClient.newHttpClient();
        Scanner scanner = new Scanner(System.in);

        int opcion = -1;

        Gson gson = new Gson();


        do{
            menu();
            opcion = scanner.nextInt();

            switch (opcion){

                case 1:

                    cambiarMoneda("PEN","USD",cliente,scanner,key,gson);
                    scanner.nextLine();
                    System.out.println("Presiona un boton para continuar");
                    scanner.nextLine();
                    break;
                case 2:

                    cambiarMoneda("USD","PEN",cliente,scanner,key,gson);
                    scanner.nextLine();
                    System.out.println("Presiona un boton para continuar");
                    scanner.nextLine();
                    break;
                case 3:

                    cambiarMoneda("PEN","EUR",cliente,scanner,key,gson);
                    scanner.nextLine();
                    System.out.println("Presiona un boton para continuar");
                    scanner.nextLine();
                    break;

                case 4:

                    cambiarMoneda("EUR","PEN",cliente,scanner,key,gson);
                    scanner.nextLine();
                    System.out.println("Presiona un boton para continuar");
                    scanner.nextLine();
                    break;

                case 5:

                    cambiarMoneda("PEN","VES",cliente,scanner,key,gson);
                    scanner.nextLine();
                    System.out.println("Presiona un boton para continuar");
                    scanner.nextLine();
                    break;
                case 6:
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        }while(opcion != 6);
        System.out.println("CHAU");

    }


    public static void menu() {
        System.out.println("=== CONVERSOR DE MONEDAS ===");
        System.out.println("1. Soles a USD");
        System.out.println("2. USD a Soles");
        System.out.println("3. Soles a Euros");
        System.out.println("4. Euros a Soles");
        System.out.println("5. Soles a Bolívares");
        System.out.println("6. SALIR");
        System.out.println();
        System.out.println("Seleccione una opción:");
    }

    public static void cambiarMoneda(String monedaOrigen, String monedaDestino, HttpClient cliente, Scanner scanner, String key, Gson gson) throws IOException, InterruptedException {
        String direccion = "https://v6.exchangerate-api.com/v6/" + key + "/latest/" + monedaOrigen;

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        String respuesta = response.body();

        JsonElement respuestaJson = gson.fromJson(respuesta, JsonElement.class);
        JsonObject respuestaJsonObject = respuestaJson.getAsJsonObject();

        JsonObject jsonMonedas = respuestaJsonObject.getAsJsonObject("conversion_rates");

        float valorCambio = jsonMonedas.get(monedaDestino).getAsFloat();

        System.out.print("Ingresa cantidad a cambiar: ");
        int cantidadACambiar = scanner.nextInt();

        float resultadoCambio = cantidadACambiar * valorCambio;

        System.out.println(cantidadACambiar+" "+monedaOrigen+" = "+resultadoCambio+" "+monedaDestino);
    }

}