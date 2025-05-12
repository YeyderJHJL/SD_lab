import threading
import time
import random

class LamportClock:
    def __init__(self):
        self.time = 0
        self.lock = threading.Lock()

    def tick(self):
        with self.lock:
            self.time += 1
            return self.time

    def update(self, received_time):
        with self.lock:
            self.time = max(self.time, received_time) + 1
            return self.time

    def get_time(self):
        with self.lock:
            return self.time

class Restaurante:
    def __init__(self, num_meseros):
        self.clock = LamportClock()
        self.eventos = []
        self.num_meseros = num_meseros

    def registrar_evento(self, mesero_id, accion, tiempo):
        self.eventos.append((tiempo, f"Mesero {mesero_id} {accion} en t={tiempo}"))

    def simular_mesero(self, mesero_id):
        t1 = self.clock.tick()
        self.registrar_evento(mesero_id, "Tomó pedido", t1)

        time.sleep(random.uniform(0.5, 1.5))

        t2 = self.clock.tick()
        self.registrar_evento(mesero_id, "Entregó pedido", t2)

    def ejecutar(self):
        hilos = []

        for i in range(1, self.num_meseros + 1):
            hilo = threading.Thread(target=self.simular_mesero, args=(i,))
            hilos.append(hilo)
            hilo.start()

        for hilo in hilos:
            hilo.join()

        print("\n=== HISTORIAL DE EVENTOS ===")
        for evento in sorted(self.eventos, key=lambda x: x[0]):
            print(evento[1])

        print("\nTiempo lógico final del restaurante:", self.clock.get_time())

if __name__ == "__main__":
    restaurante = Restaurante(num_meseros=5)
    restaurante.ejecutar()