import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class ModificarDeporteMaria {
    public static void main(String[] args) {
        ODB odb = null;
        // abrir la base de datos
        try {
            odb = ODBFactory.open("neodatis.test");
            // consulta sobre los jugadores cuyo nombre es María
            // da error porque hemos puesto el nombre sin acento
            //IQuery query = new CriteriaQuery(Jugador.class, Where.equal("nombre", "María"));

            // consulta sobre los jugadores cuyo nombre es Maria
            IQuery query = new CriteriaQuery(Jugador.class, Where.equal("ciudad", "Madrid"));
            // recuperar todos los jugadores de la consulta
            Objects<Jugador> jugadores = odb.getObjects(query);

            for (Jugador j : jugadores) {
                j.setCiudad("Jaca");
                odb.store(j);
            }
            // obtener solamente el primer jugador encontrado en la consulta
            Jugador jugador = jugadores.getFirst();
            int i = 1;
            Jugador j = new Jugador();
            while (jugadores.hasNext()) {
                Jugador jug = jugadores.next();
                System.out.println(i + ") " +
                        jug.getNombre() + " *** " + jug.getDeporte() + " *** " +
                        jug.getCiudad() + " *** " + jug.getEdad());
                i++;
            }
            // actualizar el jugador
            // jugador.setDeporte("voley-playa");
            //odb.store(jugador);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("No hay ningun jugador con ese nombre");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // validar los cambios y cerrar la base de datos
            if (odb != null) {
                odb.close();
            }
        }
    }//main
}


