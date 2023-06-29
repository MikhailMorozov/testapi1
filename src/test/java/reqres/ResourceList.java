package reqres;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@lombok.Data
public class ResourceList {
    private int page;
    @SerializedName("per_page")
    private int perPage;
    private int total;
    @SerializedName("total_pages")
    private int totalPages;
    private ArrayList<Data> dataArrayList;
    private Support support;
}
