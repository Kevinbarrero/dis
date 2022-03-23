package ru.itis.s2_lab4.orm;


import java.util.Set;

public class EntityManagerImpl implements EntityManager {

    private Set<Object> objects;


    @Override
    public void persist(Object var1) throws Exception {
        /*
            var1 -> fields
            var1.class -> table name -> SQL:
             insert into tableName (field1, ...) values (value1,...);
            objects.put(var1);
         */
    }

    @Override
    public <T> T merge(T var1) {

        /*
            if (!objects.contains(var1)) new throws ...
            var1 -> fields
            var1.class -> table name -> SQL:
             update tableName set field1=value1,...;
         */

        return var1;
    }

    @Override
    public void remove(Object var1) {
        /*
            if (!objects.contains(var1)) new throws ...
            var1.class -> table name -> SQL: delete from tableName where pkName = var1.getId()
            objects.remove(var1);
         */
    }

    @Override
    public <T> T find(Class<T> var1, Object var2) {
        /*
            var2 - id (primary key value)
            var1 - type of Object
            var1 -> pk name (default 'id')
            var1 -> table name -> SQL: select * from tableName where pkName = var2
            T result = var1.newInstance()
            resultSet -> <fielName, value> -> class fields set value
            objects.put(var1);
            return result;
         */

        return null;
    }
}