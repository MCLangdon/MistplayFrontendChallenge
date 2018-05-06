package com.mc.mistplayfrontendchallenge;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    private static ArrayList<Game> gamesList = new ArrayList<Game>(); // CHANGE TO NONSTATIC AFTER TESTING
    private RecyclerView recyclerView;
    private static SearchResultsAdapter adapter = new SearchResultsAdapter(gamesList);  // CHANGE TO NONSTATIC AFTER TESTING

    public static MainActivity getInstance() {
        if (instance == null){
            instance = new MainActivity();
        }
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create adapter for RecycleView
        recyclerView = (RecyclerView) findViewById(R.id.search_scroll);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState){}

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                // Load more results if the end of the current results has been reached
                if ( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    CommunicationManager.getInstance().loadMoreResults();
                }
            }
        });

        // Add listener to EditText input
        final EditText editTextSearch = (EditText) findViewById(R.id.search_edit_text);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Update search results based on new input
                EditText simpleEditText = (EditText) findViewById(R.id.search_edit_text);
                String strValue = simpleEditText.getText().toString();
                CommunicationManager.getInstance().searchString(strValue);
            }
        });

    }

    public void addGame(Game g){
        gamesList.add(g);
        adapter.notifyDataSetChanged();
    }

    public void clearGames(){
        gamesList.clear();
        adapter.notifyDataSetChanged();
    }


    // REMOVE AFTER TESTING
    public static void generateInitialGameResults(){
        Game game1 = new Game();
        game1.setTitle("The Witcher 3");
        game1.setRating(5.0);
        game1.setSubGenre("Strategy");
        Game game2 = new Game();
        game2.setTitle("Hearthstone");
        game2.setRating(5.0);
        game2.setSubGenre("Strategy");
        Game game3 = new Game();
        game3.setTitle("Skyrim 3");
        game3.setSubGenre("Strategy");
        game3.setRating(5.0);

        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        adapter.notifyDataSetChanged();
    }
}
