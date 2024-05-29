package com.studenthelper.domain.usecase.universityclass

import com.studenthelper.domain.model.universityclass.AddMeetingLinkDomainModel
import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import com.studenthelper.domain.usecase.base.CompletableUseCaseWithData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class AddLinkUseCase @Inject constructor(
    private val universityClassRepository: UniversityClassRepository
) : CompletableUseCaseWithData<AddLinkUseCase.AddLinkData>() {

    override suspend fun doWork(scope: CoroutineScope, data: AddLinkData) {
        universityClassRepository.addLink(
            id = data.id,
            model = AddMeetingLinkDomainModel(
                link = data.link
            )
        )
    }

    class AddLinkData(
        val id: Long,
        val link: String
    )
}