package pagination

data class Pagination<T>(
    val currentPage: Int,
    val perPage: Int,
    val totalPages: Long,
    val items: List<T>,
) {
}