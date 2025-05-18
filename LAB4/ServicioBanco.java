import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServicioBanco extends Remote {
    TarjetaCredito crearTarjeta(String numero, String titular, double limiteCredito) throws RemoteException;
    TarjetaCredito obtenerTarjeta(String numeroTarjeta) throws RemoteException;
    List<String> listarTarjetas() throws RemoteException;
    boolean eliminarTarjeta(String numeroTarjeta) throws RemoteException;
}
