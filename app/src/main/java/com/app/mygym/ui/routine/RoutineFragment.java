package com.app.mygym.ui.routine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mygym.R;
import com.app.mygym.databinding.FragmentRoutineBinding;
import com.app.mygym.entities.RoutineItem;
import com.app.mygym.ui.adapter.AdapterListRoutine;
import com.app.mygym.util.MockEntities;

import java.util.List;

public class RoutineFragment extends Fragment {

    private FragmentRoutineBinding binding;
    private RecyclerView recyclerView;
    private AdapterListRoutine mAdapter;
    private List<RoutineItem> routineItems;
    private View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentRoutineBinding.inflate(inflater, container, false);
        rootView = binding.getRoot();
        initComponents();
        return rootView;
    }

    protected void initComponents() {
        setItems();
        mAdapter = new AdapterListRoutine(routineItems);
        recyclerView.setAdapter(mAdapter);
    }

    private void setItems(){
        initList();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
    }

    private void initList(){
        routineItems = MockEntities.mockRoutines();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}