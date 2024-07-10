import java.util.HashSet;
import java.util.Set;

public class DependencyTest {
    private static final String value = "exemploDeStringParaTestarDesempenho";

    public static void main(String[] args) {
        // Aquecer a JVM
        for (int i = 0; i < 1000; i++) {
            getIntDependencies(value);
            getStringDependencies(value);
        }

        // Testar desempenho
        long startTime, endTime, totalIntTime = 0, totalStringTime = 0;
        int iterations = 100000;

        for (int i = 0; i < iterations; i++) {
            startTime = System.nanoTime();
            getIntDependencies(value);
            endTime = System.nanoTime();
            totalIntTime += (endTime - startTime);

            startTime = System.nanoTime();
            getStringDependencies(value);
            endTime = System.nanoTime();
            totalStringTime += (endTime - startTime);
        }

        System.out.println("Tempo médio com Set<Integer>: " + (totalIntTime / iterations) + " ns");
        System.out.println("Tempo médio com Set<String>: " + (totalStringTime / iterations) + " ns");
    }

    public static Set<Integer> getIntDependencies(String value) {
        Set<Integer> dependencies = new HashSet<>();
        for (char ch : value.toCharArray()) {
            dependencies.add((int) ch);
        }
        return dependencies;
    }

    public static Set<String> getStringDependencies(String value) {
        Set<String> dependencies = new HashSet<>();
        for (char ch : value.toCharArray()) {
            dependencies.add(String.valueOf(ch));
        }
        return dependencies;
    }
}
