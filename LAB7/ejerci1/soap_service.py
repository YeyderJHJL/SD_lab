from spyne import Application, rpc, ServiceBase, Iterable, Integer, Unicode, Decimal, ComplexModel, Array
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from decimal import Decimal as PyDecimal
from typing import List, Optional
import threading
from datetime import datetime

# Modelos SOAP
class ProductoSOAP(ComplexModel):
    id = Integer
    nombre = Unicode
    descripcion = Unicode
    precio = Decimal
    stock = Integer
    categoria = Unicode

class DetalleVentaSOAP(ComplexModel):
    producto_id = Integer
    nombre_producto = Unicode
    cantidad = Integer
    precio_unitario = Decimal
    subtotal = Decimal

class VentaSOAP(ComplexModel):
    id = Integer
    cliente_id = Unicode
    fecha = Unicode
    detalles = Array(DetalleVentaSOAP)
    total = Decimal
    estado = Unicode

# Simulación de base de datos en memoria
class DataStore:
    def __init__(self):
        self.productos = [
            {'id': 1, 'nombre': 'Laptop Dell XPS', 'descripcion': 'Laptop alta gama', 
             'precio': PyDecimal('1299.99'), 'stock': 10, 'categoria': 'Electrónicos'},
            {'id': 2, 'nombre': 'Mouse Logitech', 'descripcion': 'Mouse inalámbrico', 
             'precio': PyDecimal('29.99'), 'stock': 50, 'categoria': 'Accesorios'},
            {'id': 3, 'nombre': 'Teclado Mecánico', 'descripcion': 'Teclado gaming RGB', 
             'precio': PyDecimal('89.99'), 'stock': 25, 'categoria': 'Accesorios'},
            {'id': 4, 'nombre': 'Monitor 4K', 'descripcion': 'Monitor 27 pulgadas', 
             'precio': PyDecimal('399.99'), 'stock': 15, 'categoria': 'Electrónicos'},
            {'id': 5, 'nombre': 'SSD 1TB', 'descripcion': 'Disco sólido Samsung', 
             'precio': PyDecimal('129.99'), 'stock': 30, 'categoria': 'Componentes'}
        ]
        self.ventas = []
        self.venta_counter = 1
        self.lock = threading.Lock()

# Instancia global del almacén de datos
data_store = DataStore()

