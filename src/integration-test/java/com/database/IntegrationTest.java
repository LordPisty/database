package com.database;

import com.database.command.Factory;
import com.database.command.ICommand;
import com.database.data.DataFrame;
import com.database.result.PrintHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.Assert.assertEquals;

/**
 * Created by screspi.
 */
public class IntegrationTest {

    private static final String NEW_LINE = "\n";
    private static final String BASE_PATH = "/com/database/";
    private static final String BASE_PATH_TEST = BASE_PATH + "test_";
    private static final String BASE_PATH_ORACLE = BASE_PATH + "oracle_";
    private static final String EXTENSION = ".txt";

    private Function<String, ICommand> commandFactory;
    private DB<String, String> db;
    private Consumer resultHandler;
    private OutputStream outputStream;

    @Before
    public void setup() {
        commandFactory = new Factory();
        db = new DB(new DataFrame<>(null), new Stack<>());
        outputStream = new ByteArrayOutputStream();
        resultHandler = new PrintHandler(new PrintStream(outputStream));
    }

    @Test
    public void testProcessA() throws URISyntaxException, IOException {
        testProcess("A");
    }

    @Test
    public void testProcessB() throws URISyntaxException, IOException {
        testProcess("B");
    }

    @Test
    public void testProcessC() throws URISyntaxException, IOException {
        testProcess("C");
    }

    @Test
    public void testProcessD() throws URISyntaxException, IOException {
        testProcess("D");
    }

    @Test
    public void testProcessE() throws URISyntaxException, IOException {
        testProcess("E");
    }

    @Test
    public void testProcessF() throws URISyntaxException, IOException {
        testProcess("F");
    }

    private void testProcess(String index) {
        try (
                Stream<String> stream = Files.lines(Paths.get(this.getClass().getResource(BASE_PATH_TEST + index + EXTENSION).toURI()));
                Stream<String> oracle = Files.lines(Paths.get(this.getClass().getResource(BASE_PATH_ORACLE + index + EXTENSION).toURI()));
        ) {
            stream.
                    map(commandFactory).
                    map(db::execute).
                    forEach(resultHandler::accept);
            final String result = outputStream.toString();

            final String oracleString = oracle.collect(Collectors.joining(NEW_LINE)) + NEW_LINE;

            assertEquals("Test \""+ index +"\": Output should match", oracleString, result);
        } catch (URISyntaxException|IOException e) {
            e.printStackTrace();
        }
    }


}
