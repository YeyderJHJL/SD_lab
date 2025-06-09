from dataclasses import dataclass, field
from decimal import Decimal
from typing import Optional

@dataclass
class Producto:
    id: int = 0
    nombre: str = ""
    descripcion: str = ""
    precio: Decimal = field(default_factory=lambda: Decimal('0.00'))
    stock: int = 0
    categoria: str = ""
    
    def __str__(self):
        return f"Producto(id={self.id}, nombre='{self.nombre}', precio={self.precio}, stock={self.stock})"