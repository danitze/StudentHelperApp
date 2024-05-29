package com.studenthelper.domain.usecase.universityclass

import com.studenthelper.domain.model.universityclass.AddHomeTaskDomainModel
import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import com.studenthelper.domain.usecase.base.CompletableUseCaseWithData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class AddHomeTaskUseCase @Inject constructor(
    private val universityClassRepository: UniversityClassRepository
) : CompletableUseCaseWithData<AddHomeTaskUseCase.AddHomeTaskData>() {

    override suspend fun doWork(scope: CoroutineScope, data: AddHomeTaskData) {
        universityClassRepository.addHomeTask(
            id = data.id,
            model = AddHomeTaskDomainModel(
                homeTask = data.homeTask
            )
        )
    }

    class AddHomeTaskData(
        val id: Long,
        val homeTask: String
    )
}