package automated_tests.automated_tests_list.base;

import org.testng.annotations.Test;

public class OpenGoogle extends Base{

    @Test(priority =1)
    public void openGoogle(){
        System.out.println("Its working");
    }
}
