package java8Features.Lambda;

public class Client {
    public static void main(String[] args) {
        Product productA = DefaultFactory.create(ProductA::new);
        System.out.println(productA.desc());

        Product productB = DefaultFactory.create(ProductB::new);
        System.out.println(productB.desc());
    }
}
