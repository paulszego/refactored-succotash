package io.szego.draw;

import org.junit.Test;

public class RegistryTest
{
    @Test
    public void testParse()
    {
        Registry    reg = new Registry();        
        reg.parse( "C 3 3" );
        reg.parse( "L 1 2 6 2" );
        reg.parse( "R 14 1 18 3" );
        reg.parse( "B 10 3 o" );
        reg.parse( "Q" );
    }
    @Test
    public void testParseEmpty()
    {
        Registry    reg = new Registry();        
        reg.parse( "   " );
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void testParseUnknown()
    {
        Registry    reg = new Registry();        
        reg.parse( "X 42" );
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void testParseWrongCount()
    {
        Registry    reg = new Registry();        
        reg.parse( "C 39" );
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void testParseBadInt()
    {
        Registry    reg = new Registry();        
        reg.parse( "C fifty six" );
    }
}
