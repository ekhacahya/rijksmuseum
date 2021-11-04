package dev.ecbn.rijksmuseum.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
abstract class BaseActivity<out VM : BaseViewModel, VB : ViewBinding>(
    kClass: KClass<VM>,
    private val mViewBinder: (LayoutInflater) -> ViewBinding
) : AppCompatActivity() {

    @Suppress("UNCHECKED_CAST")
    protected val binding by lazy(LazyThreadSafetyMode.NONE) { mViewBinder.invoke(layoutInflater) as VB }
    protected val viewModel: VM by viewModel(clazz = kClass)
    protected var mRootView: View? = null
    protected var mDataReceived: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRootView = binding.root
        mDataReceived = intent.extras
        setContentView(mRootView)
        onInitUI(savedInstanceState)
        onInitData()
    }

    abstract fun onInitUI(savedInstanceState: Bundle?)

    abstract fun onInitData()

}