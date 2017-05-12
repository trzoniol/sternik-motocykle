package pl.sternik.trzonski.motocykle.entities;


public enum Status {
    
    NOWA("Nowy"), 
    DO_SPRZEDANIA("Do sprzedania"),
    DUBLET("Używany");
    
    
    public static final Status[] ALL = { NOWA, DO_SPRZEDANIA, DUBLET };
 
    private final String name;

    private Status(final String name) {
    	this.name = name;
    }  
    
    public String getName() {
        return this.name;
    }
}
