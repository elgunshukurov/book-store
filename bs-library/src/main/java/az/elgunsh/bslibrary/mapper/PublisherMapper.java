package az.elgunsh.bslibrary.mapper;

import az.elgunsh.bslibrary.dao.Publisher;
import az.elgunsh.bslibrary.dto.PublisherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);;

//    @Mappings(
//            value = {
//                    @Mapping(source = "name", target = "name"),
//                    @Mapping(source = "surname", target = "surname"),
//                    @Mapping(source = "age", target = "age")
//            }
//    )
    Publisher toEntity(PublisherDto source);

    PublisherDto toDto(Publisher source);

    List<PublisherDto> toDto(List<Publisher> publishers);

}