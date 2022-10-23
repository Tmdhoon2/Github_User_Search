package com.example.gitgit.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitgit.model.SearchRepository
import com.example.gitgit.viewmodel.SearchViewModel

class SearchViewModelFactory(private val searchRepository: SearchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(searchRepository) as T
    }
}