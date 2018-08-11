package fia.proy2.devmov.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import fia.proy2.devmov.fragments.CompraEfectivoFragment;
import fia.proy2.devmov.fragments.CompraPromocionFragment;

/**
 * Created by james on 25/05/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs=numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CompraEfectivoFragment();
            case 1:
                return new CompraPromocionFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
