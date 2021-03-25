package java8Features;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> firstName = Optional.of( "Tom" );
        // 判断是否!=null
        System.out.println( "First Name is set? " + firstName.isPresent() );
        // ==null 时返回lambda的值
        System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) );
        // ==null时返回值
        System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
        System.out.println();
    }
}
