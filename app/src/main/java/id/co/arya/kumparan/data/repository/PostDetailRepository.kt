package id.co.arya.kumparan.data.repository

import id.co.arya.kumparan.api.ApiService

class PostDetailRepository {

    suspend fun listPostCommentsApi(postId: String) =
        ApiService().API_SERVICE.listPostCommentsApi(postId)

}