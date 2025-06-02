from dataclasses import dataclass, field
from decimal import Decimal

@dataclass
class DetalleVenta:
    producto_id: int = 0
    nombre_producto: str = ""
    cantidad: int = 0
    precio_unitario: Decimal = field(default_factory=lambda: Decimal('0.00'))
    subtotal: Decimal = field(default_factory=lambda: Decimal('0.00'))
    
    def __post_init__(self):
        if self.precio_unitario and self.cantidad:
            self.subtotal = self.precio_unitario * Decimal(str(self.cantidad))
    
    def calcular_subtotal(self):
        self.subtotal = self.precio_unitario * Decimal(str(self.cantidad))
        return self.subtotal
    
    def __str__(self):
        return f"DetalleVenta(producto_id={self.producto_id}, cantidad={self.cantidad}, subtotal={self.subtotal})"
