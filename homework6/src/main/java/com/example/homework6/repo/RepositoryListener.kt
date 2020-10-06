package com.example.homework6.repo

interface RepositoryListener<T> {
    fun onDataReceived(data : T)
}