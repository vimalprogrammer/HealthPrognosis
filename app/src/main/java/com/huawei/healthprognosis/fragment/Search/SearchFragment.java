package com.huawei.healthprognosis.fragment.Search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.healthprognosis.R;
import com.huawei.healthprognosis.fragment.DashBoard.DiseaseModel;
import com.huawei.healthprognosis.fragment.DashBoard.Disease_Detailed_Information;

import java.time.Clock;
import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchRecyclerViewInterface
{
    ArrayList<DiseaseModel> diseaseModels=new ArrayList<>();
    RecyclerView recyclerView;
    private SearchView searchView;
    Search_RecyclerviewAdapter adapter;

    int []diseaseImage={R.drawable.fever,R.drawable.stomache,R.drawable.leg_pain,R.drawable.wirst_pain,
            R.drawable.throats_pain,R.drawable.eye_pain,R.drawable.headaches,R.drawable.foot_bath,
            R.drawable.cool_or_warm_compress,R.drawable.nausea};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.search_fragment,container,false);
        searchView=root.findViewById(R.id.diseaseSearchView);
        searchView.clearFocus();
        recyclerView=root.findViewById(R.id.search_recyclerView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpDiseaseModels();

        adapter=new Search_RecyclerviewAdapter(getActivity(),diseaseModels,this);
        recyclerView.setAdapter(adapter);

        return root;
    }
    private void filterList(String text){
        ArrayList<DiseaseModel> resultSet=new ArrayList<>();
        for(DiseaseModel item:diseaseModels){
            String name=item.getDiseaseName().toLowerCase();
            if(name.contains(text.toLowerCase()))
             {
                resultSet.add(item);
                System.out.println("HUAWEI ARUN "+resultSet.size()+"--"+item.getDiseaseName().toLowerCase());

             }
        }
        if(resultSet.isEmpty()){

        }
        else{
             adapter.setFilteredList(resultSet);
        }
        adapter. notifyDataSetChanged();
    }


    private ArrayList<DiseaseModel> setUpDiseaseModels(){
        String[] diseaseTitle=getResources().getStringArray(R.array.home_page_disease_name_list);
        String[] diseaseDescription=getResources().getStringArray(R.array.home_page_disease_name_list_description);
        String[] diseasePara=getResources().getStringArray(R.array.disease_name_Para_list);

        for(int i=0;i<diseaseTitle.length;i++){
            diseaseModels.add(new DiseaseModel(diseaseTitle[i],diseaseDescription[i],diseaseImage[i],diseasePara[i]));
        }
        return diseaseModels;
    }

    @Override
    public void onItemClick(int positon) {
        Bundle bundle=new Bundle();
        bundle.putString("title",diseaseModels.get(positon).getDiseaseName());
        bundle.putString("image",diseaseModels.get(positon).getImage()+"");
        bundle.putString("para",diseaseModels.get(positon).getPara());

        Disease_Detailed_Information fragment=new Disease_Detailed_Information();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.detailed_Search_para_container,fragment).addToBackStack(null).commit();
    }
}