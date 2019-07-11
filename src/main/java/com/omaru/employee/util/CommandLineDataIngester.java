package com.omaru.employee.util;

import org.apache.commons.cli.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Component
public class CommandLineDataIngester {
    private final DataSource dataSource;
    private static final String DEFAULT_SCRIPT="data-sample.sql";
    @Inject
    public CommandLineDataIngester(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void accept(String... args) throws Exception {
        Options options = new Options();
        Option inputFileOption = new Option("i",true,"ingest testing data from path," +
                "if none provided reads from classpath by default");
        inputFileOption.setArgName("/path/to/file.sql");
        options.addOption(inputFileOption);
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("employee", options);
        CommandLine cmd = parser.parse(options, args);
        Resource resource = new ClassPathResource(DEFAULT_SCRIPT);
        Optional<String> fileRoute = getFileRoute(cmd);
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        if(fileRoute.isPresent()){
            scriptRunner.runScript(new BufferedReader(new FileReader(fileRoute.get())));
        }
        scriptRunner.runScript(new BufferedReader(new InputStreamReader(resource.getInputStream())));
    }

    private Optional<String> getFileRoute(CommandLine cmd) {
        Optional<String> script = Optional.empty();
        if (cmd.hasOption("i")) {
            String value = cmd.getOptionValue("i");
            if(isNotBlank(value)){
               script = Optional.of(value);
            }
        }
        return script;
    }

}
