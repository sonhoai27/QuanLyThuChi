package com.sonhoai.sonho.quanlythuchi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sonho on 4/2/2018.
 */

public class ThuChiBaseAdapter extends BaseAdapter {
    private Context context;
    private List<ThuChi> thuChiList;

    public ThuChiBaseAdapter(Context context, List<ThuChi> thuChiList) {
        this.context = context;
        this.thuChiList = thuChiList;
    }

    @Override
    public int getCount() {
        return thuChiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.custom_item_thuchi, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtName.setText("Nội dung: "+thuChiList.get(i).getContent());
        holder.txtAmount.setText("Số tiền: "+thuChiList.get(i).getAmount()+"");
        holder.txtType.setText((thuChiList.get(i).getType() == 0) ? "Hình thức: Thu" : "Hình thức: Chi");
        holder.img.setImageResource((thuChiList.get(i).getType() == 0) ? R.drawable.thu : R.drawable.chi);
        return view;
    }

    private class ViewHolder{
        private TextView txtName, txtAmount, txtType;
        private ImageView img;
        public ViewHolder(View view) {
            txtName = view.findViewById(R.id.txtNoiDung);
            txtAmount = view.findViewById(R.id.txtAmount);
            txtType = view.findViewById(R.id.txtHinhThuc);
            img = view.findViewById(R.id.imgHinhThuc);
        }
    }
}
