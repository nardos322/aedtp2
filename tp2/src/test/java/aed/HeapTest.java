package aed;
import aed.comparadores.*;
import aed.testObjects.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;

public class HeapTest {
    @Test
    public void testRemoveFromEmptyHeap() {
        Heap<Integer> heap = new Heap<>();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            heap.desencolar();
        });
        assertEquals("El heap esta vacio", exception.getMessage());
    }

    @Test
    public void testInsertElementNull(){
        Heap<Integer> heap = new Heap<>();
        assertThrows(NullPointerException.class, () -> {
            heap.encolar(null);
        });
    }
    @Test
    public void testHeapifyUp() {
        Heap<Integer> heap = new Heap<>();
        heap.encolar(10);
        heap.encolar(20);
        heap.encolar(5);
        heap.encolar(30);

        assertEquals(30, heap.desencolar());
        assertEquals(20, heap.desencolar());
        assertEquals(10, heap.desencolar());
        assertEquals(5, heap.desencolar());

    }

    @Test
    public void testHeapifyDown() {
        Heap<Integer> heap = new Heap<>();
        heap.encolar(30);
        heap.encolar(20);
        heap.encolar(10);
        heap.encolar(5);

        assertEquals(30, heap.desencolar());
        assertEquals(20, heap.desencolar());
        assertEquals(10, heap.desencolar());
        assertEquals(5, heap.desencolar());
    }

    @Test
    public void testInsertAndExtractIntegers() {
        Heap<Integer> heap = new Heap<>();
        heap.encolar(10);
        heap.encolar(20);
        heap.encolar(5);

        assertEquals(20, heap.desencolar());
        assertEquals(10, heap.desencolar());
        assertEquals(5, heap.desencolar());
    }

    @Test
    public void testHeapWithNegativeNumbers() {
        Heap<Integer> heap = new Heap<>();
        heap.encolar(-10);
        heap.encolar(-20);
        heap.encolar(-5);
        heap.encolar(-30);

        assertEquals(-5, heap.desencolar());
        assertEquals(-10, heap.desencolar());
        assertEquals(-20, heap.desencolar());
        assertEquals(-30, heap.desencolar());
    }

    @Test
    public void testHeapWithMixedNumbers() {
        Heap<Integer> heap = new Heap<>();
        heap.encolar(10);
        heap.encolar(-20);
        heap.encolar(5);
        heap.encolar(-30);

        assertEquals(10, heap.desencolar());
        assertEquals(5, heap.desencolar());
        assertEquals(-20, heap.desencolar());
        assertEquals(-30, heap.desencolar());
    }

    @Test
    public void testHeapWithDuplicateNumbers() {
        Heap<Integer> heap = new Heap<>();
        heap.encolar(10);
        heap.encolar(10);
        heap.encolar(5);
        heap.encolar(5);

        assertEquals(10, heap.desencolar());
        assertEquals(10, heap.desencolar());
        assertEquals(5, heap.desencolar());
        assertEquals(5, heap.desencolar());
    }

    @Test
    public void testHeapSize() {
        Heap<Integer> heap = new Heap<>();
        heap.encolar(10);
        heap.encolar(20);
        heap.encolar(5);

        assertEquals(3, heap.tamaño());
        heap.desencolar();
        assertEquals(2, heap.tamaño());
        heap.desencolar();
        assertEquals(1, heap.tamaño());
        heap.desencolar();
        assertEquals(0, heap.tamaño());
    }

    @Test
    public void testMultipleInsertionsAndExtractions() {
        Heap<Integer> heap = new Heap<>();
        heap.encolar(10);
        heap.encolar(20);
        heap.encolar(5);
        assertEquals(20, heap.desencolar());
        heap.encolar(15);
        assertEquals(15, heap.desencolar());
        assertEquals(10, heap.desencolar());
        assertEquals(5, heap.desencolar());
    }

    @Test
    public void testInsertAndExtractStrings() {
        Heap<String> heap = new Heap<>();
        heap.encolar("apple");
        heap.encolar("banana");
        heap.encolar("cherry");

        assertEquals("cherry", heap.desencolar());
        assertEquals("banana", heap.desencolar());
        assertEquals("apple", heap.desencolar());
    }

    @Test
    public void testHeapWithCustomComparator() {
        Comparator<Integer> comparator = (a, b) -> b - a; // Min-heap
        Heap<Integer> heap = new Heap<>(comparator);
        heap.encolar(10);
        heap.encolar(20);
        heap.encolar(5);

        assertEquals(5, heap.desencolar());
        assertEquals(10, heap.desencolar());
        assertEquals(20, heap.desencolar());
    }

    @Test
    public void testHeapWithArrayConstructor() {
        Integer[] array = {10, 20, 5};
        Heap<Integer> heap = new Heap<>(array);

        assertEquals(20, heap.desencolar());
        assertEquals(10, heap.desencolar());
        assertEquals(5, heap.desencolar());
    }

    @Test
    public void testHeapWithArrayAndComparatorConstructor() {
        Integer[] array = {10, 20, 5};
        Comparator<Integer> comparator = (a, b) -> b - a; // Min-heap
        Heap<Integer> heap = new Heap<>(array, comparator);

        assertEquals(5, heap.desencolar());
        assertEquals(10, heap.desencolar());
        assertEquals(20, heap.desencolar());
    }
    @Test
    public void testHeapConstructorWithArrayAndComparator() {
        Integer[] elementos = {4, 1, 7, 3};
        Comparator<Integer> comparador = Integer::compareTo;
        Heap<Integer> heap = new Heap<>(elementos, comparador);

        assertEquals(7, heap.desencolar(), "El elemento de mayor prioridad debe ser 7");
        assertEquals(4, heap.desencolar(), "El siguiente elemento debe ser 4");
        assertEquals(3, heap.desencolar(), "El siguiente elemento debe ser 3");
        assertEquals(1, heap.desencolar(), "El siguiente elemento debe ser 1");
    }

    @Test
    public void testHeapProperty(){
        Heap<Integer> heap = new Heap<>();
        for(int i = 1; i < 100; i++){
            heap.encolar(i);
        }
        int anterior = heap.desencolar();
        for(int i = 1; i < heap.tamaño(); i++){
            int actual = heap.desencolar();
            assertTrue(anterior >= actual);
            anterior = actual;
        }
    }

    @Test
    public void testLargeNumberOfElements() {
        Heap<Integer> heap = new Heap<>();
        for (int i = 0; i < 1000; i++) {
            heap.encolar(i);
        }
        for (int i = 999; i >= 0; i--) {
            assertEquals(i, heap.desencolar());
        }
    }

    @Test
    public void testPriorityPrimeMin(){
        PrimoComparatorMin prioridadPrimoMin = new PrimoComparatorMin();
        Heap<Integer> heap = new Heap<>(prioridadPrimoMin);
        heap.encolar(4);
        heap.encolar(5);
        heap.encolar(8);
        heap.encolar(11);
        heap.encolar(15);

        assertEquals(5, heap.desencolar());
        assertEquals(11, heap.desencolar());
        assertEquals(15,heap.desencolar());
        assertEquals(8,heap.desencolar());
        assertEquals(4,heap.desencolar());
    }

    @Test
    public void testPriorityPrimeMax(){
        PrimoComparatorMax prioridadPrimoMax = new PrimoComparatorMax();
        Heap<Integer> heap = new Heap<>(prioridadPrimoMax);
        heap.encolar(6);
        heap.encolar(20);
        heap.encolar(8);
        heap.encolar(11);
        heap.encolar(3);

        assertEquals(11, heap.desencolar());
        assertEquals(3, heap.desencolar());
        assertEquals(20, heap.desencolar());
        assertEquals(8, heap.desencolar());
        assertEquals(6, heap.desencolar());
    }

    @Test
    public void testCustomObjects(){
        Comparator<Persona> comparadorPorEdad = Comparator.comparingInt(Persona::getEdad);
        Heap<Persona> heap = new Heap<>(comparadorPorEdad);
        heap.encolar(new Persona("Maria", 18));
        heap.encolar(new Persona("Sol", 23));
        heap.encolar(new Persona("Nahuel", 35));

        assertEquals("Nahuel", heap.desencolar().getNombre());
        assertEquals("Sol", heap.desencolar().getNombre());
        assertEquals("Maria", heap.desencolar().getNombre());
    }

    @Test
    public void testHeapWithProductosByPrecio() {
        Comparator<Producto> comparadorPorPrecio = Comparator.comparingDouble(Producto::getPrecio);
        Heap<Producto> heap = new Heap<>(comparadorPorPrecio);

        heap.encolar(new Producto("Producto A", 10.99));
        heap.encolar(new Producto("Producto B", 5.49));
        heap.encolar(new Producto("Producto C", 20.00));

        assertEquals("Producto C", heap.desencolar().getNombre(), "El producto más caro debe ser 'Producto C'");
        assertEquals("Producto A", heap.desencolar().getNombre(), "El siguiente producto debe ser 'Producto A'");
        assertEquals("Producto B", heap.desencolar().getNombre(), "El siguiente producto debe ser 'Producto B'");
    }

    @Test
    public void testHeapWithComparableObjects() {
        Heap<Tarjeta> heap = new Heap<>();

        heap.encolar(new Tarjeta("1111", 2));
        heap.encolar(new Tarjeta("2222", 5));
        heap.encolar(new Tarjeta("3333", 1));

        assertEquals("2222", heap.desencolar().getNumero(), "La tarjeta con mayor prioridad debe ser '2222'");
        assertEquals("1111", heap.desencolar().getNumero(), "El siguiente tarjeta debe ser '1111'");
        assertEquals("3333", heap.desencolar().getNumero(), "El siguiente tarjeta debe ser '3333'");
    }

    @Test
    public void testHeapEmpleadosComparator() {
        Comparator<Empleado> comparador = new EmpleadoComparator();
        Heap<Empleado> heap = new Heap<>(comparador);
        heap.encolar(new Empleado("Caro", "Ventas", 20));
        heap.encolar(new Empleado("Tute", "Ventas", 21));
        heap.encolar(new Empleado("Delfina", "Recursos Humanos", 21));
        heap.encolar(new Empleado("Nahuel", "Piloto de Formula 1", 28));

        // el testeo del departamento es lexicografica  no confundir con longitud,  "ventas" > "piloto de formula1"
        // si los empleados estan en el mismo depto se comparan por edad, la edad mayor tiene prioridad
        assertEquals("Tute", heap.desencolar().getNombre());
        assertEquals("Caro", heap.desencolar().getNombre());
        assertEquals("Delfina", heap.desencolar().getNombre());
        assertEquals("Nahuel", heap.desencolar().getNombre());

    }

    @Test
    public void testCambiarPrioridadMutables(){
        ContadorComparator contadorComparador = new ContadorComparator();
        Heap<Contador> heap = new Heap<>(contadorComparador);

        Contador contador = new Contador(10);
        Contador contador2 = new Contador(12);

        HeapHandle<Contador> handle1 = heap.encolar(contador);
        HeapHandle<Contador> handle2 = heap.encolar(contador2);

        contador.incrementar();
        contador.incrementar();
        contador.incrementar();
        heap.cambiarPrioridad(handle1);
        assertEquals(handle1.getElement(), heap.root());

        contador.decrementar();
        contador.decrementar();
        heap.cambiarPrioridad(handle1);
        assertEquals(handle2.getElement(),heap.root());
    }


    @Test
    void testHeapStress() {
        int numberOfElements = 1000000; // Un millón de elementos
        Heap<Integer> heap = new Heap<>();


        int seed = 1; // Semilla inicial
        int a = 1664525;
        int c = 1013904223;
        int m = Integer.MAX_VALUE;


        int[] data = new int[numberOfElements];

        // Generar y almacenar los datos
        for (int i = 0; i < numberOfElements; i++) {
            seed = (a * seed + c) % m;
            data[i] = seed;
        }

        // Medir el tiempo de inserción
        long startTime = System.currentTimeMillis();

        // Insertar elementos en el heap
        for (int value : data) {
            heap.encolar(value);
        }

        long insertEndTime = System.currentTimeMillis();

        // Verificar que el heap no está vacío
        assertFalse(heap.vacia(), "El heap debería contener elementos después de las inserciones.");

        // Medir el tiempo de extracción
        long extractStartTime = System.currentTimeMillis();

        // Extraer elementos del heap y verificar el orden
        int anterior = heap.desencolar();
        for (int i = 1; i < numberOfElements; i++) {
            int actual = heap.desencolar();
            // Verificar que el heap mantiene el orden correcto (para un max-heap)
            assertTrue(actual <= anterior, "El heap no mantiene el orden correcto.");
            anterior = actual;
        }

        long extractEndTime = System.currentTimeMillis();
        // Verificar que el heap está vacío después de las extracciones
        assertTrue(heap.vacia(), "El heap debería estar vacío después de las extracciones.");


        System.out.println("Tiempo de inserción: " + (insertEndTime - startTime) + " ms");
        System.out.println("Tiempo de extracción: " + (extractEndTime - extractStartTime) + " ms");
    }

}