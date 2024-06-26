package com.example.newsapp.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.repository.WikiStatsRepository
import com.example.newsapp.domain.service.WikipediaStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppWidgetViewModel @Inject constructor(private val repository: WikiStatsRepository) : ViewModel() {

    private val _stats = MutableLiveData<WikipediaStats>()
    val stats: LiveData<WikipediaStats> get() = _stats

    init {
        viewModelScope.launch {
            try {
                _stats.value = repository.getStats()
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}
