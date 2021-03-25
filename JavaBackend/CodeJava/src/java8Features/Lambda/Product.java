package java8Features.Lambda;

public interface Product {
    default String desc(){
        return "T公司产品";
    }
}