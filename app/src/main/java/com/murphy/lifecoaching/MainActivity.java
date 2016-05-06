package com.murphy.lifecoaching;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.murphy.lifecoaching.fragments.GoodsFragment;
import com.murphy.lifecoaching.fragments.HomeFragment;
import com.murphy.lifecoaching.fragments.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private RelativeLayout maincontent;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static boolean isBook = true;
    public static int book_pos = 1;
    public static int tshirt_pos = 1;
    private ImageView imageView;
    public static int book_carts;
    public static int tshirt_carts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        maincontent = (RelativeLayout) findViewById(R.id.main_content);
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        listSliding = new ArrayList<>();
        listSliding.add(new ItemSlideMenu(R.drawable.ic_menuhome,"Home","Murphy Life Coaching"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_menubook, "Browse Books", "Browse Books and Tshirts"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_menutshirt, "Browse Tshirts", "Browse Books and Tshirts"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_menuservice, "View All Services", "View All Services"));
        adapter = new SlidingMenuAdapter(this,listSliding);
        listViewSliding.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.image_logo3);

        setTitle(listSliding.get(0).getMainTitle());
        listViewSliding.setItemChecked(0, true);
        drawerLayout.closeDrawer(listViewSliding);

        replaceFragment(0);

        // Drawer Item click listeners
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }
    private void selectItemFromDrawer(int position){
        listViewSliding.setItemChecked(position, true);
        setTitle(listSliding.get(position).getMainTitle());
        drawerLayout.closeDrawer(listViewSliding);
        replaceFragment(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
      //  boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.menuHome).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        Log.w("menu selected",String.valueOf(item.getItemId()));
       switch (item.toString())
       {
           case "Call":
               callNumber();
               break;
           case "Message":
               sendMessage();
               break;
           case "More Contacts":
               moreContact();
               break;

       }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    private void replaceFragment(int pos){
        Fragment fragment = null;
        switch (pos){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new GoodsFragment();
                isBook = true;
                break;
            case 2:
                fragment = new GoodsFragment();
                isBook = false;
                break;
            case 3:
                fragment = new ServiceFragment();
                break;
        }
        if(null != fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    public void onButtonClick(View v){
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.book_btn:
                fragment = new GoodsFragment();
                isBook = true;
                break;
            case R.id.tshirt_btn:
                fragment = new GoodsFragment();
                isBook = false;
                break;
            case R.id.service_btn:
                fragment = new ServiceFragment();
                break;
        }

        if(null != fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    public void onBuyClick(View v){

    }
    public void onCartClick(View v){
        Button cart_btn = (Button)findViewById(R.id.cart_num);
        if(isBook)
            book_carts +=1;
        else
            tshirt_carts +=1;
        cart_btn.setText(String.valueOf(book_carts+tshirt_carts));
    }
    public void onOnlineClick(View v){
        Intent i = new Intent(getApplicationContext(),StoreActivity.class);
        i.putExtra("isBook",(isBook)?"1":"0");
        startActivity(i);
    }
    public void onSwitchClick(boolean book){
        isBook = book;
        this.showImage();
    }

    public void onPlayClick(View v){
        switch (v.getId()){
            case R.id.next_btn:
                nextImage();
                break;
            case R.id.prev_btn:
                prevImage();
                break;
        }

    }
    public void nextImage(){
        if(isBook){
            book_pos = (book_pos==1)?2:1;
        } else {
            if(tshirt_pos==6) tshirt_pos=1;
            else tshirt_pos += 1;
        }
        this.showImage();
    }
    public void prevImage(){
        if(isBook){
            book_pos = (book_pos==1)?2:1;
        } else {
            if(tshirt_pos==1) tshirt_pos=6;
            else tshirt_pos -= 1;
        }
        this.showImage();
    }
    private void showImage(){
        imageView = (ImageView)findViewById(R.id.book_tshirt);
        String imagePath = (isBook)?"b" + book_pos:"t"+tshirt_pos;
        int id = getResources().getIdentifier(imagePath, "drawable", getPackageName());
        imageView.setImageResource(id);
    }
    public void callNumber(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:+18005446037"));
        startActivity(callIntent);
    }
    public void sendMessage(){
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:+18005446037"));
        intent.putExtra("sms_body", "");
        startActivity(intent);
    }
    public void moreContact(){
        Intent intent = new Intent( getApplicationContext(), ContactActivity.class);
        startActivity(intent);
    }
}
