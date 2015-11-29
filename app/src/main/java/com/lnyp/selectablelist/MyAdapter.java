package com.lnyp.selectablelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyAdapter extends BaseAdapter {

    private Context mContext;

    private List<DataBean> mDatas;

    private LayoutInflater mInflater;

    public boolean flage = false;

    public Map<Integer, String> selected;

    private List<String> dataIds;

    public MyAdapter(Context mContext, List<DataBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;

        mInflater = LayoutInflater.from(this.mContext);

        selected = new HashMap<>();
        dataIds = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (convertView == null) {
            // 下拉项布局
            convertView = mInflater.inflate(R.layout.list_item_data, null);

            holder = new ViewHolder();

            holder.checkboxOperateData = (CheckBox) convertView.findViewById(R.id.checkbox_operate_data);
            holder.textTitle = (TextView) convertView.findViewById(R.id.text_title);
            holder.textDesc = (TextView) convertView.findViewById(R.id.text_desc);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        DataBean dataBean = mDatas.get(position);
        if (dataBean != null) {
            holder.textTitle.setText(dataBean.title);
            holder.textDesc.setText(dataBean.desc);


            // 根据isSelected来设置checkbox的选中状况
            if (flage) {
                holder.checkboxOperateData.setVisibility(View.VISIBLE);
            } else {
                holder.checkboxOperateData.setVisibility(View.GONE);
            }

            holder.checkboxOperateData.setTag(position);

            addListener(holder, dataBean.id);//添加事件响应

            if (selected.containsKey(position))

                holder.checkboxOperateData.setChecked(true);
            else
                holder.checkboxOperateData.setChecked(false);

        }


        return convertView;
    }

    private void addListener(ViewHolder holder, final String id) {

        holder.checkboxOperateData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    if (!selected.containsKey(buttonView.getTag())) {
                        selected.put((Integer) buttonView.getTag(), id);
                    }

                } else {
                    selected.remove((Integer) buttonView.getTag());
                }
            }

        });
    }

    public List<String> getIds() {

        Collection<String> ids = selected.values();
        dataIds.clear();

        for (String id : ids) {
            dataIds.add(id);
        }

        return dataIds;
    }

    class ViewHolder {

        public CheckBox checkboxOperateData;

        public TextView textTitle;

        public TextView textDesc;
    }
}
