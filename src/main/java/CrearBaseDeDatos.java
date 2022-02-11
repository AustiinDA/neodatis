import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class CrearBaseDeDatos {
    public static void main(String[] args) {

        Jugador j1 = new Jugador("Maria", "voleibol", "Madrid", 4);
        Jugador j2 = new Jugador("Miguel", "tenis", "Madrid", 15);
        Jugador j3 = new Jugador("Mario", "baloncesto", "Guadalajara", 15);
        Jugador j4 = new Jugador("Alicia", "tenis", "Madrid", 14);

        // Abrir BD
        ODB odb = ODBFactory.open("neodatis.test");

        // Almacenamos los jugadores en la base de datos
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        //recuperamos todos los jugadores
        // OJO. Objects no es la clase Object
        // Objects implementa la inteface Collection
        Objects<Jugador> objects = odb.getObjects(Jugador.class);
        System.out.printf("%d Jugadores: %n", objects.size());

        int i = 1;
        // visualizar los objetos
        while (objects.hasNext()) {
            Jugador jug = objects.next();
            System.out.printf("%d: %s, %s, %s %n",
                    i++, jug.getNombre(), jug.getDeporte(),
                    jug.getCiudad(), jug.getEdad());
        }
        odb.close(); // Cerrar BD
    }// main

}

