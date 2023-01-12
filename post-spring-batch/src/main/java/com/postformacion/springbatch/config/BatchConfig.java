package com.postformacion.springbatch.config;

import com.postformacion.springbatch.domain.ErrorDTO;
import com.postformacion.springbatch.domain.WeatherNotation;
import com.postformacion.springbatch.domain.WeatherRisk;
import com.postformacion.springbatch.listener.NotationJobExecListener;
import com.postformacion.springbatch.listener.NotationProcessListener;
import com.postformacion.springbatch.listener.NotationReaderListener;
import com.postformacion.springbatch.listener.NotationWriterListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<WeatherNotation> notationsReader() {
        return new FlatFileItemReaderBuilder<WeatherNotation>()
                .name("notationReader")
                .resource(new ClassPathResource("prueba.csv"))
                .linesToSkip(1)
                .delimited()
                .names("location","temperature","date")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(WeatherNotation.class);
                }})

                .build();
    }

    @Bean
    public FlatFileItemReader<ErrorDTO> errorReader() {
        return new FlatFileItemReaderBuilder<ErrorDTO>()
                .name("errorReader")
                .resource(new ClassPathResource("errors.csv"))
                .linesToSkip(0)
                .delimited()
                .names("location","date","temperature")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(ErrorDTO.class);
                }})

                .build();
    }

    @Bean
    public NotationProcessor processor() {
        return new NotationProcessor();
    }

    @Bean
    public NotationWriter writer() {
        return new NotationWriter();
    }

    @Bean
    public NotationJobExecListener jobExecutionListener() {
        return new NotationJobExecListener();
    }

    @Bean
    public NotationReaderListener readerListener() {
        return new NotationReaderListener();
    }

    @Bean
    public NotationProcessListener processorListener() {
        return new NotationProcessListener();
    }

    @Bean
    public NotationWriterListener writerListener() {
        return new NotationWriterListener();
    }

    @Bean
    public ErrorProcessor errorProcessor() {
        return new ErrorProcessor();
    }

    @Bean
    public ErrorWriter errorWriter() {
        return new ErrorWriter();
    }

    @Bean
    public Job job(Step step1, Step step2, NotationJobExecListener jobExecutionListener) {
        return jobBuilderFactory
                .get("job1")
                .listener(jobExecutionListener)
                .flow(step1)
                .next(step2)
                .end()
                .build();
    }

    @Bean
    public Step step1(
            NotationWriter writer,
            NotationProcessor processor,
            NotationReaderListener readerListener,
            NotationProcessListener processorListener,
            NotationWriterListener writerListener
    ) {

        return stepBuilderFactory
                .get("step1")
                .<WeatherNotation, WeatherRisk>chunk(100)
                .reader(notationsReader())
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(processorListener)
                .listener(writerListener)
                .build();
    }

    @Bean
    public Step step2(
            ErrorWriter writer,
            ErrorProcessor processor
    ) {
        return stepBuilderFactory
                .get("step2")
                .<WeatherNotation, ErrorDTO>chunk(100)
                .faultTolerant()
                .skipLimit(10)
                .reader(notationsReader())
                .processor(processor)
                .writer(writer)
                .build();
    }

//    @PostConstruct
//    public void checkErrors() throws IOException {
//        Path path = Paths.get("C:\\Users\\pablo.riojo\\Desktop\\Java-Formation\\JF\\post-spring-batch\\src\\main\\resources\\errors.csv");
//        int errorCondition = 100;
//
//        if(path.toFile().exists()) {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()));
//            String input;
//            int count = 0;
//            while ((input = bufferedReader.readLine()) != null) {
//                count++;
//            }
//
//            if (count > errorCondition) throw new RuntimeException("More than " + errorCondition + " errors. Check 'errors.csv': " + count + " errors found");
//        }
//    }
}
