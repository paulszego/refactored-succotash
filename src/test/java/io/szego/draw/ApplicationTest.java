package io.szego.draw;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest
{
    PrintStream stdout;
    PrintStream stderr;
    InputStream stdin;
    
    ByteArrayOutputStream   out;
    ByteArrayOutputStream   err;
    
    
    @Before
    public void beforeTest()
    {
        stdout  = System.out;
        stderr  = System.err;
        stdin   = System.in;
        
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        
        System.setOut( new PrintStream( out ) );
        System.setErr( new PrintStream( err ) );        
    }
    
    @After
    public void afterTest()
    {
        System.setIn( stdin );        
        System.setOut( stdout );
        System.setErr( stderr );
    }
    
    
    @Test
    public void testQuit() throws IOException
    {
        final String input = "Q\n";
        
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );
        Application.main( );
        
        assertEquals(
            "Ready...\n",
            out.toString() 
        );
        assertEquals(
            "Exit!\n",
            err.toString() 
        );

    }

    @Test
    public void testMain() throws IOException
    {
        final String input = "C 2 2\n";
        
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );
        Application.main( );
        
        assertEquals(
            "Ready...\n" +
                "----\n" +
                "|  |\n" +
                "|  |\n" +
                "----\n" +
                "\n" +
                "Done.\n",
            out.toString() 
        );
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void testFail() throws IOException
    {
        final String input = "X 42\n";
        
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );
        Application.main( );
    }
}
