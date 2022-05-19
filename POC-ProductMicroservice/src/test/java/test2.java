import org.junit.jupiter.api.Test;

import java.util.List;

public class test2 {

    @Test
    void testProduct(){
        var products = List.of(
                "product1",
                "product2",
                "product2"
        );
        Long result = products.stream().filter(p -> p.equals("product2")).count();
        System.out.println(result);
    }
}
