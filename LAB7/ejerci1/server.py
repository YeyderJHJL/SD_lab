from wsgiref.simple_server import make_server

try:
    from soap_service import wsgi_app
except Exception as e:
    print("Error al importar wsgi_app:", e)
    input("Presiona Enter para salir...")
    exit(1)

import logging

if __name__ == '__main__':
    logging.basicConfig(level=logging.DEBUG)
    logging.getLogger('spyne.protocol.xml').setLevel(logging.DEBUG)

    try:
        server = make_server('127.0.0.1', 8080, wsgi_app)
    except Exception as e:
        print("Error al crear el servidor:", e)
        input("Presiona Enter para salir...")
        exit(1)
    
    print("Iniciando servicio de Ventas Online SOAP...")
    print("URL: http://127.0.0.1:8080/")
    print("WSDL: http://127.0.0.1:8080/?wsdl")
    print("\nServicios disponibles:")
    print("- Gestión de productos (consulta, búsqueda, actualización stock)")
    print("- Gestión de ventas (crear, confirmar, cancelar)")
    print("- Cálculos (total venta, verificar disponibilidad)")
    print("\nPresiona Ctrl+C para detener el servicio...")
    
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\nServicio detenido.")
    except Exception as e:
        print("Error durante la ejecución del servidor:", e)
        input("Presiona Enter para salir...")
