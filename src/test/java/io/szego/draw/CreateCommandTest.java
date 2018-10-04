package io.szego.draw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Function;

import org.junit.Test;

import io.szego.draw.command.CreateCommand;

public class CreateCommandTest
{
    @Test
    public void testCreate()
    {
        CreateCommand   cmd = new CreateCommand();
        assertNotNull( cmd );
        
        assertEquals( "Create", cmd.getName() );
        assertEquals( CreateCommand.USAGE, cmd.getUsage() );
        assertEquals( 2, cmd.getArgCount() );
    }
    
    @Test
    public void testWith()
    {
        Function<Canvas, Canvas>    f = CreateCommand.with( 1, 1 );
        assertNotNull( f );

        Canvas                      c = f.apply( null );
        assertNotNull( c );
        assertEquals( 
            "---\n| |\n---\n", 
            c.toString()
        );
    }
    
    @Test
    public void testParse()
    {
        CreateCommand               cmd = new CreateCommand();
        Function<Canvas, Canvas>    f   = cmd.parse( "C", "1", "1" );
        assertNotNull( f );
        
        Canvas                      c = f.apply( null );
        assertNotNull( c );
        assertEquals( 
            "---\n| |\n---\n", 
            c.toString()
        );
    }    
}
