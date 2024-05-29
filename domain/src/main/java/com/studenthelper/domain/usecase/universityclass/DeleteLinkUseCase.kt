package com.studenthelper.domain.usecase.universityclass

import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import com.studenthelper.domain.usecase.base.CompletableUseCaseWithData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DeleteLinkUseCase @Inject constructor(
    private val universityClassRepository: UniversityClassRepository
) : CompletableUseCaseWithData<Long>() {

    override suspend fun doWork(scope: CoroutineScope, data: Long) {
        universityClassRepository.deleteLink(data)
    }

}