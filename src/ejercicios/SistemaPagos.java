package ejercicios;

import java.util.ArrayList;

// Clase abstracta MetodoPago
abstract class MetodoPago {
    protected String titular;
    protected String numero;

    public MetodoPago(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago();

    @Override
    public String toString() {
        return "Titular: " + titular + ", Número: " + numero;
    }
}

// Interfaz Cancelable
interface Cancelable {
    void cancelarPago();
}

// Clase TarjetaCredito
class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private String codigoSeguridad;

    public TarjetaCredito(String titular, String numero, String fechaExpiracion, String codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void realizarPago() {
        System.out.println("Pago realizado con tarjeta de crédito: " + numero);
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago cancelado para la tarjeta de crédito: " + numero);
    }
}

// Clase PayPal
class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, String numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void realizarPago() {
        System.out.println("Pago realizado con PayPal: " + correoElectronico);
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago cancelado para PayPal: " + correoElectronico);
    }
}

// Clase Pagos
class Pagos {
    private ArrayList<MetodoPago> metodos;

    public Pagos() {
        metodos = new ArrayList<>();
    }

    public void agregarMetodo(MetodoPago metodo) {
        metodos.add(metodo);
    }

    public void realizarPagos() {
        for (MetodoPago metodo : metodos) {
            metodo.realizarPago();
        }
    }

    public void cancelarPagos() {
        for (MetodoPago metodo : metodos) {
            if (metodo instanceof Cancelable) {
                ((Cancelable) metodo).cancelarPago();
            }
        }
    }

    public void mostrarMetodos() {
        for (MetodoPago metodo : metodos) {
            System.out.println(metodo);
        }
    }
}

// Clase principal
public class SistemaPagos {
    public static void main(String[] args) {
        Pagos pagos = new Pagos();

        TarjetaCredito tarjeta = new TarjetaCredito("Juan Perez", "123456789", "12/2025", "123");
        PayPal paypal = new PayPal("Ana Lopez", "987654321", "ana.lopez@gmail.com");

        pagos.agregarMetodo(tarjeta);
        pagos.agregarMetodo(paypal);

        System.out.println("Métodos de pago:");
        pagos.mostrarMetodos();

        System.out.println("\nRealizando pagos:");
        pagos.realizarPagos();

        System.out.println("\nCancelando pagos:");
        pagos.cancelarPagos();
    }
}
