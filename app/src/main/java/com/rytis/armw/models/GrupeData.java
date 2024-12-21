package com.rytis.armw.models;

import java.util.List;
import java.util.Map;

public class GrupeData {

    public class WeightCategoryRequest {
        public Data data;

        public WeightCategoryRequest(Data data) {
            this.data = data;
        }
    }

    public class Data {
        public GenderData V;
        public GenderData M;

        public Data(GenderData v, GenderData m) {
            V = v;
            M = m;
        }
    }

    public class GenderData {
        public Map<String, CategoryData> categories;

        public GenderData(Map<String, CategoryData> categories) {
            this.categories = categories;
        }

    }
    public class CategoryData {
        public List<String> K;
        public List<String> D;

        public CategoryData(List<String> k, List<String> d) {
            K = k;
            D = d;
        }
    }


}
