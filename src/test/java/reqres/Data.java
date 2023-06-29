package reqres;

import com.google.gson.annotations.SerializedName;

@lombok.Data
public class Data {
    private int id;
    private String name;
    private int year;
    private String color;
    @SerializedName("pantone_value")
    private String pantoneValue;
}
