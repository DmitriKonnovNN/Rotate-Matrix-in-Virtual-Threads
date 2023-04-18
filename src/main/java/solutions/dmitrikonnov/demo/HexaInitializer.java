package solutions.dmitrikonnov.demo;
/**
 *
 * I could have called it HeptaSupplier, which effectively it is, but I didn't.
 * */
@FunctionalInterface
public interface HexaInitializer<Q,W,E,R,T,Y,U> {
    U getInstance(Q q, W w, E e, R r, T t, Y y);
}
