package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class JPH_baseUrl {

    protected RequestSpecification specJPH;


    @BeforeTest
    public void setup(){
        specJPH = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
    /*
        Burada, adresi saklamış olduk. Burada oluşturulur. Diğer class'larda
        extends edilir.
     */

}
