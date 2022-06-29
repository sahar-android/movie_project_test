package ir.simyapp.batmanmoviesapp.models

/**
 * @author Sahar Simyari
 */
data class ResultMovies<out T>(val status: Status, val data: T?, val errorGetMovie: ErrorGetMovie?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): ResultMovies<T> {
            return ResultMovies(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, errorGetMovie: ErrorGetMovie?): ResultMovies<T> {
            return ResultMovies(Status.ERROR, null, errorGetMovie, message)
        }

        fun <T> loading(data: T? = null): ResultMovies<T> {
            return ResultMovies(Status.LOADING, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$errorGetMovie, message=$message)"
    }
}
