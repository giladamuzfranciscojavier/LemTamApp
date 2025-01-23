Pequeña aplicación desarrollada como favor personal para gestionar el vestuario de una asociación folklórica mediante una base de datos MySQL y el ORM Hibernate.

Al tratarse de una aplicación para uso privado con una base de datos local en un equipo que no está conectado a Internet, utiliza credenciales genéricas para simplificar su desarrollo y posterior uso.

La aplicación permite realizarlas operaciones CRUD básicas (siglas en inglés de Creación, Lectura, Actualización y Borrado de datos) y soporta la creación y lectura de códigos QR.

Si bien cuenta con claros problemas de optimización (realiza consultas a la base de datos aún sin haber realizado cambios en los datos con respecto a consultas anteriores en lugar de almacenarlos en memoria, lo cual provoca lentitud en equipos de pocos recursos, y la eventual desconexión forzosa, requiriendo reinicio de la aplicación), la aplicación es perfectamente funcional y no se ha requerido cambios por parte del usuario final, por lo que no se actualizará
