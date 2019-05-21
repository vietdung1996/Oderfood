package com.vietdung.oderfood.ui.history;

import com.vietdung.oderfood.model.ObjectClass.History;

import java.util.List;

public interface HistoryContact {
    interface View{
        void showHistory(List<History> histories);
    }

    interface Presenter{
        void loadHistory();
    }
}
