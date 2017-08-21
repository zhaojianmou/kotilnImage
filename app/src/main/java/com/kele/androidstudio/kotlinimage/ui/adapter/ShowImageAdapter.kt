package com.kele.androidstudio.kotlinimage.ui.adapter

import android.app.Activity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kele.androidstudio.kotlinimage.R

class ShowImageAdapter : Adapter<ShowImageAdapter.ItemHolder>, View.OnClickListener {

    var context: Activity? = null
    var list: List<String>? = null
    var mOnItemClickListener: OnItemClickListener? = null

    constructor(context: Activity, list: List<String>, listener: OnItemClickListener) {
        this.context = context
        this.list = list
        this.mOnItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShowImageAdapter.ItemHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_showimage, parent, false)
        var vh = ItemHolder(view)
        vh.cardView!!.setOnClickListener(this)
        return vh
    }

    override fun onBindViewHolder(holder: ShowImageAdapter.ItemHolder?, position: Int) {
        if (holder != null) {
            holder.cardView!!.setTag(position)
            Glide.with(context).load(list!![position]).into(holder.iamge)
        }

    }


    override fun getItemCount(): Int {
        if (list == null || list!!.size === 0) {
            return 0
        }
        return list!!.size

    }

    class ItemHolder : RecyclerView.ViewHolder {

        public var iamge: ImageView? = null
        public var cardView: CardView? = null

        constructor(view: View) : super(view) {
            iamge = view.findViewById(R.id.image) as ImageView
            cardView = view.findViewById(R.id.cardView) as CardView
        }
    }

    override fun onClick(v: View?) {
        if (mOnItemClickListener != null && v != null) {
            mOnItemClickListener!!.onItemClick(v, v!!.getTag() as Int)
        }
    }

    public fun getPath(position: Int): String {
        return list!![position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }


}