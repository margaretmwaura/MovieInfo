
package com.anamaniac.movieinfo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

class MovieInfo1 implements Parcelable {

    public Integer page;
    public Integer total_results;
    public Integer total_pages;
    public List<TvDataModel> results;

    protected MovieInfo1(Parcel in) {
        if (in.readByte() == 0) {
            page = null;
        } else {
            page = in.readInt();
        }
        if (in.readByte() == 0) {
            total_results = null;
        } else {
            total_results = in.readInt();
        }
        if (in.readByte() == 0) {
            total_pages = null;
        } else {
            total_pages = in.readInt();
        }
    }

    public static final Creator<MovieInfo1> CREATOR = new Creator<MovieInfo1>() {
        @Override
        public MovieInfo1 createFromParcel(Parcel in) {
            return new MovieInfo1(in);
        }

        @Override
        public MovieInfo1[] newArray(int size) {
            return new MovieInfo1[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<TvDataModel> getResults() {
        return results;
    }

    public void setResults(List<TvDataModel> results) {
        this.results = results;
    }
    public void MovieInfo(Integer Page, Integer totalResults, Integer totalPages, List<TvDataModel> Results){
        this.page = Page;
        this.total_pages = totalPages;
        this.total_results = totalResults;
        this.results = Results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (page == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(page);
        }
        if (total_results == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(total_results);
        }
        if (total_pages == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(total_pages);
        }
    }
}

