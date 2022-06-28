import java.lang.annotation.Annotation;

@test(count = 1, names = {"박수근","조항주"})
public class annotation {
    public static void main(String[] args) {
        Class<annotation> cls = annotation.class;
        Annotation[] annotations = cls.getAnnotations();
        test annotation = (test)cls.getAnnotation(test.class);
        System.out.println(annotation.count());
        for (Annotation anno : annotations)
            System.out.println(anno);
    }
}
