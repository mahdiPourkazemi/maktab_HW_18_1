package com.pourkazemi.mahdi.maktab_hw_18_1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.maktab_hw_18_1.data.Repository
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.Person
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.SendPerson
import com.pourkazemi.mahdi.maktab_hw_18_1.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class OnlineViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

/*     private val _generatedToken: MutableStateFlow<String> = MutableStateFlow("null")
     val generatedToken= _generatedToken.asStateFlow()*/

    fun uploadImage(id: String, image: ByteArray) {
        viewModelScope.launch {
            val multipartBody = MultipartBody.create(MediaType.parse("image/*"), image)
            /*val multipartBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", image)
                .build()*/
            val mbody = MultipartBody.Part.createFormData("image", "image.jpg", multipartBody)
            /*job?.await()*/
            repository.uploadImage(id, mbody)
        }
    }


    private var generatedTokenFromServe: String? = null
    fun createPersonOnServer(sendPerson: SendPerson, image: ByteArray) {
        val job = viewModelScope.async {
            generatedTokenFromServe=repository.createPerson(sendPerson)
        }
        viewModelScope.launch {
            job.await()
            generatedTokenFromServe?.let { uploadImage(it, image) }
        }
    }

    private val _personListOnline: MutableStateFlow<ResultWrapper<List<Person>>> =
        MutableStateFlow(ResultWrapper.Loading)
    val personListOnline = _personListOnline.asStateFlow()



     fun getPersonList() {
        viewModelScope.launch {
            val response = repository.getUserList()
            response.collect {
                _personListOnline.emit(it)
            }
        }
    }

}
