package rede;

import java.util.Arrays;

/**
 *
 * @author Roberto Santos Cordeiro
 */
public class Rede {

    // FUNCIONES
    public static int buscarIP(String ip, String[] ips) {
        int posicion = -1;

        if (ips.length > 0) {
            boolean encontrada = false;
            int i = 0;

            while (!encontrada && i < ips.length) {
                if (ips[i].equalsIgnoreCase(ip)) {
                    posicion = i;
                    encontrada = true;
                } else {
                    i++;
                }
            }
        }

        return posicion;
    }

    public static boolean validarIP(String ip) {
        boolean ipValida = true;

        // Si la ip tiene un tamaño entre 7 y 15 y además si no empieza o acaba en punto...
        if ((ip.length() >= 7 && ip.length() <= 15)
                && (ip.charAt(0) != '.' && ip.charAt(ip.length() - 1) != '.')) {

            int contPuntos = 0;
            int i = 0;

            while (ipValida && i < ip.length()) {
                // Si el char en turno NO es un número o un punto...
                if (!Character.isDigit(ip.charAt(i)) && ip.charAt(i) != '.') {
                    ipValida = false;

                } else {
                    if (ip.charAt(i) == '.') {
                        contPuntos++;
                    }
                    i++;
                }

            }

            // Si la ip no tiene caracteres invalidos y tiene el número indicado de puntos...
            if (ipValida && contPuntos == 3) {
                int indiceIni = 0;
                int indiceFin = ip.indexOf('.', indiceIni);
                boolean flag = true;

                while (ipValida && flag) {
                    String seccionIp;

                    if (indiceFin == -1) { // si es la ultima sección...
                        seccionIp = ip.substring(indiceIni);
                    } else {
                        seccionIp = ip.substring(indiceIni, indiceFin);
                    }

                    // Si la sección de la ip NO es una cadena vacia...
                    if (!seccionIp.isEmpty()) {
                        int num = Integer.valueOf(seccionIp);

                        /* 
                         * Si la sección de la ip tiene mas de 1 número y empieza por 0 y
                         * si es menor que 0 o mayor de 255...
                         */
                        if ((seccionIp.length() > 1 && seccionIp.charAt(0) == '0')
                                || (num < 0 || num > 255)) {
                            ipValida = false;

                        } else {
                            if (indiceFin == -1) { // Si es la última sección, para el bucle.
                                flag = false;
                            } else {
                                indiceIni = indiceFin + 1;
                                indiceFin = ip.indexOf('.', indiceIni);
                            }
                        }

                    } else {
                        ipValida = false;
                    }
                }
            }

        } else {
            ipValida = false;
        }

        return ipValida;
    }

    public static boolean validarMascara(String mascara) {
        boolean mascValida = true;

        if (validarIP(mascara)) {
            int valoresValidos[] = {0, 128, 192, 224, 240, 248, 252, 254, 255};

            int indiceIni = 0;
            int indiceFin = mascara.indexOf('.', indiceIni);
            boolean flag = true;
            int ultimaSeccion = 255;

            while (mascValida && flag) {
                String seccionMasc;

                if (indiceFin == -1) { // si es la ultima sección...
                    seccionMasc = mascara.substring(indiceIni);
                } else {
                    seccionMasc = mascara.substring(indiceIni, indiceFin);
                }

                int seccion = Integer.valueOf(seccionMasc);

                // si la seccion en turno NO está en el array de valores permitidos
                if (Arrays.binarySearch(valoresValidos, seccion) < 0
                        || seccion > ultimaSeccion
                        || (seccion == ultimaSeccion
                        && (ultimaSeccion != 255 && ultimaSeccion != 0))
                        || (ultimaSeccion != 255 && seccion != 0)) {

                    mascValida = false;

                } else {
                    ultimaSeccion = seccion;

                    if (indiceFin == -1) { // Si es la última sección, para el bucle.
                        flag = false;
                    } else {
                        indiceIni = indiceFin + 1;
                        indiceFin = mascara.indexOf('.', indiceIni);
                    }
                }

            }

        } else {
            mascValida = false;
        }

        return mascValida;
    }

    /*===================== FIN FUNCIONES ====================================*/
    public static void main(String[] args) {
        String[] ips = {"192.168.0.9", "192.168.100.0", "255.255.255.255", "0.0.0.0"};

        System.out.println(validarIP(ips[2]));

        System.out.println(buscarIP("192.168.100.0", ips));

        System.out.println(validarMascara("255.224.128.0"));

        System.out.println(validarMascara("255.255.0.0"));

    }

}
