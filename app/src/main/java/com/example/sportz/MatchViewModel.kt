package com.example.sportz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _matchDetails = MutableLiveData<MatchResponse?>()
    val matchDetails: LiveData<MatchResponse?> get() = _matchDetails

    fun fetchMatchDetails(apiType: Int) {
        viewModelScope.launch {
            try {
                val response = if (apiType == 1) repository.fetchMatchDetails1() else repository.fetchMatchDetails2()
                _matchDetails.postValue(response)
                response?.let {
                    println("Fetched Match Details: $it")
                } ?: run {
                    println("Match details response is null")
                }
            } catch (e: Exception) {
                _matchDetails.postValue(null)
                println("Error fetching match details: ${e.message}")
            }
        }
    }
}
