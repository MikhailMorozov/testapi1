package reqres;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class User {
    private String name;
    private String job;
    private int id;
    private String createdAt;
}
