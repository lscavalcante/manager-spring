package com.lscavalcante.manager.infra.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;

@Component
public class StairsModelMapper {
    private final ModelMapper modelMapper;

    public StairsModelMapper() {
        this.modelMapper = new ModelMapper();
    }

    public <T, S> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public void map(Object source, Object destination) {
        Assert.notNull(source, "source");
        Assert.notNull(destination, "destination");

        this.modelMapper.map(source, destination);
    }
}
