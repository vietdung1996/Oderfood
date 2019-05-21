package com.vietdung.oderfood.ui.detailhistory;

import com.vietdung.oderfood.model.ObjectClass.Food;

import java.util.List;

public interface DetailHistoryContract {
    interface View{
        void showHistory(List<Food> foods);
    }
    interface Presenter{
        void loadDetailHistory(int idInvoice, int limit);
    }

    interface GetHistoryIntractor{
        interface OnFinishedListener{
            void onFinished(List<Food> foods);
            void onFailure(Throwable t);
        }

        void getFoods(OnFinishedListener onFinishedListener,int idinvoice, int limit);

    }
}
