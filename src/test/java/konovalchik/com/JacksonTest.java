package konovalchik.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import konovalchik.com.model.UserInfo;
import org.junit.jupiter.api.Test;


import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JacksonTest {
        @Test
        void jacksonTest() throws Exception {
            ObjectMapper mapper = new ObjectMapper ();
            File file = new File("src/test/resources/jsonFile.json");
            UserInfo.User user = mapper.readValue(file, UserInfo.User.class);

            assertThat(user.name).isEqualTo("Jack");
            assertThat(user.phone).isEqualTo("1733472086");
            assertThat(user.pets.get(0).type).isEqualTo("cat");
            assertThat(user.pets.get(0).names.get(0)).isEqualTo("Felix");
            assertThat(user.pets.get(0).names.get(1)).isEqualTo("Oscar");
            assertThat(user.pets.get(0).names.get(2)).isEqualTo("Smudge");
            assertThat(user.pets.get(1).type).isEqualTo("dog");
            assertThat(user.pets.get(1).names.get(0)).isEqualTo("Bella");
            assertThat(user.pets.get(1).names.get(1)).isEqualTo("Max");
        }
}
