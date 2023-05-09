package fr.tsodev.kmcar.screens.login

data class LoadingState(val status: Status, val message: String? = null) {

    companion object {
        val IDLE = LoadingState(Status.IDLE)
        val FAILED = LoadingState(Status.FAILED)
        val SUCCES = LoadingState(Status.SUCCES)
        val LOADING = LoadingState(Status.LOADING)
    }
    enum class Status {
        SUCCES,
        FAILED,
        LOADING,
        IDLE
    }
}
