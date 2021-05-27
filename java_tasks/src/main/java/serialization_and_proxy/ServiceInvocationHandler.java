package serialization_and_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class ServiceInvocationHandler implements InvocationHandler {
    private final ServiceImpl service;
    // Кэш, ключ: пара list - пользователь и аргумент, значение: результат вычислений
    private final Map<Object, Object> cacheForService = new HashMap<>();
    // Объект, что бы сохранить
    private final SerializationForService serializationForService = new SerializationForService();

    public ServiceInvocationHandler (ServiceImpl service) {
        this.service = service;
    }

    @Override
    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
        // Если метод не помечен cache, то ничего не делаем
        if (!method.isAnnotationPresent(CacheAnnotation.class)) {
            return method.invoke(service, args);
        }

        int sizeCache = cacheForService.size();
        // Если в кэшэ нет ничего, то сохраняем пару и значение
        if (!cacheForService.containsKey(getKey(args, method.getAnnotation(CacheAnnotation.class)))) {
            System.out.println("Такой пары нет");
            cacheForService.put(getKey
                    (args, method.getAnnotation(CacheAnnotation.class)), method.invoke(service, args));
            save(method.getAnnotation(CacheAnnotation.class), cacheForService, sizeCache);
        } else {
            System.out.println("Результат из кэша");
        }
        return read(method.getAnnotation(CacheAnnotation.class)).get
                (getKey(args, method.getAnnotation(CacheAnnotation.class)));
    }

    private Object getKey (Object[] args, CacheAnnotation annotation) {
        String whatArguments = annotation.arguments().toString();
        if (whatArguments.equals("BOTH_ARGS")) {
            List<Object> key = new ArrayList<>(Arrays.asList(args));
            return key;
        }
        if (whatArguments.equals("FIRST_ARG")) {
            List<Object> key = new ArrayList<>();
            key.add(args[0]);
            key.add(0);
            return key;
        }
        if (whatArguments.equals("SECOND_ARG")) {
            List<Object> key = new ArrayList<>();
            key.add(0);
            key.add(args[1]);
            return key;
        }
        return null;
    }

    void save (CacheAnnotation annotation, Object cacheForService, int sizeCache) {
        String howToSave = annotation.save().toString();
        int limits = annotation.limits();


        if ((limits == -1) || (limits > sizeCache)) {
            if (howToSave.equals("SAVE_TO_DISK")) {
                serializationForService.save(cacheForService);
            }
            if (howToSave.equals("SAVE_TO_JVM")) {
                System.out.println("Сохраняем на ЖВМ");
            }
        } else {
            if (limits < sizeCache) {
                System.out.println("У нас закончилось место в кэшэ.");
            }
        }
    }

    HashMap<Object, Object> read (CacheAnnotation annotation) {
        String howToSave = annotation.save().toString();
        if (howToSave.equals("SAVE_TO_DISK")) {
            return serializationForService.read();
        } else {
            System.out.println("читаем с ЖВМ");
            return serializationForService.read();
        }
    }
}
