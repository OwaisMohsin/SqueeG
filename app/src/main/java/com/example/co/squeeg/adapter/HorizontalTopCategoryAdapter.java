package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.co.squeeg.GigsLists;
import com.example.co.squeeg.Model.GETHomeGigs;
import com.example.co.squeeg.R;
import com.example.co.squeeg.SubCategoryList;
import com.example.co.squeeg.utils.AppConstants;

public class HorizontalTopCategoryAdapter extends RecyclerView.Adapter<HorizontalTopCategoryAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<GETHomeGigs.Category> categories;

    public HorizontalTopCategoryAdapter(Context mContext, List<GETHomeGigs.Category> categories) {
        this.mContext = mContext;
        this.categories = categories;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_category_item_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.categoryNames.setText(categories.get(position).getCategory());
        holder.ctvTitlename.setText(categories.get(position).getCategory().substring(0, 1));
//        if (categories.get(position).getSub_category().size() > 0) {
//            for (int i = 0; i < categories.get(position).getSub_category().size(); i++) {
//                holder.subCategoryname.setText(categories.get(position).getSub_category().get(i).getName());
//            }
//
//
//        } else {
//            holder.subCategoryname.setVisibility(View.GONE);
//        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView categoryNames;
        TextView subCategoryname;
        TextView ctvTitlename;


        MyViewHolder(View itemView) {
            super(itemView);
            categoryNames = (TextView) itemView.findViewById(R.id.txt_category_name);
            subCategoryname = (TextView) itemView.findViewById(R.id.subcategory);
            ctvTitlename = (TextView) itemView.findViewById(R.id.ctv_title_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (categories.get(getAdapterPosition()).getSubcategory().equalsIgnoreCase("1")) {
                        Intent callSubPages = new Intent(mContext, SubCategoryList.class);
                        callSubPages.putExtra(AppConstants.CAT_ID, categories.get(getAdapterPosition()).getId());
                        callSubPages.putExtra(AppConstants.CAT_NAME, categories.get(getAdapterPosition()).getCategory());
                        mContext.startActivity(callSubPages);
                    } else {
                        Intent callSubPages = new Intent(mContext, GigsLists.class);
                        callSubPages.putExtra(AppConstants.CAT_ID, categories.get(getAdapterPosition()).getId());
                        callSubPages.putExtra(AppConstants.CAT_NAME, categories.get(getAdapterPosition()).getCategory());
                        mContext.startActivity(callSubPages);
                    }
                }
            });
        }
    }
}
