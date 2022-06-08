package com.huawei.healthprognosis.fragment.DashBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.healthprognosis.R;

import java.util.ArrayList;

public class DashBoardFragment  extends Fragment implements DashBoardRecyclerViewInterface
{
    ArrayList<DiseaseModel> diseaseModels=new ArrayList<>();
    int []diseaseImage={R.drawable.fever,R.drawable.stomache,R.drawable.leg_pain,R.drawable.wirst_pain,
            R.drawable.throats_pain,R.drawable.eye_pain,R.drawable.headaches,R.drawable.foot_bath,
            R.drawable.cool_or_warm_compress,R.drawable.nausea};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view=(ViewGroup) inflater.inflate(R.layout.dashboard_fragment,container,false);
        RecyclerView recyclerView=view.findViewById(R.id.dashboard_recyclerView);
        setUpDiseaseModels();

        Disease_RecyclerviewAdapter adapter=new Disease_RecyclerviewAdapter(getActivity(),diseaseModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

    }
    private void setUpDiseaseModels(){
        String[] diseaseTitle=getResources().getStringArray(R.array.home_page_disease_name_list);
        String[] diseaseDescription=getResources().getStringArray(R.array.home_page_disease_name_list_description);
        String[] diseasePara=getResources().getStringArray(R.array.disease_name_Para_list);

        for(int i=0;i<diseaseTitle.length;i++){
            diseaseModels.add(new DiseaseModel(diseaseTitle[i],diseaseDescription[i],diseaseImage[i],diseasePara[i]));
        }
    }


    @Override
    public void onItemClick(int positon) {
       Bundle bundle=new Bundle();
       bundle.putString("title",diseaseModels.get(positon).getDiseaseName());
        bundle.putString("image",diseaseModels.get(positon).getImage()+"");
        bundle.putString("para",diseaseModels.get(positon).getPara());

        Disease_Detailed_Information fragment=new Disease_Detailed_Information();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.detailed_para_container,fragment).addToBackStack(null).commit();
    }
}