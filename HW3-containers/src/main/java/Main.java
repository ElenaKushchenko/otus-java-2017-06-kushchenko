import java.util.Collections;

/**
 * Created by Elena on 18.06.2017.
 */
public class Main {

    public static void main(String... args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        Collections.addAll(list, 1);
        Collections.copy(new MyArrayList<>(), list);
        Collections.sort(list);
    }
}
