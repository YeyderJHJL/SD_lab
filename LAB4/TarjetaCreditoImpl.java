import TarjetaCredito;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TarjetaCreditoImpl extends UnicastRemoteObject implements TarjetaCredito {
    private String numeroTarjeta;
    private String titular;
    private double saldo;
    private double limiteCredito;
    
    public TarjetaCreditoImpl(String numeroTarjeta, String titular, double limiteCredito) throws RemoteException {
        super();
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
        this.saldo = 0.0;
        this.limiteCredito = limiteCredito;
    }
    
    @Override
    public String getNumeroTarjeta() throws RemoteException {
        return numeroTarjeta;
    }
    
    @Override
    public String getTitular() throws RemoteException {
        return titular;
    }
    
    @Override
    public double getSaldo() throws RemoteException {
        return saldo;
    }
    
    @Override
    public double getLimiteCredito() throws RemoteException {
        return limiteCredito;
    }
    
    @Override
    public boolean realizarCargo(double monto) throws RemoteException {
        if (saldo + monto <= limiteCredito) {
            saldo += monto;
            System.out.println("Cargo realizado: " + monto + " a la tarjeta " + numeroTarjeta);
            return true;
        }
        System.out.println("Cargo rechazado: Excede el límite de crédito");
        return false;
    }
    
    @Override
    public boolean realizarPago(double monto) throws RemoteException {
        if (monto > 0) {
            saldo -= monto;
            System.out.println("Pago recibido: " + monto + " para la tarjeta " + numeroTarjeta);
            return true;
        }
        return false;
    }
}
