package serialization_and_proxy;

import java.io.*;
import java.util.HashMap;

public class SerializationForService {
    void save(Object objectForSave) {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream("cacheForService.bin")))
        {
            outputStream.writeObject(objectForSave);
            System.out.println("Serialization DONE");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    HashMap<Object, Object> read() {
        HashMap<Object, Object> objectForRead;

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream("cacheForService.bin")))
        {
            System.out.println("Пытаемся десериализовать кэш");
            objectForRead = (HashMap) inputStream.readObject();
            System.out.println(objectForRead.toString());
            return objectForRead;
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
