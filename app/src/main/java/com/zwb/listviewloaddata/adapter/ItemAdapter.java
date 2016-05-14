package com.zwb.listviewloaddata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zwb.listviewloaddata.R;
import com.zwb.listviewloaddata.entity.ItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:ListView的Adapter
 * 作者：zwb
 * 时间：16-5-9 11:06
 * 邮箱：@163.com
 */
public class ItemAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<ItemEntity> list;

    public ItemAdapter(Context context, ArrayList<ItemEntity> list) {
        this.context = context;
        this.list = list;
    }
    public void onDateChange(ArrayList<ItemEntity> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemEntity entity = list.get(position);
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout , parent ,false);
            holder.name = ((TextView) convertView.findViewById(R.id.item_name));
            holder.msg = ((TextView) convertView.findViewById(R.id.item_msg));
            convertView.setTag(holder);
        }else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.name.setText(entity.getName());
        holder.msg.setText(entity.getMsg());

        return convertView;
    }


    class ViewHolder{
        TextView name;
        TextView msg;
    }
}
