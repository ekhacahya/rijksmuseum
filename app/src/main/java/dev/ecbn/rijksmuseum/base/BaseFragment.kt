package dev.ecbn.rijksmuseum.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Created by Eka Cahya Nugraha on 03/11/21.
 * ecbn95@gmail.com
 */
abstract class BaseFragment<out VM : BaseViewModel, VB : ViewBinding>(
    kClass: KClass<VM>,
    private val mViewBinder: (LayoutInflater) -> ViewBinding
) : Fragment() {
    @Suppress("UNCHECKED_CAST")
    protected val binding by lazy(LazyThreadSafetyMode.NONE) { mViewBinder.invoke(layoutInflater) as VB }
    protected val viewModel: VM by viewModel(clazz = kClass)
    protected var mRootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRootView = binding.root
        onInitUI(savedInstanceState)
        onInitData()
    }

    abstract fun onInitUI(savedInstanceState: Bundle?)

    abstract fun onInitData()
}