package pagination

data class Pagination<T>(
    val currentPage: Int,
    val perPage: Int,
    val total: Long,
    val items: List<T>,
) {

    fun <R> map(mapper: (T) -> R): Pagination<R> {
        val aNewList: List<R> = this.items.stream().map(mapper).toList()
        return Pagination(currentPage, perPage, total, aNewList)
    }
}
