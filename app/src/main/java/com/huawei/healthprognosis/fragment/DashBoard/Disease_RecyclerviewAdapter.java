package com.huawei.healthprognosis.fragment.DashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.healthprognosis.R;

import java.util.ArrayList;

public class Disease_RecyclerviewAdapter extends RecyclerView.Adapter<Disease_RecyclerviewAdapter.MyViewHolder> {
    private final DashBoardRecyclerViewInterface dashBoardRecyclerViewInterface;

    Context context;
    ArrayList<DiseaseModel> diseaseModels;
    public Disease_RecyclerviewAdapter(Context context, ArrayList<DiseaseModel> diseaseModels,DashBoardRecyclerViewInterface dashBoardRecyclerViewInterface){
        this.context=context;
        this.diseaseModels=diseaseModels;
        this.dashBoardRecyclerViewInterface=dashBoardRecyclerViewInterface;
    }
    @NonNull
    @Override
    public Disease_RecyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater=LayoutInflater.from(context);//context
        View view=inflater.inflate(R.layout.dashboard_recycler_view_row,parent,false);
        return new Disease_RecyclerviewAdapter.MyViewHolder(view,dashBoardRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Disease_RecyclerviewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view
        holder.tvTitle.setText(diseaseModels.get(position).getDiseaseName());
        holder.tvDiscription.setText(diseaseModels.get(position).getDiseaseDiscription());
        holder.imageView.setImageResource(diseaseModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        //the recycler view just wants to know the number of Items you want displayed
        return diseaseModels.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from our recycler_view_row layout file
        //kinda like in the onCreate method.
        ImageView imageView;
        TextView tvTitle,tvDiscription;
        public MyViewHolder(@NonNull View itemView,DashBoardRecyclerViewInterface dashBoardRecyclerViewInterface) {
            super(itemView);
            imageView=itemView.findViewById(R.id.disease_image);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvDiscription=itemView.findViewById(R.id.tv_discription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 if(dashBoardRecyclerViewInterface !=null){
                         int pos=getAdapterPosition();
                         if(pos !=RecyclerView.NO_POSITION){
                             dashBoardRecyclerViewInterface.onItemClick(pos);
                         }
                    }
                }
            });
        }
    }
}