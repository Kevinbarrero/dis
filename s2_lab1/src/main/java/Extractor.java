import java.lang.reflect.Field;
import java.util.List;

public class Extractor {
    public static String PATH_FOR_SCAN = "exercise";

    public static void main(String[] args) {
        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
        if (classList!= null){
            classList.forEach(x -> System.out.println("\t" + x.getSimpleName()));
        }
        for (Class<?> clas : classList){
            Field[] fields = clas.getDeclaredFields();
            for (Field field: fields){
                System.out.println(clas.getSimpleName()+ " зависит от ->"+field.getName()+" type ->"+field.getType());
            }
        }
    }
}
