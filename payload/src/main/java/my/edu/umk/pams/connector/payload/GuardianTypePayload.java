package my.edu.umk.pams.connector.payload;


public enum GuardianTypePayload {
	
		MOTHER, 
	    FATHER, 
	    GUARDIAN;
    public static GuardianTypePayload get(int index){
        return values()[index];
    }
}
