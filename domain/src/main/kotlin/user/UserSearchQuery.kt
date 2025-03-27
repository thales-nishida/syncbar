package user

data class UserSearchQuery(
    val page: Int,
    val perPage: Int,
    val terms: String,
    val sort: String,
    val direction: String,
)
