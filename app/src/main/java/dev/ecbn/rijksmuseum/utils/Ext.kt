package dev.ecbn.rijksmuseum.utils

import android.content.Context
import android.graphics.Rect
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Eka Cahya Nugraha on 26/10/21.
 * ecbn95@gmail.com
 */
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { it?.let { t -> action(t) } })
}

fun View.showIf(isShow: Boolean = true) {
    visibility = if (isShow) View.VISIBLE else View.GONE
}

fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    this?.let { Toast.makeText(it, text, duration).show() }

fun Context.setupRecyclerViewList(recyclerView: RecyclerView) {
    recyclerView.apply {
        setHasFixedSize(true)
        layoutManager = this@setupRecyclerViewList.verticalLayout()
        itemAnimator = DefaultItemAnimator()
    }
}

fun Context.verticalLayout(): LinearLayoutManager =
    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

class SpacesItemDecoration(space: Int) : ItemDecoration() {
    private val halfSpace: Int
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if (parent.paddingLeft != halfSpace) {
            parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace)
            parent.clipToPadding = false
        }
        outRect.top = halfSpace
        outRect.bottom = halfSpace
        outRect.left = halfSpace
        outRect.right = halfSpace
    }

    init {
        halfSpace = space / 2
    }
}

fun View.snackBarDismissAction(actionString: String, message: String?) {
    val sb = Snackbar.make(this, message.toString(), Snackbar.LENGTH_INDEFINITE)
//    val view = sb.view
//    val params = view.layoutParams as FrameLayout.LayoutParams
//    params.gravity = Gravity.TOP
//    view.layoutParams = params
    sb.setAction(actionString) {
        sb.dismiss()
    }.show()
}