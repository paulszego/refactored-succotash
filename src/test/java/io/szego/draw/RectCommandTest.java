package io.szego.draw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Function;

import org.junit.Test;

import io.szego.draw.command.RectCommand;

public class RectCommandTest
{
    @Test
    public void testCreate()
    {
        RectCommand   cmd = new RectCommand();
        assertNotNull( cmd );
        
        assertEquals( "Rect", cmd.getName() );
        assertEquals( RectCommand.USAGE, cmd.getUsage() );
        assertEquals( 4, cmd.getArgCount() );
    }
    
    @Test
    public void testParse()
    {
        RectCommand                 cmd = new RectCommand();
        Function<Canvas, Canvas>    f   = cmd.parse( "R", "1", "1", "2", "2" );
        assertNotNull( f );
        
        Canvas  c = f.apply( new Canvas( 2, 2 ) );
        assertNotNull( c );
        assertEquals( 
            "----\n" + 
            "|xx|\n" +
            "|xx|\n" +
            "----\n", 
            c.toString()
        );
    }    

}
