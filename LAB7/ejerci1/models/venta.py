from dataclasses import dataclass, field
from decimal import Decimal
from datetime import datetime
from typing import List, Optional
from .detalle_venta import DetalleVenta

@dataclass
class Venta:
    id: int = 0
    cliente_id: str = ""
    fecha: datetime = field(default_factory=datetime.now)
    detalles: List[DetalleVenta] = field(default_factory=list)
    total: Decimal = field(default_factory=lambda: Decimal('0.00'))
    estado: str = "PENDIENTE"  # PENDIENTE, CONFIRMADA, CANCELADA
    
    def __str__(self):
        return f"Venta(id={self.id}, cliente_id='{self.cliente_id}', total={self.total}, estado='{self.estado}')"