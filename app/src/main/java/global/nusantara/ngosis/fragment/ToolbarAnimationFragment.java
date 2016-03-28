package global.nusantara.ngosis.fragment;

/**
 * Created by Devryan on 3/8/16.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import global.nusantara.ngosis.R;


public class ToolbarAnimationFragment extends Fragment {
    View rootView;
    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
//    SimpleRecyclerAdapter simpleRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_animate_toolbar,null);
        final Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.anim_toolbar);
//        ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);
//        ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("Suleiman Ali Shakir");

        ImageView header = (ImageView) rootView.findViewById(R.id.header);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.background_material);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                mutedColor = palette.getMutedColor(R.color.colorPrimary);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.colorPrimaryDark);
            }
        });

//        recyclerView = (RecyclerView) rootView.findViewById(R.id.scrollableview);
//
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        List<String> listData = new ArrayList<String>();
//        int ct = 0;
//        for (int i = 0; i < VersionModel.data.length * 2; i++) {
//            listData.add(VersionModel.data[ct]);
//            ct++;
//            if (ct == VersionModel.data.length) {
//                ct = 0;
//            }
//        }
//
//        if (simpleRecyclerAdapter == null) {
//            simpleRecyclerAdapter = new SimpleRecyclerAdapter(listData);
//            recyclerView.setAdapter(simpleRecyclerAdapter);
//        }
        return rootView;
    }
}
