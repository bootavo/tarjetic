package fia.proy2.devmov.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fia.proy2.devmov.R;

/**
 * Created by bootavo on 26/05/2017.
 */

public class InicioFragment extends Fragment{

    private View rootView;
    private Context ctx;

    public InicioFragment() {
        rootView = null;
        ctx = null;
    }

    public static InicioFragment newInstance(){
        return new InicioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_inicio, null);
        ctx = container.getContext();

        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Inicio");
    }
}
