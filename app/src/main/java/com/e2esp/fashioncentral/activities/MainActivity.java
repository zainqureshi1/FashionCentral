package com.e2esp.fashioncentral.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.model.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.rest.HttpServerErrorResponse;
import com.afrozaar.wp_api_v2_client_android.rest.WordPressRestResponse;
import com.e2esp.fashioncentral.R;
import com.e2esp.fashioncentral.adapters.EndlessScrollListener;
import com.e2esp.fashioncentral.adapters.PostRecyclerAdapter;
import com.e2esp.fashioncentral.adapters.VerticalSpacingItemDecoration;
import com.e2esp.fashioncentral.interfaces.OnPostClickListener;
import com.e2esp.fashioncentral.utils.Statics;
import com.e2esp.fashioncentral.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = "MainActivity";

    private @BindView(R.id.toolbar) Toolbar toolbar;
    private @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    private @BindView(R.id.nav_view) NavigationView navigationView;

    @BindView(R.id.recyclerViewPosts) RecyclerView recyclerViewPosts;
    private ArrayList<Post> posts;
    private PostRecyclerAdapter postRecyclerAdapter;

    @BindDimen(R.dimen.margin_small) int marginSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, SplashActivity.class));

        setContentView(R.layout.activity_main);
        setupView();

        loadPosts(1);
    }

    private void setupView() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        posts = new ArrayList<>();
        postRecyclerAdapter = new PostRecyclerAdapter(this, posts, new OnPostClickListener() {
            @Override
            public void onPostClick(Post post) {
                clickedPost(post);
            }
        });
        LinearLayoutManager postLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        VerticalSpacingItemDecoration postItemDecoration = new VerticalSpacingItemDecoration(marginSmall);
        EndlessScrollListener postScrollListener = new EndlessScrollListener(postLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPosts(page);
            }
        };
        recyclerViewPosts.setAdapter(postRecyclerAdapter);
        recyclerViewPosts.setLayoutManager(postLayoutManager);
        recyclerViewPosts.addItemDecoration(postItemDecoration);
        recyclerViewPosts.addOnScrollListener(postScrollListener);
    }

    private void loadCategories() {
        Statics.getWpClient().getCategories(new WordPressRestResponse<List<Taxonomy>>() {
            @Override
            public void onSuccess(List<Taxonomy> result) {
                Log.i("TAG", "getCategories result: "+result);
            }
            @Override
            public void onFailure(HttpServerErrorResponse errorResponse) {
                Log.i("TAG", "getCategories errorResponse: "+errorResponse);
            }
        });
    }

    private void loadPosts(int page) {
        Utils.doRetrofitCall(Statics.getWpClient().getPostsForPage(page), new WordPressRestResponse<List<Post>>() {
            @Override
            public void onSuccess(List<Post> result) {
                Log.i(TAG, "getPosts result: "+result);
                if (result != null && result.size() > 0) {
                    posts.addAll(result);
                    postRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(HttpServerErrorResponse errorResponse) {
                Log.i(TAG, "getPosts errorResponse: "+errorResponse);
            }
        });
    }

    private void clickedPost(Post post) {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean backPressed = false;
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        if (backPressed) {
            super.onBackPressed();
            return;
        }

        backPressed = true;
        Toast.makeText(this, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressed = false;
            }
        }, 2000);
    }

}
