package my.edu.umk.pams.connector.payload;


public enum StudentStatus {
	
	MATRICULATED, //0
	ACTIVE,		  //1
	BARRED,		  //2
	INACTIVE,	  //3
	GRADUATED;	  //4
	
    public static StudentStatus get(int index) {
        return values()[index];
    }

}
