package com.vietdung.oderfood.ui.typemenu;

import com.vietdung.oderfood.model.TypeFood;

import java.util.List;

public interface PresenterMenu {
    interface View {
        void disPlayList(List<TypeFood> typeFoods);

    }

    interface Presenter{
        public List<TypeFood> loadTypeFood();
    }
}
