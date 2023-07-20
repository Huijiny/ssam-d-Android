package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.chat.ChatDetailResponse
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatInfoResponseBody
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatsResponseBody
import com.mashup.data.source.remote.dto.responsebody.chat.ChatRoomPagingResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatService {

    @GET("rooms/{id}")
    suspend fun getChatInfo(
        @Path("id") id: Long
    ): BaseResponse<GetChatInfoResponseBody>

    @GET("rooms/{id}/chats")
    suspend fun getChats(
        @Path("id") id: Long,
        @Query("pageNo") pageNo: Int,
        @Query("pageLength") pageLength: Int
    ): BaseResponse<GetChatsResponseBody>
    @GET("rooms")
    suspend fun getChatRooms(
        @Query("pageNo") pageNo: Int,
        @Query("pageLength") pageLength: Int?
    ): BaseResponse<ChatRoomPagingResponse>

    @GET("rooms/{roomId}/chats/{chatId}")
    suspend fun getChatDetail(
        @Path("roomId") roomId: Long,
        @Path("chatId") chatId: Long
    ): BaseResponse<ChatDetailResponse>

    @DELETE("rooms/{roomId}")
    suspend fun disconnectRoom(
        @Path("roomId") roomId: Long
    ): BaseResponse<Unit>
}
