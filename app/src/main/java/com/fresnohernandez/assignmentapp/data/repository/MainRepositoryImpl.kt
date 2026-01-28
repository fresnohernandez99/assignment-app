package com.fresnohernandez.assignmentapp.data.repository

import com.fresnohernandez.assignmentapp.data.source.local.MainDao
import com.fresnohernandez.assignmentapp.data.source.remote.MainService
import com.fresnohernandez.assignmentapp.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val mainDao: MainDao,
) : MainRepository