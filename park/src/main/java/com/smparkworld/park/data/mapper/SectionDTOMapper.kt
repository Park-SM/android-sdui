package com.smparkworld.park.data.mapper

import com.smparkworld.core.mapper.Mapper
import com.smparkworld.core.mapper.MapperDispatcher
import com.smparkworld.park.data.vo.SectionVO
import com.smparkworld.park.domain.dto.SectionDTO
import javax.inject.Inject
import kotlin.reflect.KClass


class SectionDTOMapper @Inject constructor(
    private val mapperDispatcher: MapperDispatcher,
) : Mapper<SectionVO, SectionDTO> {

    override fun map(from: SectionVO): SectionDTO {
//        return SectionDTO(
//            // 여기 ViewType 어떻게 만들지?
//            // Section은 abstract class라서 생성 불가, SectionVO로 추상화된 Section data들의 클래스 타입을 분기해서
//            // MapperManager로 요청해서 받아오는 로직이 되어야할 듯
//        )
        TODO("Not yet implemented")
    }

    override fun equals(from: KClass<*>, to: KClass<*>): Boolean =
        from == SectionVO::class && to == SectionDTO::class
}