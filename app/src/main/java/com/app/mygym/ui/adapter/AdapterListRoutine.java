package com.app.mygym.ui.adapter;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mygym.Global;
import com.app.mygym.R;
import com.app.mygym.entities.RoutineItem;
import com.app.mygym.util.StringUtils;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class AdapterListRoutine extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RoutineItem> items;
    //private  List<Routine> routineList;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, RoutineItem obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListRoutine(List<RoutineItem> items) {
        this.items = items;
        //this.RoutineList = RoutineList;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView trainer;
        public TextView description;
        public TextView name;
        public CircularImageView image;
        private View lyt_parent;

        private OriginalViewHolder(View v) {
            super(v);
            trainer = (TextView) v.findViewById(R.id.trainer);
            description = (TextView) v.findViewById(R.id.description);
            name = (TextView) v.findViewById(R.id.name);
            image = (CircularImageView) v.findViewById(R.id.image);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = Global.getInstance().getCurrentActivity().getLayoutInflater().inflate(R.layout.list_routine, null);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            RoutineItem routineItem = items.get(position);
            view.name.setText(StringUtils.capitalize(routineItem.getName()));
            view.description.setText(StringUtils.capitalize(routineItem.getDescription()));
            view.trainer.setText(StringUtils.capitalize(routineItem.getTrainer()));

            /*view.lyt_parent.setOnClickListener(view1 -> {
                if(Global.getInstance().getCurrentActivity() instanceof MainActivity){
                    MainActivity activity = (MainActivity) Global.getInstance().getCurrentActivity();
                    activity.replaceAndShowFragment(new RoutinePresenter().getView().getFragment(),

                            R.string.Routine_title, RoutinePresenter.TAG, BundleUtil.getBundle("Routine",  RoutineList.get(view.getAdapterPosition())));
                }
            });*/
        }
    }

    /*private Routine findRoutineByName(String name){
        for(Routine routine : routineList){
            if(routine.getName().equals(name)){
                return routine;
            }
        }
        return null;
    }*/

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

}