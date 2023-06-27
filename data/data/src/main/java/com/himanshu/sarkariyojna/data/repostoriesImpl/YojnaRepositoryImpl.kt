package com.himanshu.sarkariyojna.data.repostoriesImpl

import com.dropbox.android.external.store4.*
import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaMetaData
import com.himanshu.sarkari_yojna.domain.repositories.YojnaRepository
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkariyojna.data.utils.Lce
import com.himanshu.sarkariyojna.data.utils.changeDispatcherAndConvertToLce
import com.himanshu.sarkariyojna.data.yojna_list.YojnaListTransformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YojnaRepositoryImpl @Inject constructor(
    private val yojnaRemoteDataStore: YojnaRemoteDataStore,
    private val yojnaCacheStore: YojnaCacheDataStore
) : YojnaRepository {

    private val yojnaListDataStore: Store<String, List<Yojna>> =
        StoreBuilder.from(
            fetcher = Fetcher.of { _: String ->
                yojnaRemoteDataStore.getYojnaList()
            },
            sourceOfTruth = SourceOfTruth.Companion.of(
                reader = { transformationId ->

                    val transformation = YojnaListTransformation.getTransformationById(
                        transformationId
                    )
                    yojnaCacheStore.getYojnaList(transformation)
                },
                writer = { _, freshYojnaList ->
                    yojnaCacheStore.addOrUpdateYojna(freshYojnaList)
                },
                delete = { fetchConfigId ->
                    yojnaCacheStore.deleteYojna(fetchConfigId)
                },
                deleteAll = yojnaCacheStore::deleteAllCachedYojnas
            )
        ).build()


    fun getYojnaListAsFlow(
        yojnaListTransformation: YojnaListTransformation,
    ): Flow<Lce<List<Yojna>>> {
        return yojnaListDataStore.stream(
            StoreRequest.cached(
                key = yojnaListTransformation.id,
                refresh = false
            )
        ).changeDispatcherAndConvertToLce()
    }

    override suspend fun getYojnaDetails(
        yojnaId: String,
        requiredLanguage: Language
    ): YojnaDetails {
        TODO("Not yet implemented")
    }

    override suspend fun getYojnaMetaData(
        yojnaId: String
    ): YojnaMetaData {
        TODO("Not yet implemented")
    }

}