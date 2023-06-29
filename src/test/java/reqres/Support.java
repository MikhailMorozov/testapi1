package reqres;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Support {

    private String url;
    private String text;
}
