class Evento:
    def __init__(self, proceso, nombre, tiempo):
        self.proceso = proceso
        self.nombre = nombre
        self.tiempo = tiempo

    def __str__(self):
        return f"{self.proceso}-{self.nombre} @ t={self.tiempo}"

# Simulamos 3 procesos con reloj lógico
P1 = []
P2 = []
P3 = []

# P1 envía un mensaje
t1 = 1
P1.append(Evento("P1", "envía solicitud a P2", t1))

# P2 recibe el mensaje y actualiza su reloj
t2 = max(t1, 2) + 1
P2.append(Evento("P2", "recibe solicitud", t2))

# P2 responde a P1
t3 = t2 + 1
P2.append(Evento("P2", "envía respuesta a P1", t3))

# P1 recibe la respuesta
t4 = max(t1, t3) + 1
P1.append(Evento("P1", "recibe respuesta", t4))

# P3 hace sus tareas independientes
P3.append(Evento("P3", "proceso local", 1))
P3.append(Evento("P3", "tarea interna", 2))

# Reunimos y ordenamos los eventos
todos = P1 + P2 + P3
ordenados = sorted(todos, key=lambda e: e.tiempo)

print("=== Orden lógico de eventos (ocurre antes) ===")
for evento in ordenados:
    print(evento)
