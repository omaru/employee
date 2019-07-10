package com.omaru.employee.util;

import org.apache.commons.cli.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class CommandLineDataIngester {
    private DataSource dataSource;
    @Inject
    public CommandLineDataIngester(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void accept(String... args) throws Exception {
        Options options = new Options();
        options.addOption("i", true, "ingest testing data from path e.g. /path/to/file.csv");
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("employee", options);
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("i")) {
            String script = cmd.getOptionValue("i");
            ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
            scriptRunner.runScript(new BufferedReader(new FileReader(script)));
        }
    }

}
