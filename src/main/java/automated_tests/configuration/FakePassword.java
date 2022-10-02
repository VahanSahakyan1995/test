package automated_tests.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class FakePassword {

    @Value("${fake.password.short1}")
    private String short1;

    @Value("${fake.password.short2}")
    private String short2;

}
