import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TarjetaCredito extends Remote {
    String getNumeroTarjeta() throws RemoteException;
    String getTitular() throws RemoteException;
    double getSaldo() throws RemoteException;
    double getLimiteCredito() throws RemoteException;
    boolean realizarCargo(double monto) throws RemoteException;
    boolean realizarPago(double monto) throws RemoteException;
}

