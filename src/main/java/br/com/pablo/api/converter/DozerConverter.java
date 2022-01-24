package br.com.pablo.api.converter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerConverter {

    private static final Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return MAPPER.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {

        List<D> destinationObject = new ArrayList<D>();

        for (Object object : origin) {
            destinationObject.add(MAPPER.map(object, destination));
        }
        return destinationObject;
    }
}
