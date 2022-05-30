package com.huawei.healthprognosis;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.huawei.healthprognosis.fragment.DashBoardFragment;
import com.huawei.healthprognosis.fragment.AccountFragment;
import com.huawei.healthprognosis.fragment.MapFragment;
import com.huawei.healthprognosis.fragment.SearchFragment;



import java.util.Arrays;
public class MenuActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_ACCOUNT = 2;
    private static final int POS_SEARCH = 3;
    private static final int POS_MAP = 4;
    private static final int POS_LOGOUT = 6;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();


        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_SEARCH),
                createItemFor(POS_MAP),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);

    }
    private DrawerItem createItemFor(int position) {
        System.out.println("arunfuck"+position);
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorPrimaryDark))
                .withTextTint(color(R.color.colorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activitySereenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        if(position==POS_DASHBOARD){
            DashBoardFragment dashBoardFragment=new DashBoardFragment();
            transaction.replace(R.id.container,dashBoardFragment);
        }
        else if(position==POS_ACCOUNT){
            AccountFragment accountFragment=new AccountFragment();
            transaction.replace(R.id.container,accountFragment);
        }
        else if(position==POS_SEARCH){
            SearchFragment searchFragment=new SearchFragment();
            transaction.replace(R.id.container,searchFragment);
        }
        else if(position==POS_MAP){
            MapFragment mapFragment=new MapFragment();
            transaction.replace(R.id.container,mapFragment);
        }
        else if(position==POS_LOGOUT){
            finish();
        }
        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }
}