package com.sanju;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileProcessorTest {

    private FileProcessor fileProcessor;
    private static final String TEST_FILE = "test_file.txt";
    private static final String NON_EXISTENT_FILE = "non_existent_file.txt";

    @Before
    public void setUp() {
        fileProcessor = new FileProcessor();
    }

    @After
    public void tearDown() {
        // Clean up test files after each test
        File testFile = new File(TEST_FILE);
        if (testFile.exists()) {
            testFile.delete();
        }
        File nonExistentFile = new File(NON_EXISTENT_FILE);
        if (nonExistentFile.exists()) {
            nonExistentFile.delete();
        }
    }

    @Test
    public void testWriteAndReadContent() throws IOException {
        String content = "Hello, JUnit Testing!";
        
        fileProcessor.writeToFile(TEST_FILE, content);
        String readContent = fileProcessor.readFromFile(TEST_FILE);
        
        assertEquals(content, readContent);
    }

    @Test
    public void testFileExistsAfterWriting() throws IOException {
        String content = "Testing file existence";
        
        fileProcessor.writeToFile(TEST_FILE, content);
        
        assertTrue(fileProcessor.fileExists(TEST_FILE));
    }

    @Test(expected = IOException.class)
    public void testReadFromNonExistentFile() throws IOException {
        fileProcessor.readFromFile(NON_EXISTENT_FILE);
    }
}
