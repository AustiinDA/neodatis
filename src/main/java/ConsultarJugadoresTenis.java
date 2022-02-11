// CONSULTAS CON CRITERIOS EN NEODATIS

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


public class ConsultarJugadoresTenis {

    public static void main(String[] args) {
        // abrir la base de datos
        ODB odb = ODBFactory.open("neodatis.test");
        // consulta sobre los jugadores que practican el deporte tenis
        ICriterion criterion = new And().add(Where.equal("ciudad", "Jaca")).add(Where.like("nombre", "M%"));
        IQuery query = new CriteriaQuery(Jugador.class, criterion);
        // ordenar ascendentemente el resultado de la consulta por nombre y edad
        query.orderByAsc("nombre,edad");
        // recuperar todos los jugadores de la consulta
        Objects<Jugador> jugadores = odb.getObjects(query);
        System.out.println(jugadores.size() + " jugadores:");
        // visualizar los jugadores recuperados
        int i = 1;
        while (jugadores.hasNext()) {
            Jugador j = jugadores.next();
            System.out.println(i + ") " +
                    j.getNombre() + " *** " + j.getDeporte() + " *** " +
                    j.getCiudad() + " *** " + j.getEdad());
            i++;
        }
        // cerrar la base de datos
        odb.close();
    }//main
}
