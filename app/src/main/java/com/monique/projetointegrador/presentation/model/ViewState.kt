package com.monique.projetointegrador.presentation.model

sealed class ViewState {
    object MovieNotFound: ViewState()
    object GeneralError: ViewState()
}
