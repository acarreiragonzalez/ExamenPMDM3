package com.example.acarreiragonzalez.examenpmdm3;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.acarreiragonzalez.examenpmdm3.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    OnItemSelectedListener listener;


    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {


            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }
//El boton eliminará el contenido remplazándose por un texto sin nada, o en caso de estar port, el activity se cerrará
        //En el caso de algunhas versiones de Android el metodo isInLayout puede dar problemas de renderizacion
                Button eliminar = (Button) rootView.findViewById(R.id.eliminar);
                eliminar.setOnClickListener(new View.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                        @Override
                        public void onClick(View v) {
                               //se remplaza el texto por nada
                                        ((TextView) v.findViewById(R.id.item_detail)).setText("");
                               //se cierra y finaliza el activity
                                        if (rootView == null || !rootView.isInLayout()) {
                                        getActivity().finish();
                                        }

                            }
                    });
listener.oK("OK");
        return rootView;
    }
    //Creamos una interfaz con public interface e o metodo onAttach
    public interface OnItemSelectedListener {
                public void oK(String link);
            }

                @Override

        public void onAttach(Activity context) {
                super.onAttach(context);
                if (context instanceof OnItemSelectedListener) {
                    listener = (OnItemSelectedListener) context;
                    } else {
                        throw new ClassCastException(context.toString()
                                        + " must implement MyListFragment.OnItemSelectedListener");
                    }
            }

    }






