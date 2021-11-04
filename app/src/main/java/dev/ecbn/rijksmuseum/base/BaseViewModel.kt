package dev.ecbn.rijksmuseum.base

import androidx.lifecycle.ViewModel
import dev.ecbn.rijksmuseum.data.local.SessionHelper

/**
 * Created by Eka Cahya Nugraha on 02/11/21.
 * ecbn95@gmail.com
 */
abstract class BaseViewModel : ViewModel() {
    val sessionHelper = SessionHelper()
}