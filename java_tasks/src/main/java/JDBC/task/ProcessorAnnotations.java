package JDBC.task;

import JDBC.task.DAO.WorkDB;
import JDBC.task.myAnnotations.Column;
import JDBC.task.myAnnotations.Table;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcessorAnnotations {

    public void createTable(Class cl) throws SQLException {
        System.out.println("Запуск ProcessorAnnotations");
        if (!cl.isAnnotationPresent(Table.class)) {
            // TODO сделать свои эксепшены
            throw new RuntimeException("класс не помечен для создания таблицы");
        }

        //Получаем название таблицы
        StringBuilder nameDB = new StringBuilder(((Table)cl.getAnnotation(Table.class)).title());
        //Получаем название полей для таблицы
        Field[] fields = cl.getDeclaredFields();
        // Выбираем из полей помеченные нужно аннотацией
        List<Field> fieldsIsAnno = new ArrayList<>();
        for (Field f: fields) {
            if (f.isAnnotationPresent(Column.class)) {
                fieldsIsAnno.add(f);
            }
        }

        // Строим запрос
        RequestBuilder requestBuilder = new RequestBuilder(nameDB, fieldsIsAnno);
        //Посылаем запрос в БД
        WorkDB.exe(requestBuilder.createRequest("CREATE TABLE "));
    }
}
