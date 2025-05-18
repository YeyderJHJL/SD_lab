import ServicioBanco;
import TarjetaCredito;
import TarjetaCredito;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioBancoImpl extends UnicastRemoteObject implements ServicioBanco {
    private Map<String, TarjetaCredito> tarjetas;
    
    public ServicioBancoImpl() throws RemoteException {
        super();
        tarjetas = new HashMap<>();
    }
    
    @Override
    public TarjetaCredito crearTarjeta(String numero, String titular, double limiteCredito) throws RemoteException {
        if (tarjetas.containsKey(numero)) {
            System.out.println("Error: La tarjeta " + numero + " ya existe");
            return null;
        }
        
        TarjetaCredito nuevaTarjeta = new TarjetaCreditoImpl(numero, titular, limiteCredito);
        tarjetas.put(numero, nuevaTarjeta);
        System.out.println("Tarjeta creada: " + numero + " - " + titular);
        return nuevaTarjeta;
    }
    
    @Override
    public TarjetaCredito obtenerTarjeta(String numeroTarjeta) throws RemoteException {
        return tarjetas.get(numeroTarjeta);
    }
    
    @Override
    public List<String> listarTarjetas() throws RemoteException {
        List<String> listaTarjetas = new ArrayList<>();
        
        for (Map.Entry<String, TarjetaCredito> entry : tarjetas.entrySet()) {
            TarjetaCredito tarjeta = entry.getValue();
            String info = String.format("Tarjeta: %s, Titular: %s, Saldo: %.2f, Limite: %.2f",
                tarjeta.getNumeroTarjeta(),
                tarjeta.getTitular(),
                tarjeta.getSaldo(),
                tarjeta.getLimiteCredito());
            listaTarjetas.add(info);
        }
        
        return listaTarjetas;
    }
    
    @Override
    public boolean eliminarTarjeta(String numeroTarjeta) throws RemoteException {
        if (tarjetas.containsKey(numeroTarjeta)) {
            tarjetas.remove(numeroTarjeta);
            System.out.println("Tarjeta eliminada: " + numeroTarjeta);
            return true;
        }
        return false;
    }
}
