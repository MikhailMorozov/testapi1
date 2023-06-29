package reqres;

import lombok.Builder;

@lombok.Data
@Builder
public class Resource {
    private Data data;
    private Support support;
}
