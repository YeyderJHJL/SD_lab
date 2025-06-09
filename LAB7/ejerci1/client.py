from zeep import Client
from decimal import Decimal

class VentasSOAPClient:
    def __init__(self, wsdl_url="http://127.0.0.1:8080/?wsdl"):
        self.client = Client(wsdl_url)
        
    def obtener_productos(self):
        """Obtener todos los productos"""
        try:
            response = self.client.service.obtener_productos()
            return response
        except Exception as e:
            print(f"Error al obtener productos: {e}")
            return None
    
    def obtener_producto_por_id(self, producto_id):
        """Obtener producto por ID"""
        try:
            response = self.client.service.obtener_producto_por_id(producto_id=producto_id)
            return response
        except Exception as e:
            print(f"Error al obtener producto: {e}")
            return None
    
    def buscar_productos_por_categoria(self, categoria):
        """Buscar productos por categoría"""
        try:
            response = self.client.service.buscar_productos_por_categoria(categoria=categoria)
            return response
        except Exception as e:
            print(f"Error al buscar productos: {e}")
            return None
    
    def crear_venta(self, cliente_id, detalles):
        """Crear una nueva venta"""
        try:
            # Convertir detalles al formato esperado por SOAP
            detalles_soap = []
            for detalle in detalles:
                detalle_soap = {
                    'producto_id': detalle['producto_id'],
                    'cantidad': detalle['cantidad']
                }
                detalles_soap.append(detalle_soap)
            
            response = self.client.service.crear_venta(
                cliente_id=cliente_id, 
                detalles=detalles_soap
            )
            return response
        except Exception as e:
            print(f"Error al crear venta: {e}")
            return None
    
    def obtener_venta(self, venta_id):
        """Obtener venta por ID"""
        try:
            response = self.client.service.obtener_venta(venta_id=venta_id)
            return response
        except Exception as e:
            print(f"Error al obtener venta: {e}")
            return None
    
    def confirmar_venta(self, venta_id):
        """Confirmar venta"""
        try:
            response = self.client.service.confirmar_venta(venta_id=venta_id)
            return response
        except Exception as e:
            print(f"Error al confirmar venta: {e}")
            return None
    
    def cancelar_venta(self, venta_id):
        """Cancelar venta"""
        try:
            response = self.client.service.cancelar_venta(venta_id=venta_id)
            return response
        except Exception as e:
            print(f"Error al cancelar venta: {e}")
            return None

def test_client():
    """Función de prueba del cliente"""
    print("=== CLIENTE DE PRUEBA SOAP VENTAS ===\n")
    
    # Crear cliente
    client = VentasSOAPClient()
    
    # 1. Obtener todos los productos
    print("1. Obteniendo todos los productos:")
    productos = client.obtener_productos()
    if productos:
        for producto in productos:
            print(f"   - {producto.nombre}: ${producto.precio} (Stock: {producto.stock})")
    print()
    
    # 2. Buscar productos por categoría
    print("2. Buscando productos de categoría 'Electrónicos':")
    electronicos = client.buscar_productos_por_categoria("Electrónicos")
    if electronicos:
        for producto in electronicos:
            print(f"   - {producto.nombre}: ${producto.precio}")
    print()
    
    # 3. Crear una venta
    print("3. Creando nueva venta:")
    detalles_venta = [
        {'producto_id': 1, 'cantidad': 1},  # Laptop
        {'producto_id': 2, 'cantidad': 2}   # Mouse
    ]
    
    venta_id = client.crear_venta("cliente123", detalles_venta)
    if venta_id and venta_id > 0:
        print(f"   Venta creada con ID: {venta_id}")
        
        # 4. Obtener detalles de la venta
        print("4. Obteniendo detalles de la venta:")
        venta = client.obtener_venta(venta_id)
        if venta:
            print(f"   Cliente: {venta.cliente_id}")
            print(f"   Total: ${venta.total}")
            print(f"   Estado: {venta.estado}")
            print("   Detalles:")
            for detalle in venta.detalles:
                print(f"     - {detalle.nombre_producto}: {detalle.cantidad} x ${detalle.precio_unitario} = ${detalle.subtotal}")
        
        # 5. Confirmar venta
        print("5. Confirmando venta:")
        resultado = client.confirmar_venta(venta_id)
        print(f"   {resultado}")
    else:
        print("   Error al crear venta (posible stock insuficiente)")
    print()
    
    print("=== FIN DE PRUEBAS ===")

if __name__ == "__main__":
    test_client()