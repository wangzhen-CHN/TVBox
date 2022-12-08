package com.github.tvbox.osc.ui.adapter;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.tvbox.osc.R;
import com.github.tvbox.osc.bean.Movie;
import com.github.tvbox.osc.picasso.RoundTransformation;
import com.github.tvbox.osc.util.DefaultConfig;
import com.github.tvbox.osc.util.LOG;
import com.github.tvbox.osc.util.MD5;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.jessyan.autosize.utils.AutoSizeUtils;

public class HomeHotVodAdapter extends BaseQuickAdapter<Movie.Video, BaseViewHolder> {

    public HomeHotVodAdapter() {
        super(R.layout.item_user_hot_vod_item, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Movie.Video item) {
        TextView tvNote = helper.getView(R.id.tvNote);
        TextView tvName = helper.getView(R.id.tvName);
        TextView tvHotType = helper.getView(R.id.tvHotType);
        if (item.note == null || item.note.isEmpty()) {
            tvNote.setVisibility(View.GONE);
        } else {
            tvNote.setVisibility(View.VISIBLE);
        }
        ImageView ivThumb = helper.getView(R.id.ivThumb);
        FrameLayout tvHotItem = helper.getView(R.id.tvHotItem);
        if (helper.getLayoutPosition()>1){
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 260);
            tvHotItem.setLayoutParams(layoutParams);
            tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX,26);
            tvNote.setTextSize(TypedValue.COMPLEX_UNIT_PX,24);
            tvHotType.setTextSize(TypedValue.COMPLEX_UNIT_PX,22);
        }else {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 500);
            tvHotItem.setLayoutParams(layoutParams);
            tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX,38);
            tvNote.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
            tvHotType.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
        }
        tvNote.setText(item.note);
        tvHotType.setText(item.type);
        tvName.setText(item.name);
        //由于部分电视机使用glide报错
        if (!TextUtils.isEmpty(item.pic)) {
            Picasso.get()
                    .load(DefaultConfig.checkReplaceProxy(item.pic))
                    .transform(new RoundTransformation(MD5.string2MD5(item.pic + "position=" + helper.getLayoutPosition()))
                            .centerCorp(true))
                    .placeholder(R.drawable.img_loading_placeholder)
                    .error(R.drawable.img_loading_placeholder)
                    .into(ivThumb);
        } else {
            ivThumb.setImageResource(R.drawable.img_loading_placeholder);
//                                        .override(AutoSizeUtils.mm2px(mContext, 540), AutoSizeUtils.mm2px(mContext, 303))

        }
    }
}