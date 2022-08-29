package az.elgunsh.bslibrary.mapper;

import az.elgunsh.bslibrary.dao.Publisher;
import az.elgunsh.bslibrary.dto.PublisherRequestDto;
import az.elgunsh.bslibrary.dto.PublisherResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    //    @Mappings(
//            value = {
//                    @Mapping(source = "name", target = "name"),
//                    @Mapping(source = "surname", target = "surname"),
//                    @Mapping(source = "age", target = "age")
//            }
//    )
    Publisher toEntity(PublisherRequestDto source);

    PublisherResponseDto toDto(Publisher source);

    List<PublisherResponseDto> toDto(List<Publisher> publishers);

}