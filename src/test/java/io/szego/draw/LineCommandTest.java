package io.szego.draw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Function;

import org.junit.Test;

import io.szego.draw.command.LineCommand;

public class LineCommandTest
{
    @Test
    public void testCreate()
    {
        LineCommand   cmd = new LineCommand();
        assertNotNull( cmd );
        
        assertEquals( "Line", cmd.getName() );
        assertEquals( LineCommand.USAGE, cmd.getUsage() );
        assertEquals( 4, cmd.getArgCount() );
    }
    
    @Test
    public void testParse()
    {
        LineCommand                 cmd = new LineCommand();
        Function<Canvas, Canvas>    f   = cmd.parse( "L", "1", "1", "2", "1" );
        assertNotNull( f );
        
        Canvas  c = f.apply( new Canvas( 2, 2 ) );
        assertNotNull( c );
        assertEquals( 
            "----\n" + 
            "|xx|\n" +
            "|  |\n" +
            "----\n", 
            c.toString()
        );
    }    

    @Test
    public void testWithHorz()
    {
        Function<Canvas, Canvas>    f = LineCommand.with( 1, 1, 2, 1 );
        assertNotNull( f );

        Canvas  c = f.apply( new Canvas( 2, 2 ) );
        assertNotNull( c );
        assertEquals( 
            "----\n" + 
            "|xx|\n" +
            "|  |\n" +
            "----\n", 
            c.toString()
        );
    }
    
    @Test
    public void testWithVert()
    {
        Function<Canvas, Canvas>    f = LineCommand.with( 1, 1, 1, 2 );
        assertNotNull( f );

        Canvas  c = f.apply( new Canvas( 2, 2 ) );
        assertNotNull( c );
        assertEquals( 
            "----\n" + 
            "|x |\n" +
            "|x |\n" +
            "----\n", 
            c.toString()
        );
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void testWithDiag()
    {
        LineCommand.with( 1, 1, 2, 2 ).apply( new Canvas( 2, 2 ) );
    }
}
