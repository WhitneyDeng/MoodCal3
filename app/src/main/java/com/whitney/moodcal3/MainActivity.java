package com.whitney.moodcal3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    EntryStorage entryStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entryStorage = new EntryStorage();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment()); //default fragment loaded
    }

    private boolean loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction() //begin transaction
                    .replace(R.id.FragmentContainer, fragment) //put the fragment in the fragment container
                    .commit(); //commit the changes
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        Fragment fragment = null;

        switch (menuItem.getItemId())
        {
            case R.id.navigation_home: //the item id is home page
                fragment = new HomeFragment(); //the fragment will be a HomeFragment
                ((HomeFragment) fragment).setEntryStorage(entryStorage); //transfer the same storage to this fragment
                break;

            case R.id.navigation_analysis:
                fragment = new AnalysisFragment();
                ((AnalysisFragment) fragment).setEntryStorage(entryStorage);
                break;

            case R.id.navigation_commonFactors:
                fragment = new CommonFactorsFragment();
                ((CommonFactorsFragment) fragment).setEntryStorage(entryStorage);
                break;
        }
        return loadFragment(fragment); //load the fragment
    }
}
