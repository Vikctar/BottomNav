package com.vikcandroid.bottomnav.fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vikcandroid.bottomnav.R;
import com.vikcandroid.bottomnav.Utils.GridSpacingItemDecorator;
import com.vikcandroid.bottomnav.adapter.StoreAdapter;
import com.vikcandroid.bottomnav.app.BottomNavApp;
import com.vikcandroid.bottomnav.model.Movie;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {

    private static final String TAG = StoreFragment.class.getSimpleName();

    // url to fetch shopping items
    // TODO: Add my own api later, can't depend on Ravi's too much
//    private static final String URL = "https://vikctar.com/store.json";
    private static final String URL = "https://api.androidhive.info/json/movies_2017.json";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<Movie> movies;
    private StoreAdapter storeAdapter;


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.bind(this, rootView);

        movies = new ArrayList<>();
        storeAdapter = new StoreAdapter(getContext(), movies);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecorator(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(storeAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fetchStoreItems();

        return rootView;
    }

    public static StoreFragment newInstance(String p, String p2) {
        StoreFragment storeFragment = new StoreFragment();
        Bundle args = new Bundle();
        storeFragment.setArguments(args);
        return storeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * Fetch shopping items
     */
    private void fetchStoreItems() {
        final JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                if (response == null) {
                    Toast.makeText(getContext(), "could not fetch items", Toast.LENGTH_LONG).show();
                    return;
                }

                List<Movie> movieList = new Gson().fromJson(response.toString(), new TypeToken<List<Movie>>() {}.getType());

                movies.clear();
                movies.addAll(movieList);

                storeAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavApp.getInstance().addToRequestQueue(request);
    }

}
