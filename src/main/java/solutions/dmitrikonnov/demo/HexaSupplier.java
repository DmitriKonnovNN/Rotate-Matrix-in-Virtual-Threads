package solutions.dmitrikonnov.demo;

/**
 * HexaSupplier is supposed to be used with Generics on the equal terms as HexaInitializer
 * */

@FunctionalInterface
public interface HexaSupplier<Q,W,E,R,T,Y,U> {
    U get(Q q, W w, E e, R r, T t, Y y);
}