class VentasService(ServiceBase):
    
    @rpc(Integer, _returns=ProductoSOAP)
    def obtener_producto_por_id(ctx, producto_id):
        """Obtener producto por ID"""
        for producto in data_store.productos:
            if producto['id'] == producto_id:
                p = ProductoSOAP()
                p.id = producto['id']
                p.nombre = producto['nombre']
                p.descripcion = producto['descripcion']
                p.precio = producto['precio']
                p.stock = producto['stock']
                p.categoria = producto['categoria']
                return p
        return None
    
    @rpc(_returns=Iterable(ProductoSOAP))
    def obtener_productos(ctx):
        """Obtener todos los productos"""
        productos_soap = []
        for producto in data_store.productos:
            p = ProductoSOAP()
            p.id = producto['id']
            p.nombre = producto['nombre']
            p.descripcion = producto['descripcion']
            p.precio = producto['precio']
            p.stock = producto['stock']
            p.categoria = producto['categoria']
            productos_soap.append(p)
        return productos_soap
    
    @rpc(Unicode, _returns=Iterable(ProductoSOAP))
    def buscar_productos_por_categoria(ctx, categoria):
        """Buscar productos por categoría"""
        productos_soap = []
        for producto in data_store.productos:
            if producto['categoria'].lower() == categoria.lower():
                p = ProductoSOAP()
                p.id = producto['id']
                p.nombre = producto['nombre']
                p.descripcion = producto['descripcion']
                p.precio = producto['precio']
                p.stock = producto['stock']
                p.categoria = producto['categoria']
                productos_soap.append(p)
        return productos_soap
    
    @rpc(Integer, Integer, _returns=Unicode)
    def actualizar_stock(ctx, producto_id, nuevo_stock):
        """Actualizar stock de un producto"""
        with data_store.lock:
            for producto in data_store.productos:
                if producto['id'] == producto_id:
                    producto['stock'] = nuevo_stock
                    return "Stock actualizado correctamente"
        return "Producto no encontrado"
    
    @rpc(Unicode, Array(DetalleVentaSOAP), _returns=Integer)
    def crear_venta(ctx, cliente_id, detalles):
        """Crear una nueva venta"""
        with data_store.lock:
            # Verificar disponibilidad
            for detalle in detalles:
                producto = next((p for p in data_store.productos if p['id'] == detalle.producto_id), None)
                if not producto or producto['stock'] < detalle.cantidad:
                    return -1  # Stock insuficiente
            
            # Calcular total
            total = PyDecimal('0.00')
            detalles_calculados = []
            
            for detalle in detalles:
                producto = next(p for p in data_store.productos if p['id'] == detalle.producto_id)
                subtotal = producto['precio'] * PyDecimal(str(detalle.cantidad))
                
                detalle_calc = {
                    'producto_id': detalle.producto_id,
                    'nombre_producto': producto['nombre'],
                    'cantidad': detalle.cantidad,
                    'precio_unitario': producto['precio'],
                    'subtotal': subtotal
                }
                detalles_calculados.append(detalle_calc)
                total += subtotal
            
            # Crear venta
            venta_id = data_store.venta_counter
            data_store.venta_counter += 1
            
            venta = {
                'id': venta_id,
                'cliente_id': cliente_id,
                'fecha': datetime.now(),
                'detalles': detalles_calculados,
                'total': total,
                'estado': 'PENDIENTE'
            }
            data_store.ventas.append(venta)
            
            # Actualizar stock
            for detalle in detalles:
                producto = next(p for p in data_store.productos if p['id'] == detalle.producto_id)
                producto['stock'] -= detalle.cantidad
            
            return venta_id
    
    @rpc(Integer, _returns=VentaSOAP)
    def obtener_venta(ctx, venta_id):
        """Obtener venta por ID"""
        for venta in data_store.ventas:
            if venta['id'] == venta_id:
                v = VentaSOAP()
                v.id = venta['id']
                v.cliente_id = venta['cliente_id']
                v.fecha = venta['fecha'].isoformat()
                v.total = venta['total']
                v.estado = venta['estado']
                
                # Convertir detalles
                detalles_soap = []
                for detalle in venta['detalles']:
                    d = DetalleVentaSOAP()
                    d.producto_id = detalle['producto_id']
                    d.nombre_producto = detalle['nombre_producto']
                    d.cantidad = detalle['cantidad']
                    d.precio_unitario = detalle['precio_unitario']
                    d.subtotal = detalle['subtotal']
                    detalles_soap.append(d)
                
                v.detalles = detalles_soap
                return v
        return None
    
    @rpc(Integer, _returns=Unicode)
    def confirmar_venta(ctx, venta_id):
        """Confirmar una venta"""
        with data_store.lock:
            for venta in data_store.ventas:
                if venta['id'] == venta_id and venta['estado'] == 'PENDIENTE':
                    venta['estado'] = 'CONFIRMADA'
                    return "Venta confirmada"
        return "No se pudo confirmar la venta"
    
    @rpc(Integer, _returns=Unicode)
    def cancelar_venta(ctx, venta_id):
        """Cancelar una venta"""
        with data_store.lock:
            for venta in data_store.ventas:
                if venta['id'] == venta_id and venta['estado'] != 'CANCELADA':
                    venta['estado'] = 'CANCELADA'
                    
                    # Restaurar stock
                    for detalle in venta['detalles']:
                        producto = next((p for p in data_store.productos 
                                       if p['id'] == detalle['producto_id']), None)
                        if producto:
                            producto['stock'] += detalle['cantidad']
                    
                    return "Venta cancelada"
        return "No se pudo cancelar la venta"
    
    @rpc(Unicode, _returns=Iterable(VentaSOAP))
    def obtener_ventas_por_cliente(ctx, cliente_id):
        """Obtener ventas de un cliente"""
        ventas_soap = []
        for venta in data_store.ventas:
            if venta['cliente_id'] == cliente_id:
                v = VentaSOAP()
                v.id = venta['id']
                v.cliente_id = venta['cliente_id']
                v.fecha = venta['fecha'].isoformat()
                v.total = venta['total']
                v.estado = venta['estado']
                ventas_soap.append(v)
        return ventas_soap
    
    @rpc(Integer, Integer, _returns=Unicode)
    def verificar_disponibilidad(ctx, producto_id, cantidad):
        """Verificar disponibilidad de producto"""
        producto = next((p for p in data_store.productos if p['id'] == producto_id), None)
        if producto and producto['stock'] >= cantidad:
            return "Disponible"
        return "No disponible"

# Configuración de la aplicación SOAP
application = Application([VentasService],
    tns='ventasonline.soap',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

wsgi_app = WsgiApplication(application)
