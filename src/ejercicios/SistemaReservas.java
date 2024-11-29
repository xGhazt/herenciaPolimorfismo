package ejercicios;

import java.util.ArrayList;

// Clase abstracta Vuelo
abstract class Vuelo {
    protected String numeroVuelo;
    protected String origen;
    protected String destino;
    protected String fecha;

    public Vuelo(String numeroVuelo, String origen, String destino, String fecha) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public abstract double calcularPrecio();

    @Override
    public String toString() {
        return "Vuelo: " + numeroVuelo + ", Origen: " + origen + ", Destino: " + destino + ", Fecha: " + fecha;
    }
}

// Interfaz Promocionable
interface Promocionable {
    void aplicarPromocion();
}

// Clase VueloRegular
class VueloRegular extends Vuelo implements Promocionable {
    private int numeroAsientos;
    private double precioBasePorAsiento;

    public VueloRegular(String numeroVuelo, String origen, String destino, String fecha, int numeroAsientos, double precioBasePorAsiento) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
        this.precioBasePorAsiento = precioBasePorAsiento;
    }

    @Override
    public double calcularPrecio() {
        return numeroAsientos * precioBasePorAsiento;
    }

    @Override
    public void aplicarPromocion() {
        precioBasePorAsiento *= 0.9; // Aplica un 10% de descuento
    }
}

// Clase VueloCharter
class VueloCharter extends Vuelo implements Promocionable {
    private double precioTotal;

    public VueloCharter(String numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    public double calcularPrecio() {
        return precioTotal;
    }

    @Override
    public void aplicarPromocion() {
        precioTotal *= 0.85; // Aplica un 15% de descuento
    }
}

// Clase Reservas
class Reservas {
    private ArrayList<Vuelo> vuelos;

    public Reservas() {
        vuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public double calcularPrecioTotal() {
        double total = 0;
        for (Vuelo vuelo : vuelos) {
            total += vuelo.calcularPrecio();
        }
        return total;
    }

    public void aplicarPromociones() {
        for (Vuelo vuelo : vuelos) {
            if (vuelo instanceof Promocionable) {
                ((Promocionable) vuelo).aplicarPromocion();
            }
        }
    }

    public void mostrarVuelos() {
        for (Vuelo vuelo : vuelos) {
            System.out.println(vuelo + ", Precio: " + vuelo.calcularPrecio());
        }
    }
}

// Clase principal
public class SistemaReservas {
    public static void main(String[] args) {
        Reservas reservas = new Reservas();

        VueloRegular vuelo1 = new VueloRegular("VR123", "Buenos Aires", "Madrid", "2024-12-10", 150, 200);
        VueloCharter vuelo2 = new VueloCharter("VC456", "Lima", "Miami", "2024-12-12", 5000);

        reservas.agregarVuelo(vuelo1);
        reservas.agregarVuelo(vuelo2);

        System.out.println("Vuelos antes de promociones:");
        reservas.mostrarVuelos();

        reservas.aplicarPromociones();

        System.out.println("\nVuelos después de promociones:");
        reservas.mostrarVuelos();

        System.out.println("\nPrecio total de reservas: " + reservas.calcularPrecioTotal());
    }
}
