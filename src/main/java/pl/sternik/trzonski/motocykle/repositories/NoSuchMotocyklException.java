package pl.sternik.trzonski.motocykle.repositories;

public class NoSuchMotocyklException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchMotocyklException(String string) {
		super(string);
	}

	public NoSuchMotocyklException() {
	}


}
