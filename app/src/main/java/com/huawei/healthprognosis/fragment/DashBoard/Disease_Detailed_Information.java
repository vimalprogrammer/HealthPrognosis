package com.huawei.healthprognosis.fragment.DashBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.huawei.healthprognosis.R;

public class Disease_Detailed_Information extends Fragment {
    ImageView imageView;
    TextView title;
    TextView des;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_disease_detailed_information,container,false);
        imageView=root.findViewById(R.id.para_img);
        title=root.findViewById(R.id.para_title);
        des=root.findViewById(R.id.para_des);

        Bundle bundle=this.getArguments();
        String title_name=bundle.getString("title");
        String img=bundle.getString("image");
        int image=Integer.parseInt(img);
        String para=bundle.getString("para");
        imageView.setImageResource(image);
        title.setText(title_name);
        des.setText(para);
        return root;
    }

}
