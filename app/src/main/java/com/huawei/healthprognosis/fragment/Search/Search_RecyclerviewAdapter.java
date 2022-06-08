package com.huawei.healthprognosis.fragment.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.healthprognosis.R;
import com.huawei.healthprognosis.fragment.DashBoard.DiseaseModel;

import java.util.ArrayList;

public class Search_RecyclerviewAdapter extends RecyclerView.Adapter<Search_RecyclerviewAdapter.MYViewHolder> {

    Context context;
    ArrayList<DiseaseModel> diseaseModels;
   private  SearchRecyclerViewInterface searchRecyclerViewInterface;
    Search_RecyclerviewAdapter(Context context, ArrayList<DiseaseModel> diseaseModels,  SearchRecyclerViewInterface searchRecyclerViewInterface){
        this.context=context;
        this.diseaseModels=diseaseModels;
       this.searchRecyclerViewInterface=searchRecyclerViewInterface;
    }

    @NonNull
    @Override
    public Search_RecyclerviewAdapter.MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dashboard_recycler_view_row,parent,false);

        return new Search_RecyclerviewAdapter.MYViewHolder(view,searchRecyclerViewInterface);
    }
   public void setFilteredList(ArrayList<DiseaseModel> diseaseModels){
        this.diseaseModels=diseaseModels;
         notifyDataSetChanged();
         System.out.println("ArunHUAWEI change");
   }
    @Override
    public void onBindViewHolder(@NonNull Search_RecyclerviewAdapter.MYViewHolder holder, int position) {
        holder.tvTitle.setText(diseaseModels.get(position).getDiseaseName());
        holder.tvDiscription.setText(diseaseModels.get(position).getDiseaseDiscription());
        holder.imageView.setImageResource(diseaseModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return diseaseModels.size();
    }
    public static class MYViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvTitle,tvDiscription;
        public MYViewHolder(@NonNull View itemView, SearchRecyclerViewInterface searchRecyclerViewInterface) {
            super(itemView);
            imageView=itemView.findViewById(R.id.disease_image);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvDiscription=itemView.findViewById(R.id.tv_discription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  if(searchRecyclerViewInterface!=null){
                     int pos=getAdapterPosition();
                     if(pos !=RecyclerView.NO_POSITION){
                         searchRecyclerViewInterface.onItemClick(pos);
                     }
                  }
                }
            });
        }
    }
}
