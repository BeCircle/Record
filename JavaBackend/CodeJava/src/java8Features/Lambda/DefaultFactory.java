package java8Features.Lambda;

import java.util.function.Supplier;

public interface DefaultFactory {
    static Product create(Supplier<Product> supplier){
        return supplier.get();
    }
}
