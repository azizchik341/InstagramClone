package com.example.instagramclone.presentation.callBack

import androidx.recyclerview.widget.DiffUtil
import com.example.instagramclone.domain.models.models.Post

class PostItemDiffCallBack(
    private val oldList:List<Post>,
    private val newList:List<Post>
):DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      val oldPost =  oldList[oldItemPosition]
        val newPost = newList[newItemPosition]
        return oldPost.post_image.url == newPost.post_image.url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPost =  oldList[oldItemPosition]
        val newPost = newList[newItemPosition]
        return oldPost == newPost
    }
}