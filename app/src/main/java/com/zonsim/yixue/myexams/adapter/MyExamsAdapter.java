package com.zonsim.yixue.myexams.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.zonsim.yixue.R;
import com.zonsim.yixue.bean.MyExamsResp;
import com.zonsim.yixue.util.L;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

import static com.zonsim.yixue.App.app;

/**
 * ^-^
 * Created by tang-jw on 2017/6/21.
 */

public class MyExamsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    private List<MyExamsResp.ExamBean> mList;
    private OnMyExamClickListener mItemClickListener;
    
    public MyExamsAdapter(@NonNull List<MyExamsResp.ExamBean> list,
                          @NonNull OnMyExamClickListener itemClickListener) {
        mList = list;
        mItemClickListener = itemClickListener;
        L.i("new MyExamsAdapter()");
    }
    
    public void replaceData(@NonNull List<MyExamsResp.ExamBean> list) {
        mList = list;
        notifyDataSetChanged();
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        
        View view = inflater.inflate(R.layout.item_myexam, parent, false);
        
        final ItemVH itemVH = new ItemVH(view);
        RxView.clicks(view)
                .throttleFirst(500L, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mItemClickListener.onItemClick(mList.get(itemVH.getAdapterPosition()));
                    }
                });
        
        return itemVH;
    }
    
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyExamsResp.ExamBean examBean = mList.get(holder.getAdapterPosition());
    
        ItemVH itemVH = (ItemVH) holder;
    
        L.i("------------------------------");
        itemVH.tvName.setText(examBean.getProfessionName());
        itemVH.tvTime.setText(examBean.getApplyDate());
    
    
        itemVH.tvName.setText(examBean.getProfessionName() + examBean.getApplyProfessionLevel() + "级");
        itemVH.tvTime.setText("考试日期：" + examBean.getExamDate());
    
        switch (examBean.getApplyStatus()) {
            case "2":
                itemVH.tvStatus.setText("未支付");
                itemVH.tvStatus.setTextColor(app.getResources().getColor(R.color.colorExamStatus1));
                itemVH.tvStatus.setBackgroundResource(R.drawable.shape_bg_myexam_status_1);
                break;
        
            case "3":
                itemVH.tvStatus.setText("待考试");
                itemVH.tvStatus.setTextColor(app.getResources().getColor(R.color.colorExamStatus2));
                itemVH.tvStatus.setBackgroundResource(R.drawable.shape_bg_myexam_status_2);
                break;
        
            case "4":
                itemVH.tvStatus.setText("领取证书");
                itemVH.tvStatus.setTextColor(app.getResources().getColor(R.color.colorExamStatus3));
                itemVH.tvStatus.setBackgroundResource(R.drawable.shape_bg_myexam_status_3);
                break;
        
            case "5":
                itemVH.tvStatus.setText("待补考");
                itemVH.tvStatus.setTextColor(app.getResources().getColor(R.color.colorExamStatus4));
                itemVH.tvStatus.setBackgroundResource(R.drawable.shape_bg_myexam_status_4);
                break;
        
            case "7":
                itemVH.tvStatus.setText("已完成");
                itemVH.tvStatus.setTextColor(app.getResources().getColor(R.color.colorExamStatus5));
                itemVH.tvStatus.setBackgroundResource(R.drawable.shape_bg_myexam_status_5);
                break;
        
        }
    
    }
    
    
    @Override
    public int getItemCount() {
        return mList.size();
    }
    
    private class ItemVH extends RecyclerView.ViewHolder {
        
        TextView tvName;
        TextView tvTime;
        TextView tvStatus;
        
        ItemVH(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
    
    public interface OnMyExamClickListener {
        
        void onItemClick(MyExamsResp.ExamBean myExam);
        
        
    }
    
    
}
