package JDBC.task;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestBuilder {
    Map<Class, String> map = new HashMap<>();
    StringBuilder nameDB;
    List<Field> fields;

    {
        map.put(int.class, "INTEGER");
        map.put(String.class, "TEXT");
        map.put(double.class, "DOUBLE");
    }

    public RequestBuilder(StringBuilder nameDB, List<Field> fields) {
        this.nameDB = nameDB;
        this.fields = fields;
    }

    public String createRequest(String nameRequest) {
        StringBuilder requestCreateDB = new StringBuilder(nameRequest);
        requestCreateDB.append(nameDB);
        requestCreateDB.append(" (");

        for (Field f : fields) {
            requestCreateDB.append(f.getName())
                    .append(" ")
                    .append(map.get(f.getType()))
                    .append(", ");
        }

        requestCreateDB.setLength(requestCreateDB.length() - 2);
        requestCreateDB.append(");");

        return requestCreateDB.toString();
    }

}
