package com.github.rezalotfi01.weberpro.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.rezalotfi01.weberpro.Browser.AdBlock;
import com.github.rezalotfi01.weberpro.R;

import java.util.List;

public class WhitelistAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final int layoutResId;
    private final List<String> list;

    public WhitelistAdapter(Context context, int layoutResId, List<String> list){
        super(context, layoutResId, list);
        this.context = context;
        this.layoutResId = layoutResId;
        this.list = list;
    }

    private static class Holder {
        TextView domain;
        ImageButton cancel;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(layoutResId, parent, false);
            holder = new Holder();
            holder.domain = view.findViewById(R.id.whilelist_item_domain);
            holder.cancel = view.findViewById(R.id.whilelist_item_cancel);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.domain.setText(list.get(position));
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdBlock adBlock = new AdBlock(context);
                adBlock.removeDomain(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
                WeberToast.Companion.show(context, R.string.toast_delete_successful);
            }
        });

        return view;
    }
}
