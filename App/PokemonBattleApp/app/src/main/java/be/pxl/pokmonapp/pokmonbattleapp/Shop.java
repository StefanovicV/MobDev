package be.pxl.pokmonapp.pokmonbattleapp;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ExpandableListView;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

public class Shop extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        listView = (ExpandableListView)findViewById(R.id.Shop);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Items");
        listDataHeader.add("Pokémons");
        listDataHeader.add("Attacks");

        List<String> items = new ArrayList<>();
        items.add("Potion");
        items.add("Super Potion");

        List<String> pokémons = new ArrayList<>();
        pokémons.add("Pikhachu");
        pokémons.add("Ibracadabra");
        pokémons.add("Machop");

        List<String> attacks = new ArrayList<>();
        attacks.add("Body Slam");
        attacks.add("Head Slam");
        attacks.add("Butt Slam");

        listHash.put(listDataHeader.get(0), items);
        listHash.put(listDataHeader.get(1), pokémons);
        listHash.put(listDataHeader.get(2), attacks);
    }
}

