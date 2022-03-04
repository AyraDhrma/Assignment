package id.co.arya.kumparan.api

data class ResultApi<out T>(val statusApi: StatusApi, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(data: T?): ResultApi<T> =
            ResultApi(statusApi = StatusApi.LOADING, data = data, message = null)

        fun <T> success(data: T?): ResultApi<T> =
            ResultApi(statusApi = StatusApi.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ResultApi<T> =
            ResultApi(statusApi = StatusApi.ERROR, data = data, message = message)
    }
}