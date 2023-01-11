package com.postformacion.springbatch.config;

import com.postformacion.springbatch.domain.ErrorDTO;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class ErrorWriter extends FlatFileItemWriter<ErrorDTO> {
    public ErrorWriter() {
        setResource(new FileSystemResource("post-spring-batch/src/main/resources/errors.csv"));
        setAppendAllowed(false);
        setLineAggregator(new DelimitedLineAggregator<>(){
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    {
                        setNames(new String[]{"location", "date", "temperature"});
                    }
                });
            }
        });
    }
}
