import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;

public class EjemploOid {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("neodatis.test"); // Abrir BD
        OID oid = OIDFactory.buildObjectOID(3); // Obtener objeto con OID 3
        System.out.println("OID: " + oid);
        Jugador jug = (Jugador) odb.getObjectFromId(oid);
        System.out.printf("%s, %s, %s, %d %n",
                jug.getNombre(), jug.getDeporte(), jug.getCiudad(), jug.getEdad());
        odb.close(); // Cerrar BD
    }// main
}