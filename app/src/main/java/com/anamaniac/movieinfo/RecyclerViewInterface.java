package com.anamaniac.movieinfo;

import android.view.View;

import java.util.List;

public interface RecyclerViewInterface {
    public void onClick(View view, int Position, List<TvDataModel> Movies);
}
