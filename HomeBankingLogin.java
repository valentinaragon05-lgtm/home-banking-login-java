import java.util.Scanner;

public class HomeBankingLogin {

    public static void main(String[] args) {

        // Defino credenciales correctas
        String userCorrecto = "admin";
        String passCorrecta = "admin";

        // Defino Scanner y doy bienvenida
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido al home banking!");

        // Seteo ingInc y variable ingreso para entrar al while
        int ingInc = 0;
        String ingreso = "s";

        int[] tokens = new int[3];
        int indNuevoToken = -1;
        int token = 0;

        // While que entra si o si la primera vez
        while (ingreso.equalsIgnoreCase("s")) {

            boolean tokenRepetido = true;

            // WHILE para validar token repetido
            // La primera vez entra si o si
            while (tokenRepetido == true) {
                token = generarToken();

                indNuevoToken++;
                tokens[indNuevoToken] = token;
                tokenRepetido = false;

                // Recorro el array de tokens para ver si
                // ya existe el nuevo token
                for (int i = 0; i < indNuevoToken; i++) {
                    if (tokens[i] == token) {
                        tokenRepetido = true;
                        indNuevoToken--;
                    }
                }
            }

            System.out.println("Token: " + token);

            // Solicito credenciales y token
            System.out.println("Ingrese usuario");
            String userUsuario = teclado.next();
            System.out.println("Ingrese contraseña");
            String passUsuario = teclado.next();
            System.out.println("Ingrese token");
            int tokenUsuario = teclado.nextInt();

            // Si es todo correcto
            if (validarCredenciales(userCorrecto, userUsuario,
                    passCorrecta, passUsuario, token, tokenUsuario)) {
                System.out.println("Bienvenido!");
                break;
            } else { // Sino...
                // Sumo un error
                ingInc++;

                // Verifico si llego a 3 errores
                if (ingInc == 3) {
                    System.out.println("Usuario bloqueado");
                    break;
                }

                // Si no llego a 3, informo que es incorrecto y
                // pregunto si desea volver a ingresar
                System.out.println("Credenciales incorrectas.");
                System.out.println("¿Desea ingresar nuevamente s/n?");

                // Guardo respuesta en la misma variable del while
                ingreso = teclado.next();

                // Si eligió N, despido y break
                if (!ingreso.equalsIgnoreCase("s")) {
                    System.out.println("Gracias, chau.");
                    break;
                }
            }
        }
    }

    public static int generarToken() {
        int token = (int) (Math.random() * (0 - 999999 + 1) + 999999);
        return token;
    }

    public static boolean validarCredenciales(
            String uCorrecto, String uUsuario,
            String pCorrecta, String pUsuario,
            int tCorrecto, int tUsuario) {

        if (uCorrecto.equalsIgnoreCase(uUsuario) &&
                pCorrecta.equals(pUsuario) &&
                tCorrecto == tUsuario) {
            return true;
        } else {
            return false;
        }
    }
}
