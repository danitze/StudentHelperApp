package com.studenthelper.domain.usecase.universityclass

import com.studenthelper.domain.model.universityclass.AddMeetingLinkDomainModel
import com.studenthelper.domain.repository.universityclass.UniversityClassRepository
import com.studenthelper.domain.usecase.base.CompletableUseCaseWithData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class AddLinkToSeriesUseCase @Inject constructor(
    private val universityClassRepository: UniversityClassRepository
) : CompletableUseCaseWithData<AddLinkToSeriesUseCase.AddLinkData>() {

    override suspend fun doWork(scope: CoroutineScope, data: AddLinkData) {
        universityClassRepository.addLinkToSeries(
            seriesId = data.seriesId,
            model = AddMeetingLinkDomainModel(
                link = data.link
            )
        )
    }

    class AddLinkData(
        val seriesId: String,
        val link: String
    )
}