package com.huawei.healthprognosis.fragment.DashBoard;

public class DiseaseModel {
    String diseaseName;
    String diseaseDiscription;
    int image;
    String para;

    public DiseaseModel(String diseaseName, String diseaseDiscription, int image,String para) {
        this.diseaseName = diseaseName;
        this.diseaseDiscription = diseaseDiscription;
        this.image = image;
        this.para=para;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDiseaseDiscription() {
        return diseaseDiscription;
    }

    public int getImage() {
        return image;
    }
    public String getPara(){
        return para;
    }
}